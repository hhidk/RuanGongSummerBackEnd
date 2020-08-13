package com.diamond.service;

import com.diamond.dto.TeamMessage;
import com.diamond.mapper.MessageMapper;
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
            list =  messageMapper.getInvitationMsg(userID);
        else if(type == 1)
            list =  messageMapper.getApplicationMsg(userID);
        return list;
    }

    public void deleteMsg(String msgID) throws Exception
    {
        messageMapper.deleteMessage(msgID);
    }

}
