package com.ljr.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * 用户表的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users
{
    private Integer id;//主键
    private String name;//姓名
    private String pwd;//密码
    private String ip;//IP地址
    private String state;//状态，-1或空表示不在线，0表示在线，1表示Q我，2表示离开，3表示忙碌，4表示隐身
    private String gender;//性别
    private String email;//邮箱
    private String lastLogin;//最后一次登录
    private String lastExit;//最后一次退出
    private String remarke;//备用
    private String signature;//个性签名
    private String headImg;//头像
    private String type;//类型
    private Date birthday;//生日

    /**
     * @param id	      	用户ID
     * @param name			用户名
     * @param pwd			用户密码
     * @param gender		用户性别
     * @param email			用户e-mail
     * @param remarke		备注
     * @param signature		用户签名
     * @param headImg		用户头像
     * @param birthday		用户生日
     * @param type			用户类型（管理员/普通用户）
     */
    public Users(int id, String name, String pwd, String gender, String email, String remarke,

                 String signature, String headImg, Date birthday,String type,String ip,String state) {
        super();
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.gender = gender;
        this.email = email;
        this.remarke = remarke;
        this.signature = signature;
        this.headImg = headImg;
        this.birthday = birthday;
        this.type = type;
        this.ip = ip;
        this.state = state;
    }
    /**
     * 无用户ID的构造方法
     * @param name
     * @param pwd
     * @param gender
     * @param email
     * @param signature
     * @param headImg
     * @param birthday
     */

    public Users(String name, String pwd, String gender, String email,
                 String signature, String headImg, Date birthday) {
        super();
        this.name = name;
        this.pwd = pwd;
        this.gender = gender;
        this.email = email;
        this.signature = signature;
        this.headImg = headImg;
        this.birthday = birthday;
        this.state=-1+"";//将用户默认状态设为-1表示不在线
    }

    public Users(int id, String name, String pwd, String gender, String email,
                 Date birthday) {
        super();
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.gender = gender;
        this.email = email;
        this.birthday = birthday;
    }

}

