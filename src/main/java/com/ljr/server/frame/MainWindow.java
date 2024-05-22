package com.ljr.server.frame;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * 服务器管理主窗体
 */
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	ServerMana serverMana;
	MsgMana msgMana;
	UserMana userMana;
	
    //主方法
	public static void main(String[] args) throws IOException {
		new MainWindow().setVisible(true);
	}

	//服务器界面
	public MainWindow() throws IOException {
		setTitle("MyQQ服务器后台管理");
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

		setBounds((size.width-753)/2, (size.height-500)/2, 753, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		serverMana = new ServerMana();
		tabbedPane.addTab("服务管理", new ImageIcon(MainWindow.class.getResource("/img/manager_server.png")), serverMana, null);

		userMana = new UserMana();
		tabbedPane.addTab("用户管理", new ImageIcon(MainWindow.class.getResource("/img/manager_server.png")), userMana, null);

		msgMana = new MsgMana();
		tabbedPane.addTab("消息管理", new ImageIcon(MainWindow.class.getResource("/img/manager_server.png")), msgMana, null);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 492, Short.MAX_VALUE)
								.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				int n = JOptionPane.showConfirmDialog(null,"确认退出吗?","确认对话框",
						JOptionPane.YES_NO_OPTION );
				if(n == JOptionPane.YES_OPTION)
					System.exit(0);
			}});
		validate();
	}
}
