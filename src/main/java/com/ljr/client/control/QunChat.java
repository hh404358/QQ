package com.ljr.client.control;

import com.ljr.client.common.MyTextPane;
import com.ljr.client.frame.FaceFrame;
import com.ljr.client.frame.QunChatFrame;
import com.ljr.client.frame.ScreenFrame;
import com.ljr.client.socket.CS_TCP;
import com.ljr.common.Utils.Utils;
import com.ljr.common.constants.Constant;
import com.ljr.common.constants.Flag;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Date;
public class QunChat extends QunChatFrame
{
	CS_TCP cs_TCP = null;
	Main main = null;
	String screenCutImgName = "";

	public QunChat(Main main) {
		this.main = main;
		this.tree.setModel(main.tree.getModel());
		this.tree.setCellRenderer(main.tree.getCellRenderer());
		this.cs_TCP = main.cs_TCP;
		Utils.setWindowsMiddleShow(this);
		this.setTitle("MyQQ官方聊天室(当前用户："+main.userName.getText()+")");
		addEvent();
		this.setVisible(true);
	}
	public void addEvent() {
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nowTime =  DateFormat.getTimeInstance().format(new Date());
				cs_TCP.sendMessage(Flag.QUN_CHAT + Constant.FLAGEND + main.userName.getText() + Constant.SPLIT1 + nowTime + Constant.SPLIT1 + sendPane.getText());
				sendPane.setText("");
			}
		});
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();//关闭聊天室
			}
		});


	}
	public void receiveMessage(String message) {
		String[] temp = message.split(Constant.SPLIT1);
		if(temp[0].equals(main.userName.getText())) {
			new MyTextPane(recieveBoard).addText(temp[0]+" "+temp[1]+"\n", MyTextPane.getTimeAttribute());
			new MyTextPane(recieveBoard).addText(temp[2]+"\n", MyTextPane.getMyAttribute());
		}
		else {
			new MyTextPane(recieveBoard).addText(temp[0]+" "+temp[1]+"\n", MyTextPane.getTimeAttribute());
			new MyTextPane(recieveBoard).addText(temp[2]+"\n", MyTextPane.getFriendAttribute());
		}
	}
	public void showPublicMessage(String message) {
		groupBoard.setText(message);
	}

	@Override
	public void selectFace() {
		new FaceFrame(this);
	}

	/*
	 * 发送图片
	 */
	@Override
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
                return fileName.endsWith(".jpg") || fileName.endsWith(".png")
                        || fileName.endsWith(".bmp");
            }
		});

		int result  =  chooser.showOpenDialog(null);

		// 选择打开时
		if (result  ==  JFileChooser.APPROVE_OPTION)
		{
			String filePath  =  chooser.getSelectedFile().getAbsolutePath();
			// 给图片添加；路径
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

			System.out.println("截图的名字是" + screenCutImgName);
			sendPane.insertIcon(new ImageIcon(ImageIO
					.read(Files.newInputStream(Paths.get("./screenCut/snap.jpg")))));
			screenCutImgName = "snap";
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}