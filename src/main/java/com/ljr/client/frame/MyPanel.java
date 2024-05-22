package com.ljr.client.frame;

import com.ljr.common.Utils.Utils;

import javax.swing.*;
import java.awt.*;
 

public class MyPanel extends JPanel{
	private Image image = null;
	public MyPanel(String imagePath){
		this.setOpaque(false);
		image = Utils.getIcon(imagePath).getImage();
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
