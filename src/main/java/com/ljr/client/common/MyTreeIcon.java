package com.ljr.client.common;

import com.ljr.common.Utils.Utils;
import com.ljr.common.constants.Constant;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.ArrayList;


public class MyTreeIcon extends DefaultTreeCellRenderer {
	//如果是：用户名;图片路径，那么就是好友节点
	ArrayList<String> nodeImages = null;

    public MyTreeIcon(ArrayList<String> nodeImages)
    {
    	this.nodeImages = nodeImages;
    }

    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        Integer row,
                        boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,expanded,
        		leaf, row, hasFocus);
        setForeground(SystemColor.BLACK);// 设置文字的颜色
        setBackgroundSelectionColor(SystemColor.CYAN);// 设置选中时的背景色
        setBackgroundNonSelectionColor(SystemColor.controlHighlight);// 设置没选中时的背景色
        for(String str:nodeImages) {
        	String[] temp = str.split(Constant.SPLIT1);
        	if(value.toString().startsWith(temp[0])&&!temp[0].equals("")){
        		 try {
					if(temp.length == 2)//如果是：用户名;图片路径，那么就是好友节点
	        			//this.setIcon(new ImageIcon(grayImage));
						this.setIcon(Utils.getIcon(temp[1]));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
        		
        	}
        }
        return this;
    }
}

