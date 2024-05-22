package com.ljr.common.Utils;

import com.ljr.client.control.Main;
import com.ljr.common.constants.Flag;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Utils {
    /**
     * 将窗体居中显示
     * @param frame 需要居中显示的窗体
     */
    public static void setWindowsMiddleShow(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2, frame.getWidth(), frame.getHeight());
    }

    /**
     * 将窗体居中显示
     * @param frame 需要居中显示的窗体
     * @param width 窗体的宽度
     * @param height 窗体的高度
     */
    public static void setWindowsMiddleShow(JFrame frame,int width,int height) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
    }

    /**
     * 更换皮肤
     */
    public static void changeSkin() {
        try {
            UIManager.put("control", new Color(215, 255, 255));//控件背景色
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据文件路径获取图片
     * @param path 路径
     * @return 返回获取的图片
     */
    public static ImageIcon getIcon(String path){
        try{
            return new ImageIcon(ImageIO.read(Main.class.getResource(path)));
        }
        catch (IOException e){
            System.out.println("图片：" + path + "不存在！");
            return null;
        }
    }

    /**
     * 将字符串转成Flag类型的枚举
     * @param str 需要转的字符串
     * @return 返回转换后的Flag枚举
     */
    public static Flag stringToFlagEnum(String str)
    {
        return Enum.valueOf(Flag.class, str);
    }

    public static String getFaceByIdx(int idx){
        String fileName = "";
        if(idx < 10){
            fileName = "/img/face/f00" + idx + ".png";//修改图片路径
        }
        else{
            fileName = "/img/face/f0" + idx + ".png";
        }
        return fileName;
    }
}
