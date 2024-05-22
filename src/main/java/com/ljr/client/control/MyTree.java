package com.ljr.client.control;

import com.ljr.client.common.MyTreeIcon;
import com.ljr.common.constants.Constant;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MyTree
{
	private JTree tree;//存放好友列表的树
	private DefaultTreeModel treeModel;//好友树的数据
	private String[] groupNames = null;//好友分组信息
	ArrayList<String> nodeImages;//存放节点的头像，示例：张三;02_100.jpg
	private ArrayList<String[]> friendInfos=new ArrayList<String[]>();//每个分组的好友信息
	//建树构造
	public MyTree(JTree tree,String[] groupNames,ArrayList<String[]> friendInfos){
		this.tree = tree;
		this.groupNames = groupNames;
		this.friendInfos = friendInfos;
		init();//初始化
		addEvent();//添加事件
	}
	//建树构造
	public MyTree(JTree tree){
		this.tree = tree;
		treeModel = (DefaultTreeModel) tree.getModel();
	}
	
	//初始化
	public void init(){
		tree.setRootVisible(false);//设置根节点不可见
        tree.setAutoscrolls(true);//设置自动滚动
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);//设置单选模式
        tree.setBackground(SystemColor.controlHighlight);//设置背景
        
        nodeImages = new ArrayList<String>();
        nodeImages.add("所有在线用户");
        nodeImages.add("所有不在线用户");
        nodeImages.add("我的好友");

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        treeModel = new DefaultTreeModel(root);//新建一个用于存放好友树的Model
        
        for(int i = 0;i < groupNames.length;i++) {
        	DefaultMutableTreeNode node = new DefaultMutableTreeNode(groupNames[i]);
        
        	for(int j = 0;j < friendInfos.get(i).length; j++) {
        		String[] temp = friendInfos.get(i)[j].split(Constant.SPLIT3);
        		String friendName = temp[0];//好友用户名
        		String ip = temp[1];//好友IP地址
        		String port = temp[2];//好友的端口号
        		String headImage = temp[3];//好友头像
        		String state = temp[4];//状态
        		nodeImages.add(friendName+ Constant.SPLIT1 + "/img/headImage/small/" + headImage + "_32.jpg");
        		node.add(new DefaultMutableTreeNode(friendName+"("+ip+":"+port+")"));
        	}
        	root.add(node);
        }
        tree.setCellRenderer(new MyTreeIcon(nodeImages));
        tree.setModel(treeModel);  
	}
	
	public void setNodeImage()
	{
        
	}
	
	//添加事件
	public void addEvent(){
		tree.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				int selRow = tree.getRowForLocation(e.getX(), e.getY());
		         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
		         if(selRow != -1) {
		        	 //selPath的结果示例：“root,我的好友”
		        	 if(selPath.toString().split(",").length>2)
		        	 {
		        	 }
		        	 
		         }
		         
			}
		
		});
	}

	//添加好友分组到树
	public void addGroupToTree(String groupName){
		DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(groupName);
		DefaultMutableTreeNode parent=(DefaultMutableTreeNode) treeModel.getRoot();
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
	}
	/**
	 * 添加新的好友到指定的分组
	 * @param groupName 分组
	 * @param friendName 要添加的好友的名字
	 */
	public  void addFriendToTree(String groupName,String friendName){
		int groupIndex = treeModel.getIndexOfChild((DefaultMutableTreeNode)treeModel.getRoot(), new DefaultMutableTreeNode(groupName));
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(friendName);
		if(groupIndex != -1){
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode) treeModel.getChild(treeModel.getRoot(), groupIndex);
			treeModel.insertNodeInto(childNode, parent, parent.getChildCount());
		}
		else {
			System.out.println("好友组“" + groupName + "”不存在！");
		}
		 	
	}
	
	//删除好友
	public void deleteFriend(String groupName,String friendName) {
		DefaultMutableTreeNode root=(DefaultMutableTreeNode) treeModel.getRoot();
		for(int i = 0;i < root.getChildCount(); i++){
			if(root.getChildAt(i).toString().startsWith(groupName)){
				for(int j = 0;j < root.getChildAt(i).getChildCount();j++){
					if(root.getChildAt(i).getChildAt(j).toString().startsWith(friendName)){
						System.out.println(root.getChildAt(i).getChildAt(j));
						DefaultMutableTreeNode node=(DefaultMutableTreeNode)root.getChildAt(i).getChildAt(j);
						node.removeFromParent();
						System.out.println("删除成功！");
					}
					break;
				}
			}
			break;
		}
	}
	
	//打开好友分组
	public void openGroup(String groupName){
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(groupName);
		tree.scrollPathToVisible(new TreePath(childNode.getPath()));//自动打开到当前节点
	}
}
