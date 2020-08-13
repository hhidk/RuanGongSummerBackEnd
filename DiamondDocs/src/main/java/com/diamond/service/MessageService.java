package com.diamond.service;

import com.diamond.dto.TeamMessage;
import com.diamond.mapper.MessageMapper;
import com.diamond.mapper.TeamMapper;
import com.diamond.pojo.Message;
import com.diamond.utils.DiyUUID;
import com.diamond.utils.FormatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private TeamMapper teamMapper;

    public void applyTeam(String userID, String targetTeamID, String content) throws Exception
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent(content);
        message.setMsgType(1);
        message.setTeamID(targetTeamID);
        message.setUserID(userID);
        message.setTargetUserID(teamMapper.getCreatorIDByTeamID(targetTeamID));

        messageMapper.addMessage(message);
    }

    public void acceptMember(String userID, String targetUserID, String teamID)
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(3);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);
    }

    public void refuseMember(String userID, String targetUserID, String teamID)
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(4);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);
    }

    public void inviteMember(String userID, String teamID, String targetUserID, String content)
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent(content);
        message.setMsgType(0);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);
    }

    public void joinTeam(String userID, String targetUserID, String teamID)
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(5);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);
    }

    public void refuseTeam(String userID, String targetUserID, String teamID)
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(6);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);
    }

    public void quitTeam(String userID, String teamID)
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(7);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(teamMapper.getCreatorIDByTeamID(teamID));

        messageMapper.addMessage(message);
    }

    public void dismissMember(String userID, String targetUserID, String teamID)
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(8);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);
    }

    public List<TeamMessage> getCommonMsg(int type, String userID) throws Exception
    {
        List<TeamMessage> list = new ArrayList<>();
        if(type == 0)
        {
            list = messageMapper.getInvitationMsg(userID);
            for (TeamMessage teamMessage : list)
            {
                teamMessage.setCreateTime(FormatHandler.AlterTimeFormat(teamMessage.getCreateTime()));
            }
        }
        else if(type == 1)
        {
            list = messageMapper.getApplicationMsg(userID);
            for (TeamMessage teamMessage : list)
            {
                teamMessage.setCreateTime(FormatHandler.AlterTimeFormat(teamMessage.getCreateTime()));
            }
        }
        return list;
    }

    public void deleteMsg(String msgID) throws Exception
    {
        messageMapper.deleteMessage(msgID);
    }

}
