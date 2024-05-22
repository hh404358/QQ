package com.ljr.client.frame;

import com.ljr.common.constants.Constant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterFrame extends JFrame {

	public JPanel contentPane;
	//用户名、密码、重复密码
	public JTextField txtName;
	public JPasswordField pwd;
	public JPasswordField pwdRe;
	//Email、生日、性别单选
	public JTextField txtEmail;
	public JTextField txtbirthday;
	public JComboBox comGender;
	//个性签名、头像单选
	public JTextArea txtSignat;
	public JComboBox comboBoxHeadImage;
	//注册按钮
	public JButton btnRegister;
	
	public RegisterFrame()
	{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) 
			{
				beforeClose();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterFrame.class.getResource("/img/QQ_64.png")));
		setTitle("chat");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(592, 553);
		
		contentPane = new MyPanel("/img/registerBGimg2.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbUserName = new JLabel("用户名:");
		lbUserName.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbUserName.setBounds(22, 45, 70, 24);
		contentPane.add(lbUserName);
		
		JLabel lbPwd = new JLabel("密码:");
		lbPwd.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbPwd.setBounds(38, 96, 54, 30);
		contentPane.add(lbPwd);
		
		JLabel lbPwdre = new JLabel("重复密码:");
		lbPwdre.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbPwdre.setBounds(22, 138, 79, 35);
		contentPane.add(lbPwdre);
		
		JLabel lbEmail = new JLabel("E-mail:");
		lbEmail.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbEmail.setBounds(22, 234, 70, 28);
		contentPane.add(lbEmail);
		
		JLabel lbBirthday = new JLabel("生日:");
		lbBirthday.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbBirthday.setBounds(38, 285, 46, 24);
		contentPane.add(lbBirthday);
		
		JLabel lbSignature = new JLabel("个性签名:");
		lbSignature.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbSignature.setBounds(22, 332, 79, 28);
		contentPane.add(lbSignature);
		
		btnRegister = new JButton("立即注册");
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(0, 100, 0));
		btnRegister.setFont(new Font("微软雅黑", Font.BOLD, 28));
		btnRegister.setBounds(57, 431, 161, 60);
		contentPane.add(btnRegister);
		
		JButton btnCancel = new JButton("返回");
		btnCancel.setForeground(new Color(255, 250, 250));
		btnCancel.setBackground(new Color(106, 90, 205));
		btnCancel.setFont(new Font("微软雅黑", Font.BOLD, 28));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				cancle();
			}
		});
		btnCancel.setBounds(248, 431, 100, 60);
		contentPane.add(btnCancel);
		
		txtName = new JTextField();
		txtName.setBounds(111, 40, 217, 35);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(111, 92, 217, 35);
		contentPane.add(pwd);
		
		pwdRe = new JPasswordField();
		pwdRe.setBounds(113, 138, 215, 35);
		contentPane.add(pwdRe);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(111, 231, 217, 35);
		contentPane.add(txtEmail);
		
		comGender = new JComboBox();
		comGender.setModel(new DefaultComboBoxModel(new String[] {Constant.MAN, Constant.WOMAN}));
		comGender.setBounds(111, 185, 59, 35);
		contentPane.add(comGender);
		
		JLabel lbGender = new JLabel("性别:");
		lbGender.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lbGender.setBounds(38, 190, 46, 24);
		contentPane.add(lbGender);
		
		txtSignat = new JTextArea();
		txtSignat.setBounds(111, 328, 217, 75);
		contentPane.add(txtSignat);
		
		txtbirthday = new JTextField();
		txtbirthday.setBounds(111, 281, 217, 35);
		contentPane.add(txtbirthday);
		txtbirthday.setColumns(10);
		
		comboBoxHeadImage = new JComboBox();
		comboBoxHeadImage.setBounds(375, 193, 138, 117);
		contentPane.add(comboBoxHeadImage);
		
		JLabel lblHead = new JLabel("头像选择:");
		lblHead.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblHead.setBounds(375, 139, 100, 32);
		contentPane.add(lblHead);
	}
	
	/**
	 * 在窗体关闭之前需要做的事
	 */
	public void beforeClose()
	{}
	/**
	 * 取消按钮事件
	 */
	public void cancle()
	{}
}
