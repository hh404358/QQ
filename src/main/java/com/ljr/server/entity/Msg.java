package com.ljr.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg
{
    private Integer msgId;
    private String msgContent;
    private Integer sendFrom;//信息发送者
    private Integer sendTo;//信息接收者
    private String sendTime;//信息发送时间
    private String remark;
    private String msgTye;//信息类型：离线消息和群公告

    @Override
    public String toString() {
        return "消息的全部信息是：\n"+"msgId:"+msgId+"  msgContent:"+msgContent+
                "  sendFrom:"+sendFrom+"  sendTo:"+sendTo+" sendTime :"+sendTime+
                "   remark:"+remark+"  msgTye:"+msgTye;
    }


    /**
     * @param msgContent
     * @param sendFrom
     * @param sendTo
     * @param remark
     * @param msgTye
     *
     * 除msgId 和 sendTime 参数之外  的构造参数
     */
    public Msg(String msgContent, Integer sendFrom, Integer sendTo, String remark,
               String msgTye)
    {
        super();
        this.msgContent = msgContent;
        this.sendFrom = sendFrom;
        this.sendTo = sendTo;
        this.remark = remark;
        this.msgTye = msgTye;
    }

    /**
     * @param msgId
     * @param msgContent
     * @param sendFrom
     * @param sendTo
     * @param remark
     * @param msgTye
     *
     *  含有除  sendTime参数的  构造函数
     */
    public Msg(int msgId, String msgContent, Integer sendFrom, Integer sendTo,
               String remark, String msgTye) {
        super();
        this.msgId = msgId;
        this.msgContent = msgContent;
        this.sendFrom = sendFrom;
        this.sendTo = sendTo;
        this.remark = remark;
        this.msgTye = msgTye;
    }


    /**
     * @param msgContent
     * @param sendFrom
     * @param sendTo
     * @param sendTime
     * @param remark
     * @param msgTye
     *   含有除msgId之外所有参数的构造方法
     */
    public Msg(String msgContent, Integer sendFrom, Integer sendTo, String sendTime,
               String remark, String msgTye) {
        super();
        this.msgContent = msgContent;
        this.sendFrom = sendFrom;
        this.sendTo = sendTo;
        this.sendTime = sendTime;
        this.remark = remark;
        this.msgTye = msgTye;
    }

}
