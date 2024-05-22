package com.ljr.client.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public abstract class QunChatFrame extends JFrame {

	private JPanel contentPane;
	public JTree tree;
	public JButton sendButton;
	public JButton closeButton;
	public JTextPane sendPane;
	public JTextPane recieveBoard;
	public JTextArea groupBoard;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QunChatFrame frame = new QunChatFrame() {
						@Override
						public void selectFace() {

						}

						@Override
						public void sendImg() {

						}

						@Override
						public void screenFrame() {

						}
					};
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QunChatFrame() {
		setTitle("MyQQ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 712, 576);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel rightPane = new JPanel();

		JPanel leftPane = new JPanel();
		leftPane.setBackground(new Color(204, 255, 204));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(leftPane, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(rightPane, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(rightPane, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
						.addComponent(leftPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
		);

		JPanel topPane = new JPanel();
		topPane.setBackground(new Color(0, 153, 255));

		JPanel middlePane = new JPanel();

		JPanel bottomPane = new JPanel();
		bottomPane.setBackground(new Color(204, 255, 255));
		GroupLayout gl_leftPane = new GroupLayout(leftPane);
		gl_leftPane.setHorizontalGroup(
				gl_leftPane.createParallelGroup(Alignment.LEADING)
						.addComponent(bottomPane, GroupLayout.PREFERRED_SIZE, 476, Short.MAX_VALUE)
						.addComponent(topPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
						.addComponent(middlePane, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
		);
		gl_leftPane.setVerticalGroup(
				gl_leftPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_leftPane.createSequentialGroup()
								.addComponent(topPane, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(middlePane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(bottomPane, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
		);
		topPane.setLayout(null);

		JLabel avatar = new JLabel("");
		avatar.setIcon(new ImageIcon(QunChatFrame.class.getResource("/img/QQ_64.png")));
		avatar.setBounds(6, 6, 64, 64);
		topPane.add(avatar);

		JLabel video = new JLabel("");
		video.setIcon(new ImageIcon(QunChatFrame.class.getResource("/img/chat/fun_video_54.png")));
		video.setBounds(82, 16, 54, 54);
		topPane.add(video);

		JLabel voice = new JLabel("");
		voice.setIcon(new ImageIcon(QunChatFrame.class.getResource("/img/chat/fun_voice_54.png")));
		voice.setBounds(138, 16, 54, 54);
		topPane.add(voice);

		JLabel sendFile = new JLabel("");
		sendFile.setIcon(new ImageIcon(QunChatFrame.class.getResource("/img/chat/fun_sendfile_54.png")));
		sendFile.setBounds(193, 16, 54, 54);
		topPane.add(sendFile);
		middlePane.setLayout(new BorderLayout(0, 0));

		recieveBoard = new JTextPane();
		middlePane.add(recieveBoard);

		sendPane = new JTextPane();

		sendButton = new JButton("发送");
		sendButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

		closeButton = new JButton("关闭");
		closeButton.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));

		JLabel font = new JLabel("");
		font.setIcon(new ImageIcon(QunChatFrame.class.getResource("/img/chat/fun_font_32.png")));

		JLabel face = new JLabel("");
		face.setIcon(new ImageIcon(QunChatFrame.class.getResource("/img/chat/fun_face_32.png")));
		face.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectFace();
			}
		});

		JLabel music = new JLabel("");
		music.setIcon(new ImageIcon(QunChatFrame.class.getResource("/img/chat/fun_music_32.png")));

		JLabel picture = new JLabel("");
		picture.setIcon(new ImageIcon(QunChatFrame.class.getResource("/img/chat/fun_picture_32.png")));
		picture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendImg();
			}
		});
		JLabel screenCapture = new JLabel("");
		screenCapture.setIcon(new ImageIcon(QunChatFrame.class.getResource("/img/chat/fun_snap_32.png")));
		screenCapture.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				screenFrame();
			}
		});
		GroupLayout gl_bottomPane = new GroupLayout(bottomPane);
		gl_bottomPane.setHorizontalGroup(
				gl_bottomPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_bottomPane.createSequentialGroup()
								.addContainerGap(285, Short.MAX_VALUE)
								.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addGap(15))
						.addComponent(sendPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_bottomPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(font)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(face)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(music)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(picture)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(screenCapture)
								.addContainerGap(258, Short.MAX_VALUE))
		);
		gl_bottomPane.setVerticalGroup(
				gl_bottomPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_bottomPane.createSequentialGroup()
								.addContainerGap(24, Short.MAX_VALUE)
								.addGroup(gl_bottomPane.createParallelGroup(Alignment.LEADING)
										.addComponent(font)
										.addComponent(face)
										.addComponent(music)
										.addComponent(picture)
										.addComponent(screenCapture))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(sendPane, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(gl_bottomPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(sendButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
		);
		bottomPane.setLayout(gl_bottomPane);
		leftPane.setLayout(gl_leftPane);

		JPanel board = new JPanel();
		board.setBackground(new Color(0, 153, 255));
		board.setBorder(new TitledBorder(null, "\u7FA4\u516C\u544A", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.RED));

		JScrollPane scrollPane = new JScrollPane();
		board.setLayout(new BorderLayout(0, 0));

		groupBoard = new JTextArea();
		groupBoard.setLineWrap(true);
		groupBoard.setForeground(new Color(255, 0, 0));
		groupBoard.setText("\u6682\u65E0\u4EFB\u4F55\u5B98\u65B9\u516C\u544A\uFF01");
		groupBoard.setFont(new Font("微软雅黑", Font.BOLD, 18));
		groupBoard.setBackground(new Color(255, 204, 0));
		groupBoard.setEnabled(false);
		board.add(groupBoard, BorderLayout.CENTER);
		GroupLayout gl_rightPane = new GroupLayout(rightPane);
		gl_rightPane.setHorizontalGroup(
				gl_rightPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rightPane.createSequentialGroup()
								.addGap(0)
								.addGroup(gl_rightPane.createParallelGroup(Alignment.LEADING)
										.addComponent(board, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
								.addGap(0))
		);
		gl_rightPane.setVerticalGroup(
				gl_rightPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_rightPane.createSequentialGroup()
								.addComponent(board, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
		);

		tree = new JTree();
		tree.setRootVisible(false);
		scrollPane.setViewportView(tree);
		rightPane.setLayout(gl_rightPane);
		contentPane.setLayout(gl_contentPane);
	}

	public abstract void selectFace();



	/*
	 * 发送图片
	 */
	public abstract void sendImg();

	/*
	 * @截图
	 */
	public abstract void screenFrame();
}