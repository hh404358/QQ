package com.ljr.client.control;

import com.ljr.client.common.MyLabel;
import com.ljr.client.common.MyTextPane;
import com.ljr.client.frame.ChatFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

import com.ljr.client.frame.FaceFrame;
import com.ljr.client.frame.ScreenFrame;
import com.ljr.client.frame.SendFileFrame;
import com.ljr.client.socket.CC_TCP;
import com.ljr.client.socket.CS_TCP;
import com.ljr.client.socket.UDPChat;
import com.ljr.common.Utils.Utils;
import com.ljr.common.constants.Constant;
import com.ljr.common.constants.Flag;
import com.ljr.common.socket.TCP;



public class Chat extends ChatFrame {

	public String friendIP  =  null;// 好友的IP地址
	public int friendTCPPort = 0;//好友的TCP端口
	public String friendName = "";//好友的名字
	public String myName = "";//自己的名字
	public CC_TCP cc_TCP = null;//发起聊天的TCP
	public TCP tcp = null;//接收聊天的TCP

	public UDPChat chatUDP = null;
	int width  =  680;// 窗体宽度
	int height  =  600;// 窗体高度
	private StyledDocument receiveDocument  =  null;// 用来存放接收框的文本或图片
	private StyledDocument sendDocument  =  null;// 用来存放发送框的文本或图片


	public ServerSocket getFileServer = null;
	public int myGetFilePort = 10000;
	public int friendGetFilePort = 0;


	public CS_TCP cs_TCP = null;//用来当对方不在线时给服务器发送离线消息

	public String ImgPath  =  "";
	String screenCutImgName  =  "";

	public int faceIdx  =  -1;//表情的索引
	/**
	 * 通过UDP构造聊天窗体
	 */
	/*public Chat(String friendIP,String friendName,String myName)
	{
		chatUDP = new UDPChat(friendIP,this);//实例化一个已经封装好的ChatUDP类
		init();
	}*/
	/**
	 * 通过TCP主动发起聊天
	 * @param friendIP
	 * @param friendTCPPort
	 * @param friendName
	 * @param myName
	 */
	public Chat(String friendIP,int friendTCPPort,String friendName,String myName) {
		this.friendIP = friendIP;
		this.friendTCPPort = friendTCPPort;
		this.friendName = friendName;
		this.myName = myName;
		try {
			cc_TCP = new CC_TCP(this.friendIP, this.friendTCPPort,this);
			cc_TCP.sendMessage(Flag.START_CHAT + Constant.FLAGEND + myName);//发起聊天请求
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		init();
		initGetFileServer();//初始化接收文件的服务
	}
	/**
	 * 通过TCP被动接受聊天
	 * @param tcp
	 * @param friendName
	 * @param myName
	 */
	public Chat(TCP tcp,String friendName,String myName) {
		this.tcp = tcp;
		this.friendName = friendName;
		this.myName = myName;
		init();
		initGetFileServer();//初始化接收文件的服务
	}

	/**
	 * 发送离线消息时的构造方法
	 * @param cs_TCP
	 * @param friendName
	 */
	public Chat(CS_TCP cs_TCP,String friendName) {
		this.cs_TCP = cs_TCP;
		this.friendName = friendName;
		init();
		setTitle("给"+friendName+"发送离线消息中……");
	}

	public void init() {
		this.setMinimumSize(new Dimension(width, height));// 设置窗体的最小大小
		if(cc_TCP !=  null)//如果是发起方则直接设置标题，接收方因为对方IP还未知，所以这里还不能设置
			setTitle("与" + friendName+"("+friendIP+":"+friendTCPPort+")聊天中");// 设置窗体标题
		initLabelEvent();// 初始化窗体上所有Label的鼠标移动事件
		Utils.setWindowsMiddleShow(this,width,height);//设置窗体居中显示
		sendButton.setMnemonic(KeyEvent.VK_ENTER);// 设置发送按钮的快捷键为Alt+Enter

		receiveDocument  =  receiveBoard.getStyledDocument();
		sendDocument  =  sendPane.getStyledDocument();
		receiveBoard.insertIcon(Utils.getIcon("/img/warning.png"));
		String warning = "交谈中请勿轻信汇款、中奖信息、陌生电话，勿使用外挂软件。\n";
		try {
			SimpleAttributeSet warn = MyTextPane.getFontAttribute("黑体", 12, Color.blue, false, false);
			receiveDocument.insertString(receiveDocument.getLength(), warning, warn);
			// 设置窗体的顶部图标
			this.setIconImage(ImageIO.read(Main.class.getResource("../img/QQ_64.png")));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化接收文件的服务
	 */
	public void initGetFileServer() {
		while (true){
			try {
				getFileServer  =  new ServerSocket(myGetFilePort);
				System.out.println("已开启文件接收监听！");
				if(cc_TCP != null)
					cc_TCP.sendMessage(Flag.SENDFILE + Constant.FLAGEND + myGetFilePort);
				else
					tcp.sendMessage(Flag.SENDFILE + Constant.FLAGEND + myGetFilePort);
				break;
			}
			catch (Exception e) {
				myGetFilePort++;
			}
		}
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				RecieveThread recieveThread;
				while (true) {
					try {
						Socket socket  =  getFileServer.accept();
						System.out.println("与文件发送方连接成功！");
						recieveThread  =  new RecieveThread(new SendFileFrame(), socket);
						recieveThread.start();
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		new Thread(runnable).start();
	}

	/**
	 * 初始化窗体上所有Label的鼠标移动事件
	 */
	public void initLabelEvent() {
		new MyLabel(font).addEvent();
		new MyLabel(face).addEvent();
		new MyLabel(music).addEvent();
		new MyLabel(picture).addEvent();
		new MyLabel(screenCapture).addEvent();
		new MyLabel(chatHistory).addEvent();
		new MyLabel(video).addEvent();
		new MyLabel(voice).addEvent();
		new MyLabel(sendFile).addEvent();
		new MyLabel(qqSpace).addEvent();
		new MyLabel(myFriends).addEvent();
		new MyLabel(addFriend).addEvent();
	}

	/*
	 * 给好友发送消息
	 */
	@Override
	public void sendMessage() {
		//发送的是文本信息
		if ("".equals(ImgPath) && "".equals(screenCutImgName)) {
			if(faceIdx<0) {
				if(cc_TCP != null) {//如果是发起聊天的一方
					cc_TCP.sendMessage(Flag.MESSAGE + Constant.FLAGEND + sendPane.getText());
				}else if(tcp != null) {//如果是接收聊天的一方
					tcp.sendMessage(Flag.MESSAGE + Constant.FLAGEND + sendPane.getText());
				}else if(cs_TCP != null) {//发送未读消息
					cs_TCP.sendMessage(Flag.UNDERLINE_MESSAGE + Constant.FLAGEND + friendName + Constant.SPLIT1 + sendPane.getText());
				}
				setReceivePaneText(true,sendPane.getText());//将用户发送的消息显示在聊天框中
				sendPane.setText("");// 清空发送框
			}
			else {
				if(cc_TCP != null)//如果是发起聊天的一方
					cc_TCP.sendMessage(Flag.FACE + Constant.FLAGEND + faceIdx);
				else if(tcp != null)//如果是接收聊天的一方
					tcp.sendMessage(Flag.FACE + Constant.FLAGEND + faceIdx);
				new MyTextPane(receiveBoard).addIcon(Utils.getFaceByIdx(faceIdx), myName);
				sendPane.setText("");// 清空发送框
				faceIdx = -1;//图片表情发完后置为默认
			}

		}
		//发送的是图片
		else  if(!"".equals(ImgPath)) {
			try {
				//置空发送框
				sendPane.setText("");
				//接收框显示图片
				receiveBoard.insertIcon(new ImageIcon(ImageIO
						.read(Files.newInputStream(Paths.get(ImgPath)))));
				//发送图片给对方
				cc_TCP.sendImg(ImgPath);


				//发送完图片后置空图片路径
				ImgPath  = "";
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {    //置空发送框
				sendPane.setText("");
				//接收框显示图片
				receiveBoard.insertIcon(new ImageIcon(ImageIO
						.read(Files.newInputStream(Paths.get("./screenCut/snap.jpg")))));
				//发送图片给对方
				cc_TCP.sendImg("./screenCut/snap.jpg");


				//发送完图片后置空图片路径
				screenCutImgName  = "";
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 设置发送框文本
	 *
	 * @param text
	 *            需要设置的文本
	 */
	public void setSendPaneText(String text) {}

	/**
	 * 设置接收框的文本
	 *
	 * @param text
	 *            需要设置的文本
	 */
	public void setReceivePaneText(boolean isFromMyself, String text) {
		if (isFromMyself) {
			String time =  DateFormat.getTimeInstance().format(new Date());
			new MyTextPane(receiveBoard).addText(myName+" "+time+ "\n",MyTextPane.getTimeAttribute());
			new MyTextPane(receiveBoard).addText(text+ "\n",MyTextPane.getMyAttribute());
		}
		else {
			String time =  DateFormat.getTimeInstance().format(new Date());
			new MyTextPane(receiveBoard).addText(friendName+" "+time+ "\n",MyTextPane.getTimeAttribute());
			new MyTextPane(receiveBoard).addText(text+ "\n",MyTextPane.getFriendAttribute());
		}
	}
	/**
	 * 发送文件
	 */
	public void sendFile() {
		System.out.println("对方已开启文件接收监听！");
		SendFileFrame sendFileFrame = new SendFileFrame();
		sendFileFrame.lblProgress.setText("正在等待文件被接收....");
		String filePath  =  sendFileFrame.showDialog(JFileChooser.FILES_ONLY);
		sendFileFrame.setVisible(true);
		SendTread sendThread;
		//sendThread  =  new SendTread(sendFileFrame, filePath,message.split(Constant.SPLIT1)[0],Integer.parseInt(message.split(Constant.SPLIT1)[1]));
		sendThread  =  new SendTread(sendFileFrame, filePath,friendIP,friendGetFilePort);
		sendThread.start();
	}
	/*
	 * 发生在窗体关闭之前
	 */
	public void beforeClose(){}

	public void selectFace() {
		new FaceFrame(this);
	}
	/*
	 * 发送图片
	 */
	public void sendImg() {
		JFileChooser chooser  =  new JFileChooser();

		// 添加过滤器
		chooser.addChoosableFileFilter(new FileFilter() {

			@Override
			public String getDescription() {

				return ".jpg/.png/.bmp";
			}

			@Override
			public boolean accept(File file) {
				// 获取文件名
				String fileName  =  file.getName();
				if (file.isDirectory())
					return true;

				// 过滤文件名
				if (fileName.endsWith(".jpg") || fileName.endsWith(".png")
						|| fileName.endsWith(".bmp"))
				{
					return true;
				}

				return false;
			}
		});

		int result  =  chooser.showOpenDialog(null);

		// 选择打开时
		if (result  ==  JFileChooser.APPROVE_OPTION)
		{
			String filePath  =  chooser.getSelectedFile().getAbsolutePath();
			// 给图片添加；路径
			ImgPath  =  filePath;
			try {

				sendPane.insertIcon(new ImageIcon(ImageIO
						.read(new FileInputStream(filePath))));
			}
			catch (FileNotFoundException e) {
				System.out.println("文件未找到");
				e.printStackTrace();
			}
			catch (IOException e) {
				System.out.println("io异常");
				e.printStackTrace();
			}

		}
		// 选择关闭是
		else {
			dispose();
		}

	}


	/*
	 * @截图
	 */
	@Override
	public void screenFrame() {
		try {
			ScreenFrame.main();
			System.out.println("截图的名字是"+screenCutImgName);
			sendPane.insertIcon(new ImageIcon(ImageIO
					.read(Files.newInputStream(Paths.get("./screenCut/snap.jpg")))));
			screenCutImgName = "snap";
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}