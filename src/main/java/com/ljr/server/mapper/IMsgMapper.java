package com.ljr.server.mapper;

import com.ljr.server.entity.Msg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface IMsgMapper {
    public boolean insertMsg(Msg msg);
    public boolean deleteMsg(int msgId);
    public Integer updateMsg(Msg msg);
    public Msg selectAMsg(int msgId);
    public List<Msg> selectMsgBySendTo(int sendTo);
    public List<Msg> selectMsgBySendFrom(int sendFrom);
    public List<Msg> selectMsgs();

    void deleteMsgBySendTO(int userID);
}
