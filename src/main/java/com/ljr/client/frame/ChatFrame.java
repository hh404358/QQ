package com.ljr.client.frame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChatFrame extends javax.swing.JFrame
{
	private javax.swing.JButton closeButton;
	public javax.swing.JButton sendButton;
	private javax.swing.JLabel other;
	private javax.swing.JLabel self;
	public javax.swing.JLabel qqSpace;
	public javax.swing.JLabel addFriend;
	public javax.swing.JLabel sendFile;
	public javax.swing.JLabel picture;
	private javax.swing.JLabel avatar;
	public javax.swing.JLabel font;
	public javax.swing.JLabel myFriends;
	public javax.swing.JLabel screenCapture;
	public javax.swing.JLabel chatHistory;
	public javax.swing.JLabel face;
	public javax.swing.JLabel video;
	public javax.swing.JLabel voice;
	public javax.swing.JLabel music;
	private javax.swing.JPanel sendBoard;
	private javax.swing.JPanel rightJPanel;
	private javax.swing.JPanel leftJPanel;
	private javax.swing.JPanel chatBoard;
	private javax.swing.JPanel topJPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane3;
	public javax.swing.JTextPane sendPane;
	public javax.swing.JTextPane receiveBoard;


	public ChatFrame() {
		initComponents();
	}

	
	private void initComponents() {

		rightJPanel = new javax.swing.JPanel();
		other = new javax.swing.JLabel();
		self = new javax.swing.JLabel();
		leftJPanel = new javax.swing.JPanel();
		topJPanel = new javax.swing.JPanel();
		avatar = new javax.swing.JLabel();
		qqSpace = new javax.swing.JLabel();
		myFriends = new javax.swing.JLabel();
		video = new javax.swing.JLabel();
		voice = new javax.swing.JLabel();
		sendFile = new javax.swing.JLabel();
		addFriend = new javax.swing.JLabel();
		chatBoard = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		receiveBoard = new javax.swing.JTextPane();
		sendBoard = new javax.swing.JPanel();
		sendButton = new javax.swing.JButton();
		closeButton = new javax.swing.JButton();
		javax.swing.JPanel toolBoard= new javax.swing.JPanel();
		font = new javax.swing.JLabel();
		face = new javax.swing.JLabel();
		face.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectFace();
			}
		});
		picture = new javax.swing.JLabel();
		picture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendImg();
			}
		});
		music = new javax.swing.JLabel();
		screenCapture = new javax.swing.JLabel();
		screenCapture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				screenFrame();
			}
		});
		chatHistory = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		sendPane = new javax.swing.JTextPane();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("聊天");
		setBackground(new java.awt.Color(204, 255, 255));
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		rightJPanel.setBackground(new java.awt.Color(204, 255, 255));

		other.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/qqshow/qqshow_girl_02_180.jpg")));   

		self.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/qqshow/qqshow_boy_01.jpg")));   

		javax.swing.GroupLayout rightJPanelLayout = new javax.swing.GroupLayout(
				rightJPanel);
		rightJPanel.setLayout(rightJPanelLayout);
		rightJPanelLayout.setHorizontalGroup(rightJPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(other).addComponent(self));
		rightJPanelLayout.setVerticalGroup(rightJPanelLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				rightJPanelLayout
						.createSequentialGroup()
						.addComponent(other)
						.addGap(18, 18, 18)
						.addComponent(self,
								javax.swing.GroupLayout.PREFERRED_SIZE, 251,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		leftJPanel.setBackground(new java.awt.Color(204, 255, 255));

		topJPanel.setBackground(new java.awt.Color(51, 204, 255));

		avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/QQ_64.png")));   

		qqSpace.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_qzone_54.png")));   

		myFriends.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_myfeeds_54.png")));   

		video.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_video_54.png")));   
		video.setToolTipText("开始视频");

		voice.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_voice_54.png")));   
		voice.setToolTipText("开始语音");

		sendFile.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_sendfile_54.png")));   
		sendFile.setToolTipText("发送文件");
		sendFile.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				sendFileMouseClicked(evt);
			}
		});

		addFriend.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_add_54.png")));   

		javax.swing.GroupLayout topJPanelLayout = new javax.swing.GroupLayout(
				topJPanel);
		topJPanel.setLayout(topJPanelLayout);
		topJPanelLayout
				.setHorizontalGroup(topJPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								topJPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(avatar)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(video)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(voice)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(sendFile)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(qqSpace)
										.addGap(13, 13, 13)
										.addComponent(myFriends)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												23, Short.MAX_VALUE)
										.addComponent(addFriend)
										.addContainerGap()));
		topJPanelLayout
				.setVerticalGroup(topJPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								topJPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												topJPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(avatar)
														.addGroup(
																topJPanelLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(
																				video)
																		.addComponent(
																				voice)
																		.addComponent(
																				sendFile)
																		.addComponent(
																				qqSpace)
																		.addComponent(
																				myFriends)
																		.addComponent(
																				addFriend)))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		chatBoard.setLayout(new java.awt.BorderLayout());

		receiveBoard.setEditable(false);
		jScrollPane1.setViewportView(receiveBoard);

		chatBoard.add(jScrollPane1, java.awt.BorderLayout.CENTER);

		sendBoard.setBackground(new java.awt.Color(204, 255, 255));

		sendButton.setText("发送");
		sendButton.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				sendButtonActionPerformed(evt);
			}
		});

		closeButton.setText("\u5173\u95ed");

		toolBoard.setBackground(new java.awt.Color(204, 255, 255));

		font.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_font_32.png")));   
		font.setToolTipText("\u5b57\u4f53");
		font.setBorder(javax.swing.BorderFactory.createEtchedBorder(
				new java.awt.Color(204, 255, 255), new java.awt.Color(204, 255,
						255)));

		face.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_face_32.png")));
		face.setToolTipText("表情");

		picture.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_picture_32.png")));   
		picture.setToolTipText("发送图片");

		music.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_music_32.png")));   
		music.setToolTipText("发送音乐");

		screenCapture.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_snap_32.png")));   
		screenCapture.setToolTipText("截图");

		chatHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/img/chat/fun_message_history_32.png")));   
		chatHistory.setText("聊天记录");
		chatHistory
				.setToolTipText("打开我的聊天记录");

		javax.swing.GroupLayout toolBoardLayout = new javax.swing.GroupLayout(
				toolBoard);
		toolBoard.setLayout(toolBoardLayout);
		toolBoardLayout
				.setHorizontalGroup(toolBoardLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								toolBoardLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												font,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												36,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(face)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(music)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(picture)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(screenCapture)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												193, Short.MAX_VALUE)
										.addComponent(chatHistory)));
		toolBoardLayout
				.setVerticalGroup(toolBoardLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								toolBoardLayout
										.createSequentialGroup()
										.addGroup(
												toolBoardLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(font)
														.addComponent(face)
														.addComponent(music)
														.addComponent(picture)
														.addComponent(screenCapture)
														.addComponent(
																chatHistory))
										.addContainerGap()));

		jScrollPane3.setViewportView(sendPane);

		javax.swing.GroupLayout sendBoardLayout = new javax.swing.GroupLayout(
				sendBoard);
		sendBoard.setLayout(sendBoardLayout);
		sendBoardLayout
				.setHorizontalGroup(sendBoardLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(toolBoard,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								sendBoardLayout
										.createSequentialGroup()
										.addContainerGap(321, Short.MAX_VALUE)
										.addComponent(
												closeButton,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												76,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												sendButton,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												77,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(5, 5, 5))
						.addComponent(jScrollPane3,
								javax.swing.GroupLayout.DEFAULT_SIZE, 486,
								Short.MAX_VALUE));
		sendBoardLayout
				.setVerticalGroup(sendBoardLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								sendBoardLayout
										.createSequentialGroup()
										.addComponent(
												toolBoard,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												36,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane3,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												96,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(10, 10, 10)
										.addGroup(
												sendBoardLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																sendButton,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																35,
																Short.MAX_VALUE)
														.addComponent(
																closeButton,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																35,
																Short.MAX_VALUE))
										.addContainerGap()));

		javax.swing.GroupLayout leftJPanelLayout = new javax.swing.GroupLayout(
				leftJPanel);
		leftJPanel.setLayout(leftJPanelLayout);
		leftJPanelLayout.setHorizontalGroup(leftJPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(topJPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(sendBoard, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(chatBoard, javax.swing.GroupLayout.DEFAULT_SIZE,
						486, Short.MAX_VALUE));
		leftJPanelLayout
				.setVerticalGroup(leftJPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								leftJPanelLayout
										.createSequentialGroup()
										.addComponent(
												topJPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												89,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												chatBoard,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												260, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												sendBoard,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(leftJPanel,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(rightJPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(rightJPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(leftJPanel,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		// TODO 
		beforeClose();
	}

	private void sendFileMouseClicked(java.awt.event.MouseEvent evt) {
		// TODO 
		sendFile();
	}

	private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO 
		sendMessage();
	}

	


	public javax.swing.JTextPane getchatHistory() {
		return receiveBoard;
	}

	public void setchatHistory(javax.swing.JTextPane chatHistory) {
		this.receiveBoard = chatHistory;
	}

	/**
	 * 发送消息，空的方法，由子类实现
	 */
	public void sendMessage()
	{
	}

	public void beforeClose(){}
	/**
	 * 选择表情
	 */
	public void selectFace(){}
	/**
	 * 处理消息中的表情
	 */
	public void dealIcon(String str)
	{

	}
	/**
	 * 发送文件
	 */
	public void sendFile()
	{

	}


	/**
	 * 发送图片
	 */
	public void sendImg()
	{

	}



	public void screenFrame()
	{

	}

}