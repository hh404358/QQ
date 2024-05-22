package com.ljr.common.socket;

import lombok.Data;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

@Data
public class UDP
{
	public DatagramSocket socket = null;// 通过UDP建立的Socket
	private Integer myPort = 6000;// 我的端口，从6000开始查找可用的端口号
	public InetAddress friendIP = null;// 好友的IP地址
	public Integer friendPort = 0;// 好友的端口

	public UDP(String friendIP) {
		getMyUsefulPort();
		try {
			this.friendIP = InetAddress.getByName(friendIP);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 获取可用的端口号
	 */
	public void getMyUsefulPort() {
		while(true) {
    		try {
    			// 实例化一个DatagramSocket
    			socket = new DatagramSocket(myPort);
    			break;
    		}
    		catch (SocketException e) {
    			myPort++;
    		}
		}
	}

	/*
	 * 给好友发送消息
	 */
	public void sendMessage(String text) {
		byte[] data = text.getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length, friendIP, friendPort);
		try {
			socket.send(packet);// 开始发送消息
			System.out.println("使用UDP成功发送："+text);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接收消息
	 */
	public String getMessage() {
		byte[] buf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		try {
			socket.receive(dp);
			String message = new String(dp.getData(), 0, dp.getLength());
			System.out.println("使用UDP成功接收到："+message);
			return message;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 开辟新线程后台获取消息
	 */
	public void newThreadGetMessage() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					getMessage();// 调用UDP的获取消息方法
					try {
						Thread.sleep(100);// 线程休息100毫秒
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(runnable).start();
	}
	
}
