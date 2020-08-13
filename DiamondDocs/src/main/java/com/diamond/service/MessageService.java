package com.diamond.service;

import com.diamond.dto.TeamMessage;
import com.diamond.mapper.MessageMapper;
import com.diamond.utils.FormatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

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
