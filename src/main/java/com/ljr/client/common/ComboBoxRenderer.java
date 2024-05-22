package com.ljr.client.common;

import javax.swing.*;
import java.awt.*;

public class ComboBoxRenderer extends JLabel implements ListCellRenderer {
	private Font uhOhFont;
	private ImageIcon[] images = null;
	private String[] imageNames = null;
	public ComboBoxRenderer(ImageIcon[] images,String[] imageNames) {
		setOpaque(true);
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		this.imageNames=imageNames;
		this.images=images;
	}

	/*
	 * 此方法查找与所选值相对应的图像和文本
	 * 并返回标签，设置为显示文本和图像。
	 */
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus)
	{
		int selectedIndex = ((Integer) value).intValue();
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		// 设置图标和文本。如果图标为空，返回没有图片
		ImageIcon icon = images[selectedIndex];
		String pet = imageNames[selectedIndex];
		setIcon(icon);
		if (icon != null){
			setText(pet);
			setFont(list.getFont());
		}
		else{
			setUhOhText(pet + " (没有有效的图片)", list.getFont());
		}

		return this;
	}

	// 设置字体和文本时，没有找到图像
	protected void setUhOhText(String uhOhText, Font normalFont){
		if (uhOhFont == null){
			// 创建这个字体
			uhOhFont = normalFont.deriveFont(Font.ITALIC);
		}
		setFont(uhOhFont);
		setText(uhOhText);
	}
}
