package com.ljr.client.socket;

import com.ljr.client.control.Login;
import com.ljr.client.control.Main;
import com.ljr.client.frame.PublicMessageFrame;
import com.ljr.common.Utils.Utils;
import com.ljr.common.constants.Constant;
import com.ljr.common.constants.Flag;
import com.ljr.common.socket.TCP;

import javax.swing.*;
import java.util.ArrayList;



/**
 * 客户端与QQ服务器之间的TCP通讯
 */
public class CS_TCP extends TCP
{
	Login login = null;//登录窗体，从构造方法中传过来
	Main main = null;//主窗体
	
	public CS_TCP(String serverIP, Integer serverPort, Login login,Main main) throws Exception {
		super(serverIP, serverPort);
		this.login = login;
		this.main = main;
	}
	/**
	 * 此构造方法仅注册用
	 */
	public CS_TCP(String serverIP, Integer serverPort) throws Exception {
		super(serverIP, serverPort);
	}


	@Override
	public void dealWithMessage(Flag flag, String message){
		System.out.println("测试" + flag.toString() + "," + message);
		switch(flag){
			case LOGIN:
				doLogin(message);
				break;
			case REGISTER:
				doRegister(message);
				break;
			case CHANGE:
				doChange(message);
				break;
			case USERINFO://用户信息
				doUsersInfo(message);
				break;
			case GET_FRIEND_INFO://服务器发来的指定好友信息
				doGetFriendInfo(message);
				break;
			case FRIENDS_LIST://接收服务器发来的刷新好友列表信息
				doFriendList(message);
				break;
			case QUN_CHAT://处理群消息
				doQunChat(message);
				break;
			case UNDERLINE_MESSAGE://接收服务器发来的未读消息
				doUnreadMessage(message);
				break;
			default:
				break;
		}
	}

	@Override
	public void dealWithExit(){}

	//处理登录相关事件
	public void doLogin(String message){
		//登录成功
		if (message.split(Constant.SPLIT1)[0].equals(Flag.SUCCESS.toString())) {
			login.dispose();//销毁登录窗体
			main.setCS_TCP(login.cs_TCP);
			main.setVisible(true);
			doUsersInfo(message.split(Constant.SPLIT1)[1]);
		}
		// 登录失败
		else if (message.split(Constant.SPLIT1)[0].equals(Flag.FAILED.toString())){
			JOptionPane.showMessageDialog(null,
					message.split(Constant.SPLIT1)[1]);//显示登录失败的原因
		}
	}
	
    //处理注册相关事件
	public void doRegister(String message) {
		if(message.split(Constant.SPLIT1)[0].equals(Flag.SUCCESS.toString())) {
			JOptionPane.showMessageDialog(null, message.split(Constant.SPLIT1)[1]);
		}
		else if(message.split(Constant.SPLIT1)[0].equals(Flag.FAILED.toString())) {
			JOptionPane.showMessageDialog(null, message.split(Constant.SPLIT1)[1], Constant.FAILED, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//处理个人信息修改事件
	public void doChange(String message){
		if(message.split(Constant.SPLIT1)[0].equals(Flag.SUCCESS.toString())) {
			JOptionPane.showMessageDialog(null, message.split(Constant.SPLIT1)[1]);
		}
		else if(message.split(Constant.SPLIT1)[0].equals(Flag.FAILED.toString())) {
			JOptionPane.showMessageDialog(null, message.split(Constant.SPLIT1)[1], Constant.FAILED, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// 处理服务器发回来用户信息
	public void doUsersInfo(String message){
		String[] temp = message.split(Constant.SPLIT2);
		String userName = temp[0];//用户名
		int userState=Integer.parseInt(temp[1]);
		String signature = temp[2];//个性签名
		String headImage = temp[3];//头像
		if(main != null){
			main.userName.setText(userName);
			main.comboBoxState.setSelectedIndex(userState);
			main.signate.setText(signature);
			main.headImg.setIcon(Utils.getIcon("/img/headImage/middle/" + headImage + "_64.jpg"));
		}
	}
	
	//处理获取好友信息
	public void doGetFriendInfo(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}
	
	//处理好友列表
	public void doFriendList(String message){
		System.out.println("客户端已接收到好友列表！");
		System.out.println(message);
		String[] groupNames = message.split(Constant.SPLIT1)[0].split(Constant.SPLIT2);
		ArrayList<String[]> friendNames = new ArrayList<String[]>();
		for(int i = 0;i < groupNames.length;i++) {
			String temp = message.split(Constant.SPLIT1)[i+1];
			if(!temp.equals(Constant.NONE) && !temp.isEmpty()) {
				friendNames.add(temp.split(Constant.SPLIT2));
			}
			else {
				friendNames.add(new String[]{});
			}
		}
		
		main.initjTree(groupNames, friendNames);
	}
	
	//处理群聊
	public void doQunChat(String message) {
		if(main.qunChat != null) {
			main.qunChat.receiveMessage(message);
		}
		else{
			String[] temp = message.split(Constant.SPLIT1);
			new PublicMessageFrame("聊天室未读消息", temp[0] + "说(" + temp[1] + "):\n" + temp[2]);
		}
			
	}
	
	//处理离线消息
	public void doUnreadMessage(String message) {
		new PublicMessageFrame("您有未读离线消息", message);
	}
}
