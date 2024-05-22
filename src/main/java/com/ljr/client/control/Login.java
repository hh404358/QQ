package com.ljr.client.control;


import com.ljr.client.common.MyLabel;
import com.ljr.client.frame.LoginFrame;
import com.ljr.client.frame.RegisterFrame;
import com.ljr.client.socket.CS_TCP;
import com.ljr.common.Utils.Utils;
import com.ljr.common.constants.Constant;
import com.ljr.common.constants.Flag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
//import com.sun.awt.AWTUtilities;

//登录窗体，继承自LoginFrame
public class Login extends LoginFrame
{
	private static final long serialVersionUID = 5055331246882705423L;
	int width = 350;
	int height = 266;
	private Point lastPoint = null;//存放按下鼠标时的坐标点
	public CS_TCP cs_TCP = null;//声明一个客户端TCP
	public Main main; //声明一个主窗体
	public RegisterFrame registerFrame = null;
	
	//程序主路口
	public static void main(String[] args) throws IOException {
		new Login();
	}
	
    //登录窗体构造
	public Login() throws IOException {
		init();
	}

	//初始化
	public void init() throws IOException {
		Utils.setWindowsMiddleShow(this,width,height);//设置窗体居中显示
		new MyLabel(login, "/img/button/button_login", "png").addEvent();
		new MyLabel(min, "/img/button/login_minsize", "png").addEvent();
		new MyLabel(exit, "/img/button/login_exit", "png").addEvent();
		new MyLabel(mal_zhanghao, "/img/button/login_duozhanghao", "png").addEvent();
		new MyLabel(setting, "/img/button/login_setting", "png").addEvent();
		new MyLabel(register_number).addEvent();
		initUserStatus();
		//AWTUtilities.setWindowOpaque(this, false);//设置窗体完全透明
		addEvent();
		this.setVisible(true);
		main = new Main();
	}

	/**
	 * 初始化用户是否在线等状态
	 */
	public void initUserStatus() {
		comboBoxState.removeAllItems();
		comboBoxState.addItem(Utils.getIcon("/img/status/status_online_12.png"));
		comboBoxState.addItem(Utils.getIcon("/img/status/status_qme_12.png"));
		comboBoxState.addItem(Utils.getIcon("/img/status/status_leave_12.png"));
		comboBoxState.addItem(Utils.getIcon("/img/status/status_busy_12.png"));
		comboBoxState.addItem(Utils.getIcon("/img/status/status_invisible_12.png"));
	}


	/**
	 * 登录到服务器
	 */
	public void login() {
		String name = userName.getText();
		String password = new String(pwd.getPassword());
		if(name.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "用户名和密码不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
		}else {
			try {
				if(cs_TCP == null) {
					cs_TCP = new CS_TCP(Constant.QQServerIP, Constant.QQServerPort, this, main);
				}
				System.out.println("开始检测用户名和密码……");

				cs_TCP.sendMessage(Flag.LOGIN+Constant.FLAGEND+name+Constant.SPLIT1+password+Constant.SPLIT1+main.getServerPort()+Constant.SPLIT1+comboBoxState.getSelectedIndex());
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "连接服务器失败！请检查网络连接或确保QQ服务器已启动！");
			}
		}
	}

	/**
	 * 处理窗体的鼠标按下事件
	 * @param e
	 */
	public void mousePress(MouseEvent e)
	{
		lastPoint = e.getLocationOnScreen();
	}
	/**
	 * 处理窗体的拖拽事件
	 * @param e
	 */
	public void mouseDrag(MouseEvent e) {
		Point point = e.getLocationOnScreen();
		int offsetX = point.x - lastPoint.x;
		int offsetY = point.y - lastPoint.y;
		Rectangle bounds = this.getBounds();
		bounds.x += offsetX;
		bounds.y += offsetY;
		this.setBounds(bounds);
		lastPoint = point;
	}

	/**
	 * 给窗体添加事件
	 */
	public void addEvent() {
		login.addMouseListener(new MouseAdapter() {
			//登录按钮的单击事件
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});
		this.addMouseListener(new MouseAdapter() {
			//窗体的鼠标按下事件
			@Override
			public void mousePressed(MouseEvent e) {
				mousePress(e);
			}
		});
		this.addMouseMotionListener(new MouseAdapter()
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				mouseDrag(e);
			};
		});
		exit.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				System.exit(0);
			}
		});
		min.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, "功能暂未实现！敬请期待！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mal_zhanghao.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, "功能暂未实现！敬请期待！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		setting.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				JOptionPane.showMessageDialog(null, "功能暂未实现！敬请期待！", "温馨提示", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		register_number.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				new Register();
			}
		});
	}
}
