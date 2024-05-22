package com.ljr.server;

import com.ljr.common.constants.Constant;
import com.ljr.common.constants.Flag;
import com.ljr.common.socket.TCP;
import com.ljr.common.socket.TCPServer;
import com.ljr.server.config.MybatisConfig;
import com.ljr.server.entity.Msg;
import com.ljr.server.entity.Users;
import com.ljr.server.mapper.IMsgMapper;
import com.ljr.server.mapper.IUserMapper;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


/**
 * 服务端
 */

public class QQServer extends TCPServer {
	MybatisConfig config = new MybatisConfig();
//	IUserMapper userMapper ;
//	IMsgMapper msgMapper ;
	IUserMapper userMapper = MybatisConfig.getUserMapper();
	IMsgMapper msgMapper = MybatisConfig.getMsgMapper();
	public QQServer(Integer serverPort) throws IOException {
		super(serverPort);
		userMapper = MybatisConfig.getUserMapper();
		msgMapper = MybatisConfig.getMsgMapper();

	}
//	UserDao userDao = new UserDao();
//	MsgDao msgDao = new MsgDao();
	public QQServer(int serverPort) throws IOException {
		super(serverPort);
		/*userDao = new UserDao();
		msgDao = new MsgDao();*/
		userMapper = MybatisConfig.getUserMapper();
		msgMapper = MybatisConfig.getMsgMapper();

	}


	//主函数
	public static void main(String[] args) throws IOException {
		new QQServer(Constant.QQServerPort);
	}

	/**
	 * 处理客户端发来的各种信息
	 * @param flag 信息标志
	 * @param message 消息内容
	 * @param tcp TCP连接
	 */
	@Override
	public void dealWithMessage(Flag flag, String message, TCP tcp) throws SQLException {
		switch(flag){
			case LOGIN:
				doLogin(message,tcp);
				break;//处理用户登录事件
			case GET_FRIEND_INFO:
				doGetFriendInfo(message,tcp);
				break;//处理用户发来的请求好友资料的事件
			case REGISTER:
				doRegister(message,tcp);
				break;   //注册事件
			case CHANGE:
				doChange(message,tcp);
				break;   //个人管理
			case QUN_CHAT:
				doQunChat(message,tcp);
				break;     //处理用户发送的群聊事件
			case UNDERLINE_MESSAGE:
				doUnderlineMessage(message,tcp);
				break;//处理用户发来的离线消息
			default:
				break;
		}
	}
	
	/**
	 * 处理客户端退出的相关事件
	 * @param tcp TCP连接
	 */
	@Override
	public void dealWithExit(TCP tcp) {
		refreshAllUserFriendList();
		Users uses = new Users();
		uses.setName(tcp.getClientName());
		uses.setState(Constant.EXIT);
		userMapper.update(uses);//将用户的状态更新到数据库中去
		//userDao.update(uses);
		System.out.println("用户" + tcp.getClientName() + "已退出！");
		showOnlineNumber();
	}
	
	/* 
	 * 服务端启动后要做的事情
	 */
	@Override
	public void afterServerStart(){
		System.out.println("服务器已启动！");
		//显示在线人数
		showOnlineNumber();
	}

	/**
	 * 处理客户端登录
	 * @param message
	 */
	public void doLogin(String message,TCP tcp) {
		System.out.println("客户端" + tcp.getClientIP() + "尝试登录……");
		String[] temp = message.split(Constant.SPLIT1);
		String name = temp[0];//用户名
		String password = temp[1];//用户密码
		int port = Integer.parseInt(temp[2]);//用户端口号
		int userState = Integer.parseInt(temp[3]);//用户状态
		System.out.println("用户你好！");
		System.out.println(userMapper);
		System.out.println(msgMapper);
		System.out.println(userMapper.queryByName(name).toString());
		System.out.println();
		//如果用户名和密码都正确
		if(/*userDao.checkNameAndPwd(name,password)*/Objects.equals(userMapper.queryByName(name).getPwd(), password)){
			//如果重复登录
			if(checkIsLoginAgain(name)) {
				tcp.sendMessage(Flag.LOGIN + Constant.FLAGEND + Flag.FAILED + Constant.SPLIT1 + "您不能重复登录！");
			}
			else {
				tcp.setClientName(name);//设置登录用户的名字到TCP中保存
				tcp.setClientServerPort(port);//保存当前登录用户的端口
				tcp.setUserState(userState);//保存当前登录用户的状态
				//Users uses = userDao.queryByName(name);
//				uses.setName(name);
				Users uses = userMapper.queryByName(name);
				uses.setState(String.valueOf(userState));
				//uses.setIp(tcp.getClientIP());
				uses.setLastLogin(LocalDateTime.now().toLocalDate().toString());
				//uses.setBirthday();
				try{
					userMapper.update(uses);
				}catch (
						Exception e
				){
					e.printStackTrace();
				}
				userMapper.update(uses);//更新用户信息
				//userDao.update(uses);
				tcp.sendMessage(Flag.LOGIN + Constant.FLAGEND + Flag.SUCCESS + Constant.SPLIT1 + getCurrentUserInfo(tcp));//发送一个消息给用户提示登录成功

				try{
					Thread.sleep(50);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshAllUserFriendList();//刷新所有用户的好友列表，但不包括当前登录用户
				
				try{
					Thread.sleep(50);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				sendUnreadMessage(tcp);//发送用户未读消息
				showOnlineNumber();//显示当前在线人数
			}
		}//如果登录失败
		else {
			tcp.sendMessage(Flag.LOGIN + Constant.FLAGEND + Flag.FAILED + Constant.SPLIT1 + "用户名或密码错误！");
		}
	}
	
	/**
	 * 处理用户发来的请求好友资料的事件
	 * @param message
	 * @param tcp
	 */
	public void doGetFriendInfo(String message,TCP tcp) {
		Users users = userMapper.queryByName(tcp.getClientName());
		//Users users = userDao.queryByName(tcp.getClientName());
		StringBuffer sb = new StringBuffer();
		sb.append("用户ID：").append(users.getId()).append("\n");
		sb.append("用户名：").append(users.getName()).append("\n");
		sb.append("性别：").append(users.getGender()).append("\n");
		sb.append("电子邮件：").append(users.getEmail()).append("\n");
		sb.append("最后一次登录：").append(users.getLastLogin()).append("\n");
		sb.append("最后一次退出：").append(users.getLastExit()).append("\n");
		sb.append("个性签名：").append(users.getSignature()).append("\n");
		sb.append(Constant.BIRTHDAY).append(users.getBirthday()).append("\n");
		String userInfo = new String(sb);
		tcp.sendMessage(Flag.GET_FRIEND_INFO + Constant.FLAGEND + userInfo);
	}
	
	/**
	 * 处理用户发来的注册请求
	 * @param message
	 * @param tcp
	 */
	public void doRegister(String message,TCP tcp) throws SQLException {
		String[] temp = message.split(Constant.SPLIT1);
		String name = temp[0];//姓名
		String password = temp[1];//密码
		String sex = temp[2];//性别
		String email = temp[3];//电子邮件
		Date birthday;//生日
		String repost = Constant.REGISTER_SUCCESS;//给用户的回复
		try {
			System.out.println(Constant.BIRTHDAY + temp[4]);
			birthday = Date.valueOf(temp[4]);
			System.out.println(birthday);
		}
		catch (Exception e) {
			birthday = Date.valueOf(String.valueOf(LocalDateTime.now()));
			repost += "\n生日日期格式不正确，系统已给您设置成默认的生日：" + birthday;
		}
		String signature = temp[5];//个性签名
		String headImageIdx = temp[6];//头像索引
		Users users = new Users(name, password, sex, email, signature, headImageIdx, birthday);
		if(/*userDao.queryByName(name) != null*/userMapper.queryByName(name) != null) {
			tcp.sendMessage(Flag.REGISTER + Constant.FLAGEND + Flag.FAILED + Constant.SPLIT1 + "用户名已存在！");
		}
		else {
			userMapper.add(users);
			//userDao.add(users);
			tcp.sendMessage(Flag.REGISTER + Constant.FLAGEND + Flag.SUCCESS + Constant.SPLIT1 + repost);

		}
	}
	
	/**
	 * 处理用户发来的个人信息修改管理
	 * @param message
	 * @param tcp
	 */
	public void doChange(String message,TCP tcp) {
		String[] temp = message.split(Constant.SPLIT1);
		String name = temp[0];//姓名
		String password = temp[1];//密码
		String sex = temp[2];//性别
		String email = temp[3];//电子邮件
		Date birthday;//生日
		String repost = Constant.MODIFY_SUCESS;//给用户的回复
		try {
			System.out.println(Constant.BIRTHDAY + temp[4]);
			birthday = Date.valueOf(temp[4]);
			System.out.println(birthday);
		}
		catch (Exception e) {
			birthday=Date.valueOf(String.valueOf(LocalDateTime.now()));
			repost += Constant.WRONG_BIRTHDAY + birthday;
		}
		String signature = temp[5];//个性签名
		String headImageIdx = temp[6];//头像索引
		Users users = new Users(name, password, sex, email, signature, headImageIdx, birthday);
		if(/*userDao.queryByName(name) != null*/userMapper.queryByName(name) != null) {
			if(/*userDao.update(users)*/userMapper.update(users)) {
				tcp.sendMessage(Flag.USERINFO + Constant.FLAGEND + Flag.SUCCESS + Constant.SPLIT1 + repost);
			}
		}else{
			tcp.sendMessage(Flag.USERINFO + Constant.FLAGEND + Flag.FAILED + Constant.SPLIT1 + Constant.REGISTER_FAILED);

		}
	}
	

	/**
	 * 检查用户是否重复登录
	 * @param name 用户名
	 * @return 返回的真假值
	 */
	public boolean checkIsLoginAgain(String name) {
		boolean res = false;
		for(TCP tcp : tcpSockets)
			if(tcp.getClientName().equals(name)) {
				res = true;
				break;
			}
		return res;
	}
	
	/**
	 * 初始化客户端信息
	 * @param tcp
	 */
	public void initClientInfo(TCP tcp) {
		tcp.sendMessage(Flag.USERINFO + Constant.FLAGEND + getCurrentUserInfo(tcp));
	}

	/**
	 * 刷新所有在线用户的好友列表
	 */
	public void refreshAllUserFriendList() {
		for(TCP tcp : tcpSockets)
			tcp.sendMessage(Flag.FRIENDS_LIST + Constant.FLAGEND + getAllUsersInfo());
	}
	
	/**
	 * 获取当前用户的信息
	 * @return  用户相关信息
	 */
	public String getCurrentUserInfo(TCP tcp) {
		//示例：用户名;状态;个性签名;头像
		StringBuffer sb = new StringBuffer();
		String userName = tcp.getClientName();
		//Users users = userDao.queryByName(userName);
		Users users = userMapper.queryByName(userName);//从数据库中查询用户信息
		sb.append(userName).append(Constant.SPLIT2//用户名
        ).append(tcp.getUserState()).append(Constant.SPLIT2//状态
        ).append(users.getSignature()).append(Constant.SPLIT2//签名
        ).append(users.getHeadImg());//头像
        return new String(sb);
	}
	
	
	/**
	 * 将所有用户的信息转换成字符串
	 * @return
	 */
	public String getAllUsersInfo() {
		//示例：刘显安,192.168.1.1,8888;吴志强,192.168.1.2,6666
		StringBuffer sb = new StringBuffer();
		sb.append("所有在线用户" + Constant.SPLIT2 + "所有不在线用户" + Constant.SPLIT2 + "我的好友" + Constant.SPLIT1);
		for(TCP tcp:tcpSockets) {
			//4表示隐身
			if(!Objects.equals(tcp.getUserState(), Constant.INVISIBLE)){
				System.out.println("客户端名:" + tcp.getClientName());
    			sb.append(tcp.getClientName()).append(Constant.SPLIT3//用户名
                ).append(tcp.getClientIP()).append(Constant.SPLIT3//IP
                ).append(tcp.getClientServerPort()).append(Constant.SPLIT3//端口号
                ).append(userMapper.queryByName(tcp.getClientName()).getHeadImg()).append(Constant.SPLIT3//头像
                ).append(tcp.getUserState()).append(Constant.SPLIT2);//用户状态
			}
		}
		String onlineUser = new String(sb);
		onlineUser = onlineUser.substring(0,onlineUser.length() - 1);//去掉最后一个逗号
		onlineUser += Constant.SPLIT1;//加上一个分号
		
		
		sb = new StringBuffer();
		//List<Users> userList = userDao.queryAll();
		List<Users> userList=userMapper.queryAll();
		for(Users users : userList) {
			if(users.getState().equals(Constant.EXIT)||
					users.getState().equals(Constant.INVISIBLE.toString())||
					users.getState().isEmpty()) {
				sb.append(users.getName()).append(Constant.SPLIT3).
						append("下线或隐身").append(Constant.SPLIT3).
						append("0").append(Constant.SPLIT3).
						append(users.getHeadImg()).append(Constant.SPLIT3).
						append(Constant.EXIT).append(Constant.SPLIT2);
			}
		}
		String underlineUser = new String(sb);
		if(!underlineUser.isEmpty()) {
			underlineUser = underlineUser.substring(0, underlineUser.length() - 1);//去掉最后一个逗号
		}
		underlineUser += Constant.SPLIT1;//加上一个分号
		
		String myfriend = Constant.NONE;
		
		return onlineUser + underlineUser + myfriend;
	}
	
	/**
	 * 显示当前在线人数
	 */
	public void showOnlineNumber() {
		System.out.println("当前总在线人数：" + tcpSockets.size());
	}
	
	public void doQunChat(String message,TCP tcp){
		for(TCP t : tcpSockets) {
			t.sendMessage(Flag.QUN_CHAT + Constant.FLAGEND + message);
		}
	}
	
	/**
	 * 处理用户发来的离线消息
	 * @param message
	 * @param tcp
	 */
	public void doUnderlineMessage(String message,TCP tcp) throws SQLException {
		int sendFrom = userMapper.queryByName(tcp.getClientName()).getId();
		int sendTo = userMapper.queryByName(message.split(Constant.SPLIT1)[0]).getId();

//		Integer sendFrom = userDao.queryByName(tcp.getClientName()).getId();
//		Integer sendTo = userDao.queryByName(message.split(Constant.SPLIT1)[0]).getId();
		Msg msg = new Msg(message.split(Constant.SPLIT1)[1],sendFrom,sendTo,LocalDateTime.now().toString(),"", "");
		System.out.println(msg);
        msgMapper.insertMsg(msg);
		//msgDao.insertMsg(msg);
    }
	
	
	/**
	 * 给用户发送未读消息
	 */
	public void sendUnreadMessage(TCP tcp){
		String userName = tcp.getClientName();
		//int userID = userDao.queryByName(userName).getId();
		int userID = userMapper.queryByName(userName).getId();
		List<Msg> msgs = msgMapper.selectMsgBySendTo(userID);
		//List<Msg> msgs = msgDao.selectMsgBySendTo(userID);
		StringBuilder sb = new StringBuilder();
		sb.append(Flag.UNDERLINE_MESSAGE + Constant.FLAGEND);
		if(!msgs.isEmpty()){
			for(Msg msg:msgs){
				//sb.append(userDao.queryById(msg.getSendFrom()).getName()).append("(").append(msg.getSendTime()).append("):\n").append(msg.getMsgContent()).append("\n");

				sb.append(userMapper.queryById(msg.getSendFrom()).getName()).append("(").append(msg.getSendTime()).append("):\n").append(msg.getMsgContent()).append("\n");
			}
			String unreadMessage = new String(sb);
			tcp.sendMessage(unreadMessage);
			System.out.println("给用户发送未读离线消息成功！");
			msgMapper.deleteMsgBySendTO(userID);
			//msgDao.deleteMsgBySendTO(userID);
			System.out.println("删除用户未读消息成功！");
		}
	}
	
}
