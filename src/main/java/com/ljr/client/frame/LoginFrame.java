package com.ljr.client.frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class LoginFrame extends JFrame
{

	private JPanel contentPane;
	public JPasswordField pwd;
	private JLabel lblQQ2013;
	public JLabel avatar;
	public JCheckBox remember_pwd;
	public JCheckBox auto_login;
	public JLabel login;
	public JTextField userName;
	public JLabel register_number;
	public JLabel forget_pwd;
	public JLabel min;
	public JLabel exit;
	public JLabel mal_zhanghao;
	public JLabel setting;
	public JComboBox comboBoxState;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					//AWTUtilities.setWindowOpaque(frame, false);//设置窗体完全透明
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
	public LoginFrame() {
		setTitle("QQ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/img/QQ_64.png")));
		setUndecorated(true);//设置窗体没有边框
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 354, 272);

		contentPane = new MyPanel("/img/QQ2011_Login.png");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pwd = new JPasswordField();
		pwd.setText("123");
		pwd.setEchoChar('●');
		pwd.setBounds(104, 163, 154, 26);
		contentPane.add(pwd);

		lblQQ2013 = new JLabel("QQ");
		lblQQ2013.setForeground(new Color(0, 0, 51));
		lblQQ2013.setFont(new Font("宋体", Font.BOLD, 16));
		lblQQ2013.setBounds(14, 6, 55, 18);
		contentPane.add(lblQQ2013);

		avatar = new JLabel("");
		avatar.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/headImage/head_boy_01_64.jpg")));
		avatar.setBounds(18, 127, 64, 64);
		contentPane.add(avatar);

		remember_pwd = new JCheckBox("\u8BB0\u4F4F\u5BC6\u7801");
		remember_pwd.setBounds(156, 198, 76, 18);
		contentPane.add(remember_pwd);

		auto_login = new JCheckBox("\u81EA\u52A8\u767B\u5F55");
		auto_login.setBounds(237, 198, 76, 18);
		contentPane.add(auto_login);

		login = new JLabel("");
		login.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/button/button_login_1.png")));
		login.setBounds(262, 237, 69, 22);
		contentPane.add(login);

		userName = new JTextField();
		userName.setText("\u9A6C\u5316\u817E");
		userName.setBounds(104, 128, 154, 26);
		contentPane.add(userName);
		userName.setColumns(10);

		register_number = new JLabel("\u6CE8\u518C\u8D26\u53F7");
		register_number.setFont(new Font("SansSerif", Font.PLAIN, 13));
		register_number.setForeground(new Color(0, 51, 255));
		register_number.setBounds(288, 132, 55, 18);
		contentPane.add(register_number);

		forget_pwd = new JLabel("\u5FD8\u8BB0\u5BC6\u7801");
		forget_pwd.setFont(new Font("SansSerif", Font.PLAIN, 13));
		forget_pwd.setForeground(new Color(0, 51, 255));
		forget_pwd.setBounds(288, 167, 55, 18);
		contentPane.add(forget_pwd);

		min = new JLabel("");
		min.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/button/login_minsize_1.png")));
		min.setBounds(284, 0, 29, 19);
		contentPane.add(min);

		exit = new JLabel("");
		exit.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/button/login_exit_1.png")));
		exit.setBounds(312, -1, 37, 20);
		contentPane.add(exit);

		mal_zhanghao = new JLabel("");
		mal_zhanghao.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/button/login_duozhanghao_1.png")));
		mal_zhanghao.setBounds(14, 237, 69, 21);
		contentPane.add(mal_zhanghao);

		setting = new JLabel("");
		setting.setIcon(new ImageIcon(LoginFrame.class.getResource("/img/button/login_setting_1.png")));
		setting.setBounds(93, 237, 69, 21);
		contentPane.add(setting);

		comboBoxState = new JComboBox();
		comboBoxState.setBounds(104, 195, 40, 24);
		contentPane.add(comboBoxState);
	}
}
