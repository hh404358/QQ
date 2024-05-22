package com.ljr.common.socket;

import com.ljr.client.common.MyTextPane;
import com.ljr.client.control.Chat;
import com.ljr.common.Utils.Utils;
import com.ljr.common.constants.Constant;
import com.ljr.common.constants.Flag;
import lombok.Data;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.Arrays;

@Data
public class TCP
{
	private Socket socket = null;
	private String clientName = "";//用户名
	private Integer clientServerPort = 0;//客户端端口号
	private Integer userState = 0;//0表示在线

	/**
	 * 客户端TCP初始化
	 * @param serverIP 服务端IP地址
	 * @param serverPort  服务端端口号
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public TCP(String serverIP, Integer serverPort) throws Exception {
		socket = new Socket(serverIP, serverPort);
		System.out.println("客户端TCP成功启动！");
		getMessageNewThread();//对于客户端的TCP，给它启动一个新线程不停的接收消息
	}
	
	/**
	 * TCP的构造方法
	 * @param server 传过来的Server端TCP连接
	 */
	public TCP(ServerSocket server) {
		try {
			socket = server.accept();
			System.out.println("新增一台客户端与服务端连接！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭Socket连接
	 */
	public void closeSocket() {
		try {
			if(socket != null)
				socket.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 开辟新线程后台接收消息并处理
	 */
	public void getMessageNewThread() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				boolean exit = false;
				//循环监听客户端发送过来的请求
				while(!exit) {
					try {
						//将获取的消息按#分割，msg[0]的表示标志，msg[1]表示内容
						String[] temp = getMessage().split(Constant.FLAGEND);
						String flagHead = temp[0];//获取消息的标志
						String message = temp[1];//获取消息的真实内容
						Flag flag = Utils.stringToFlagEnum(flagHead);//获取标志
						System.out.println("tcp getmessage: "+ Arrays.toString(temp));
						//处理相应请求
						dealWithMessage(flag,message);
					}
					catch (Exception e) {
						dealWithExit();
						exit = true;
					}
				}
			}
		};
		new Thread(runnable).start();
	}
	
	public void dealWithMessage(Flag flag,String message) {	}

	public void dealWithExit() {}

	/**
	 * 发送消息
	 * @param text
	 * 要发送的文本
	 */
	public void sendMessage(String text) {
		try {
			// 数据以字节流的形式保存
			socket.getOutputStream().write(text.getBytes());
			System.out.println("TCP成功发送：" + text);  
			Thread.sleep(100);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取消息
	 * @return 获取的文本
	 */
	public String getMessage() throws Exception {
    	byte[] b = new byte[1024];
    	// 数据以字节流的形式读出
    	int len = socket.getInputStream().read(b);
    	String str = new String(b, 0, len);
    	System.out.println("TCP接收到：" + str);
    	return str;
	}

	public void sendFile() {}

	/**
	 * 根据图片路径发送图片
	 * @param imgPath
	 */
	public void sendImg(String imgPath) {
		try {
			File imgFile = new File(imgPath);
			FileInputStream imgFileIn = new FileInputStream(imgFile);
			// 获取文件名
			String imgName = imgFile.getName();
			byte[] buff = new byte[1024];
			int len = 0;

			// 首先发送文件名
			sendMessage(Flag.SENDIMG+ Constant.FLAGEND + imgName);
			OutputStream out = socket.getOutputStream();

			// 开始发送图片
			System.out.println("开始发送图片……");
			if(socket.isClosed()) {
				System.out.println("已关闭");
			}
			else {
				System.out.println("未关闭");
			}
			while ((len = imgFileIn.read(buff)) != -1) {
				System.out.println("111");
				out.write(buff, 0, len);
				System.out.println("222");
			}
			out.write(Constant.SEND_FILE_SUCCESS.getBytes());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("图片已发送完成");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到图片
	 */
	public void getImg(Chat chat , String imgName) {
		File imgFile = null;
		try {
			InputStream in = socket.getInputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			// 存放接收文件对象
			imgFile = new File("./" + System.currentTimeMillis() + imgName);
			FileOutputStream imgOut = new FileOutputStream(imgFile);

			System.out.println("12342");
			len = 1024;
			while (len == 1024) {
				System.out.println("len="+len);
				
				System.out.println("图片正在接收....");
				len = in.read(buff,0,buff.length);
				String str = new String(buff,0,len);
				if(str.equals(Constant.SEND_FILE_SUCCESS)) {
					break;
				}
				imgOut.write(buff, 0, len);
			}
			System.out.println("图片接收完成");
			in.close();
			imgOut.close();
			
			new MyTextPane(chat.receiveBoard).addIcon(ImageIO
					.read(Files.newInputStream(imgFile.toPath())), chat.friendName);
			System.out.println("图片插入完成");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取客户端的IP地址
	 * @return
	 */
	public String getClientIP()
	{
		return socket.getInetAddress().getHostAddress().replaceAll("/", "");
	}
	
	/**
	 *  获取本机IP地址
	 * @return 返回字符串形式的IP地址
	 */
	public static String getLocalHostIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress().replaceAll("/", "");
		}
		catch (UnknownHostException e) {
			return null;
		}
	}

}
