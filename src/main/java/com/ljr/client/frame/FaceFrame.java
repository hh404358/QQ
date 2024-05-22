package com.ljr.client.frame;

import com.ljr.client.common.MyLabel;
import com.ljr.client.control.Chat;
import com.ljr.client.control.QunChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class FaceFrame extends JWindow
{

	private JPanel contentPane;

	GridLayout gridLayout1 = new GridLayout();
	public static JLabel[] ico = new JLabel[90];
	Chat chat;
	QunChat qunChat;
	int width = 720;
	int height = 288;

	public FaceFrame(Chat chat){
		super(chat);
		this.chat = chat;
		try{
			jbInit();
			int left = chat.getLocation().x + 20;
			int top = chat.getLocation().y + chat.getHeight() -  200 -  height;
			this.setBounds(left, top, width, height);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
		} catch (Exception exception){
			exception.printStackTrace();
		}
	}
	public FaceFrame(QunChat chat){
		super(chat);
		this.qunChat = chat;
		try{
			jbInit();
			int left = chat.getLocation().x + 20;
			int top = chat.getLocation().y + chat.getHeight() -  200 -  height;
			this.setBounds(left, top, width, height);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
		} catch (Exception exception){
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception{
		getContentPane().setLayout(gridLayout1);
		gridLayout1.setColumns(15);
		gridLayout1.setHgap(1);
		gridLayout1.setRows(6);
		gridLayout1.setVgap(1);
		String fileName = " ";
		for (int i = 0; i < ico.length; i++ ) {
			if(i < 10){
        		fileName = "/img/face/f00" + i + ".png";//图片路径
        	}
        	else{
        		fileName = "/img/face/f0" + i + ".png";
			}
			ico[i] = new   JLabel(new   ImageIcon(FaceFrame.class.getResource(fileName)));
			ico[i].setToolTipText("点此添加QQ表情哟^_^");
			new MyLabel(ico[i],Color.black).addEvent();
			final Icon img = ico[i].getIcon();
			final Integer count = i;
			ico[i].addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					chat.sendPane.insertIcon(img);
					chat.faceIdx = count;
					getObj().dispose();// 单例模式
				}
			});
			this.getContentPane().add(ico[i]);
		}
		this.getContentPane().setBackground(SystemColor.text);
	}

	private JWindow getObj()
	{
		return this;
	}

}
