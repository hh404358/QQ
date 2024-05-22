package com.ljr.common.socket;

import com.ljr.common.Utils.Utils;
import com.ljr.common.constants.Constant;
import com.ljr.common.constants.Flag;
import com.ljr.server.config.MybatisConfig;


import com.ljr.server.mapper.IMsgMapper;
import com.ljr.server.mapper.IUserMapper;
import lombok.Data;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.util.ArrayList;

@Data
public class TCPServer
{
	public ServerSocket server = null;// 服务端
    public ArrayList<TCP> tcpSockets = new ArrayList<TCP>();// 存放与客户端的TCPSocket连接
	private Integer serverPort = 0;//服务端的端口
	public boolean isStart = false;// 是否启动了服务端
	IMsgMapper msgMapper ;
	IUserMapper userMapper ;

	/**
	 * 服务端TCP初始化
	 */
	public TCPServer(int serverPort) throws IOException {
		this.serverPort = serverPort;
		isStart = true;
		//启动TCP服务端
		if(startServerTCP()) {
			afterServerStart();	//服务端启动后要做的事情
			userMapper = MybatisConfig.getUserMapper();
			msgMapper = MybatisConfig.getMsgMapper();
			getConnectionNewThread();//开辟新线程不断的接受连接
		}
	}
	/**
	 * 关闭服务器
	 */
	public void closeServer() {
		isStart = false;
		try {
			server.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 启动TCP服务端
	 */
	public boolean startServerTCP() {
		//启动qq服务器
		if(serverPort == Constant.QQServerPort){
    		try {
				server = new ServerSocket(serverPort);
				return true;
			} catch (IOException e) {
				System.out.println("启动QQ服务器失败！端口" + serverPort + "已被占用，请检查是否已开启了一个QQ服务器！");
				return false;
			}
			
		}
		//启动普通TCP服务端
		else {
			while(true) {
        		try {
        			server = new ServerSocket(serverPort);
        			break;
        		}
        		catch (IOException e) {
        			serverPort++;
        		}
        	}
			return true;
		}
	}
	/**
	 * 开辟新线程不断的接受连接
	 */
	public void getConnectionNewThread() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (isStart) {
        			TCP tcp = new TCP(server);
        			tcpSockets.add(tcp);
        			getMessageNewThread(tcp);
        		}
			}
		};
		new Thread(runnable).start();
	}
	/**
	 * 开辟新线程后台接收消息并处理
	 * @param tcp 传过来的TCP连接
	 */
	public void getMessageNewThread(final TCP tcp) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				boolean exit = false;
				//一直循环监听客户点请求事件
				while(!exit) {
					try {
						//将获取的消息按#分割，msg[0]的表示标志，msg[1]表示内容
						String[] temp = tcp.getMessage().split(Constant.FLAGEND);
						String flagHead = temp[0];//获取消息的标志
						String message = temp[1];//获取消息的真实内容
						Flag flag = Utils.stringToFlagEnum(flagHead);//获取标志
						System.out.println("flagHead:" + flagHead+",message:" + message + ",flag:" + flag);
						dealWithMessage(flag,message,tcp);
					}
					catch (Exception e) {
						//e.printStackTrace();
						//从ArrayList中移除已退出的用户
						if(tcpSockets.remove(tcp)) {
							dealWithExit(tcp);
						}
						exit = true;
					}
				}
			}
		};
		new Thread(runnable).start();
	}
	/**
	 * 处理客户端发来的各种信息
	 * 被子类重写
	 * @param flag 信息标志
	 * @param message 消息内容
	 * @param tcp TCP连接
	 */
	public void dealWithMessage(Flag flag,String message,TCP tcp) throws SQLException {	}
	/**
	 * 处理客户端退出的相关事件
	 * @param tcp TCP连接
	 */
	public void dealWithExit(TCP tcp) { }
	/**
	 * 服务端启动后要做的事情，把这部分单独提取出来的目的是为了方便子类继承时重写
	 */
	public void afterServerStart()
	{
		System.out.println("服务端TCP已启动！端口号："+serverPort);
	}



}
