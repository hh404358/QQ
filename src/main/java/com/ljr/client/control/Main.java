package com.ljr.client.control;

import com.ljr.client.frame.MainFrame;
import com.ljr.client.frame.personalMana;
import com.ljr.client.socket.CS_TCP;
import com.ljr.client.socket.S_TCP;
import com.ljr.common.Utils.Utils;
import com.ljr.common.constants.Constant;
import com.ljr.common.constants.Flag;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends MainFrame {
	public CS_TCP cs_TCP = null;
	public S_TCP s_TCP = null;
	private Integer serverPort = 5000;//端口从5000开始分配
	final Integer width = 350;//窗体宽度
	final Integer height = 595;//窗体高度
	public QunChat qunChat;//群聊天室
	public personalMana pers;//个人管理
	
	public static void main(String[] args) throws IOException {
		new Main().setVisible(true);
	}
	
	//空参构造，初始化main对象
	public Main() throws IOException {
		getContentPane().setBackground(SystemColor.controlHighlight);
		tree.setBackground(SystemColor.controlHighlight);     
		init();
	}
	
	//客户端初始化main 对象
	public Main(CS_TCP cs_TCP) throws IOException {
		this.cs_TCP = cs_TCP;
		init();
	}
	
	//初始化主窗体
	public void init() throws IOException {
		Utils.setWindowsMiddleShow(this,width,height);//设置窗体居中显示
		initUserStatus();//初始化用户是否在线等状态
		s_TCP = new S_TCP(serverPort,this);
	}
	
	//当登录成功后，将登录窗体的CS_TCP传给主窗体
	public void setCS_TCP(CS_TCP cs_TCP){
		this.cs_TCP = cs_TCP;
	}
	public Integer getServerPort()
	{
		return s_TCP.getServerPort();
	}
	
	//初始化好友状态
	public void initUserStatus() {
		comboBoxState.removeAllItems();
		comboBoxState.addItem(Utils.getIcon("/img/status/status_online.png"));
		comboBoxState.addItem(Utils.getIcon("/img/status/status_qme.png"));
		comboBoxState.addItem(Utils.getIcon("/img/status/status_leave.png"));
		comboBoxState.addItem(Utils.getIcon("/img/status/status_busy.png"));
		comboBoxState.addItem(Utils.getIcon("/img/status/status_invisible.png"));
	}
	
	//初始化好友列表
	public void initjTree(String[] groupNames,ArrayList<String[]> friendNames){
		new MyTree(tree, groupNames, friendNames);
	}

	//开始与好友聊天
	@Override
	public void startChat(ActionEvent e) throws IOException {
		if(Objects.requireNonNull(tree.getSelectionPath()).getPathCount() == 3){
			String str = tree.getSelectionPath().getLastPathComponent().toString();
			String friendName = str.substring(0,str.indexOf("("));
			String friendIP = str.substring(str.indexOf("(")+1,str.indexOf(":"));
			int friendPort = Integer.parseInt(str.substring(str.indexOf(":")+1,str.indexOf(")")));
			if(!friendIP.equals("下线或隐身")){
				//发送在线消息
				Chat chat = new Chat(friendIP,friendPort,friendName,this.userName.getText());
				chat.setVisible(true);
			}
			else{
				//发送离线消息
				Chat chat = new Chat(cs_TCP, friendName);
				chat.setVisible(true);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "对不起，您未选中任何好友！");
		}
	}
	
	//获取好友资料
	@Override
	public void getFriendInfo(ActionEvent e) {
		String str = tree.getSelectionPath().getLastPathComponent().toString();
		String userName = str.substring(0,str.indexOf("("));
		//通过传输到服务器，服务器进行解析返回相应的列表
		cs_TCP.sendMessage(Flag.GET_FRIEND_INFO + Constant.FLAGEND + userName);
	}
	
	//用户管理
	@Override
	public void changeInfomation(ActionEvent e) throws IOException {
		pers = new personalMana(this);
	}
	
	//发送文件
	@Override
	public void sendFile(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "功能暂未实现！");
	}
	
	//删除好友
	public void deleteFriend(ActionEvent e){
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		node.removeFromParent();
		JOptionPane.showMessageDialog(null, "删除好友成功！请关闭并打开当前分组以刷新好友列表！");
	}
	
	//进入聊天室
	@Override
	public void gotoChatRoom() {
		qunChat = new QunChat(this);
	}
	
	//创建聊天室
	@Override
	public void buildNewChatRoom(){
		JOptionPane.showMessageDialog(null, "您还不是会员，是否想要加入会员，仅需10元每月哦！","提示",JOptionPane.YES_NO_CANCEL_OPTION);
	}
}
