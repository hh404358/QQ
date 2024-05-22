package com.ljr.client.socket;


import com.ljr.client.common.MyTextPane;
import com.ljr.client.control.Chat;
import com.ljr.client.control.Main;
import com.ljr.client.frame.PublicMessageFrame;
import com.ljr.common.Utils.Utils;
import com.ljr.common.constants.Flag;
import com.ljr.common.socket.TCP;
import com.ljr.common.socket.TCPServer;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class S_TCP extends TCPServer {
	ArrayList<Chat> chats = new ArrayList<Chat>();//存放所有的打开的聊天窗体集合
	Main main = null;//传过来的主窗体

	public S_TCP(int serverPort,Main main) throws IOException {
		super(serverPort);
		this.main = main;
	}


	/**
	 * 处理客户端发来的各种信息
	 * @param flag 信息标志
	 * @param message 消息内容
	 */
	@Override
	public void dealWithMessage(Flag flag, String message, TCP tcp) {
		switch (flag){
			case START_CHAT:
				doStartChat(message,tcp);
				break;
			case MESSAGE:
				showMessage(message,tcp);
				break;
			case SENDFILE:
				doSendFile(message,tcp);
				break;
			case SENDIMG:
				dogetImg(message ,tcp);
				break;
			case FACE:
				doFace(message,tcp);
				break;//如果是表情
			default:
				break;
		}
	}
	
	//接收图片
	private void dogetImg(String message,TCP tcp) {
		Chat currentChatWindoew = null;
		for(Chat chat : chats) {
			if(chat.friendName.equals(tcp.getClientName()))
				currentChatWindoew = chat;
		}
	  tcp.getImg(currentChatWindoew ,message);
	}
	
	
	/**
	 * 处理客户端退出的相关事件
	 * @param tcp TCP连接
	 */
	@Override
	public void dealWithExit(TCP tcp){}
	
	@Override
	public void afterServerStart(){}
	
	//开始启动聊天
	public void doStartChat(String message,TCP tcp){
		tcp.setClientName(message);//设置对方的名字
		if(!message.equals(main.userName.getText())){
			Chat chat = new Chat(tcp,tcp.getClientName(),main.userName.getText());
    		chat.setTitle("与" + tcp.getClientName() +  "("+tcp.getClientIP() + ")聊天中");// 设置窗体标题
    		chats.add(chat);
		}
		else {
			JOptionPane.showMessageDialog(null,	"不能与自己聊天喔！");
		}
	}
	
	//处理接收文本相关事件
	public void showMessage(String message,TCP tcp){
		for(Chat chat:chats){
			if(chat.friendName.equals(tcp.getClientName())){
				if(!chat.isVisible()){
					new PublicMessageFrame(tcp.getClientName() + "给您发来消息",message,chat);
					
				}
				chat.setReceivePaneText(false, message);
			}
		}
	}
	
	//处理接收文件的相关事件
	public void doSendFile(String message,TCP tcp) {
		for(Chat chat:chats) {
			if(chat.friendName.equals(tcp.getClientName())) {
				chat.friendGetFilePort = Integer.parseInt(message);
				System.out.println("已成功获取好友的接收文件端口:" + message);
			}
		}
	}
	
	//处理发送表情事件
	public void doFace(String message,TCP tcp){
		for(Chat chat:chats){
			if(chat.friendName.equals(tcp.getClientName())){
				new MyTextPane(chat.receiveBoard).addIcon(Utils.getFaceByIdx(Integer.parseInt(message)), chat.friendName);
			}
		}
	}
}
