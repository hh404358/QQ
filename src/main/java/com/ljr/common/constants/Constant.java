package com.ljr.common.constants;

public class Constant {
    //下面三句话定义一些标志，示例如下：
    //login#马化腾;1234567
    //friends_info#我的好友,柳夏南,王林,吴志强;陌生人,胡锦涛,陈水扁
    public static final String FLAGEND = "#";//定义最当头标志结束的字符
    public static final String SPLIT1 = ";";//定义一级分割字符
    public static final String SPLIT2 = ",";//定义二级分割字符
    public static final String SPLIT3 = "&";//定义三级分割字符
    
    
    public static final String EXIT = "-1";
    public static final Integer INVISIBLE = 4;
    
    
    public static final String REGISTER_SUCCESS = "恭喜你，注册成功！";
    public static final String BIRTHDAY = "生日：";
    public static final String MODIFY_SUCESS = "恭喜你，修改成功！";
    public static final String WRONG_BIRTHDAY = "\n生日日期格式不正确，系统已给您设置成默认的生日：" ;
    public static final String REGISTER_FAILED = "注册失败";
    public static final String NONE = "无";
    public static final String SEND_FILE_SUCCESS = "#文件发送完毕#";
    public static final String FAILED = "失败";
    // 数据库地址和密码
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/myqq?useUnicode=true&characterEncoding=utf-8";
    public static final String JDBC_USER = "root";
    public static final String JDBC_PWD = "liujiarong";
    public static String MAN = "男";
    public static String WOMAN = "女";

    public static final int QQServerPort = 6776; // QQ服务器端口号
    public static final String QQServerIP = "127.0.0.1"; // 服务器IP地址
}
