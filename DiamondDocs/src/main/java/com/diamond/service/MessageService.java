package com.diamond.service;

import com.diamond.dto.CommentMessage;
import com.diamond.dto.SystemMessage;
import com.diamond.dto.TeamMessage;
import com.diamond.mapper.*;
import com.diamond.pojo.Doc;
import com.diamond.pojo.Message;
import com.diamond.utils.DiyUUID;
import com.diamond.utils.FormatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private DocUserMapper docUserMapper;
    @Autowired
    private DocMapper docMapper;
    @Autowired
    private CommentMapper commentMapper;

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

    public void addComment(String docID, String userID, String commentContent, String replyID) throws Exception
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        if(replyID.equals("0"))
        {
            message.setMsgType(9);
            message.setTeamID(docID);
            message.setTargetUserID(docMapper.getCreatorID(docID));
        }
        else
        {
            message.setMsgType(10);
            message.setTeamID(replyID);
            message.setTargetUserID(commentMapper.getCommentByCommentID(replyID).getUserID());
        }
        message.setMsgContent(commentContent);
        message.setUserID(userID);

        messageMapper.addMessage(message);
    }

    public void acceptMember(String userID, String targetUserID, String teamID, String msgID) throws Exception
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(3);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);

        Map<String, Object> map = new HashMap<>();
        map.put("msgID", msgID);
        map.put("type", 2);
        messageMapper.setMsgReadState(map);
    }

    public void refuseMember(String userID, String targetUserID, String teamID, String msgID) throws Exception
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(4);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);

        Map<String, Object> map = new HashMap<>();
        map.put("msgID", msgID);
        map.put("type", 3);
        messageMapper.setMsgReadState(map);
    }

    public void inviteMember(String userID, String teamID, String targetUserID, String content) throws Exception
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

    public void joinTeam(String userID, String targetUserID, String teamID, String msgID) throws Exception
    {
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(5);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);

        Map<String, Object> map = new HashMap<>();
        map.put("msgID", msgID);
        map.put("type", 2);
        messageMapper.setMsgReadState(map);
    }

    public void refuseTeam(String userID, String targetUserID, String teamID, String msgID) throws Exception
    {
        System.out.println(msgID);
        Message message = new Message();
        message.setMsgID(DiyUUID.generateMsgID());
        message.setMsgContent("");
        message.setMsgType(6);
        message.setTeamID(teamID);
        message.setUserID(userID);
        message.setTargetUserID(targetUserID);

        messageMapper.addMessage(message);

        Map<String, Object> map = new HashMap<>();
        map.put("msgID", msgID);
        map.put("type", 3);
        messageMapper.setMsgReadState(map);
    }

    public void quitTeam(String userID, String teamID) throws Exception
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

    public void dismissMember(String userID, String targetUserID, String teamID) throws Exception
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
        }
        else if(type == 1)
        {
            list = messageMapper.getApplicationMsg(userID);
        }
        for (TeamMessage teamMessage : list)
        {
            teamMessage.setCreateTime(FormatHandler.AlterTimeFormat(teamMessage.getCreateTime()));
            if(teamMessage.getIsRead() == 0)
            {
                Map<String, Object> map = new HashMap<>();
                map.put("msgID", teamMessage.getMsgID());
                map.put("type", 1);
                messageMapper.setMsgReadState(map);
            }
        }
        return list;
    }

    public List<CommentMessage> getCommentMsg(String userID) throws Exception
    {
        List<CommentMessage> list = messageMapper.getCommentMsg(userID);
        for (CommentMessage commentMessage : list)
        {
            commentMessage.setCreateTime(FormatHandler.AlterTimeFormat(commentMessage.getCreateTime()));
            if (commentMessage.getMsgType() == 9)
            {
                commentMessage.setOriginalContent(docMapper.getDocByDocID(commentMessage.getID()).getDocTitle());
            }
            else if (commentMessage.getMsgType() == 10)
            {
                commentMessage.setOriginalContent(commentMapper.getCommentByCommentID(commentMessage.getID()).getCommentContent());
            }

            if(commentMessage.getIsRead() == 0)
            {
                Map<String, Object> map = new HashMap<>();
                map.put("msgID", commentMessage.getMsgID());
                map.put("type", 1);
                messageMapper.setMsgReadState(map);
            }
        }
        return list;
    }

    public List<SystemMessage> getSystemMsg(String userID) throws Exception
    {
        List<SystemMessage> list = new ArrayList<>();
        list = messageMapper.getSystemMsg(userID);
        for (SystemMessage systemMessage : list)
        {
            systemMessage.setCreateTime(FormatHandler.AlterTimeFormat(systemMessage.getCreateTime()));
            if (systemMessage.getMsgType() == 8 || systemMessage.getMsgType() == 3 || systemMessage.getMsgType() == 4)
            {
                systemMessage.setName(teamMapper.getTeamByTeamID(messageMapper.getMsgByMsgID(systemMessage.getMsgID()).getTeamID()).getTeamName());
            }
            else if (systemMessage.getMsgType() == 11)
            {
                systemMessage.setName(docMapper.getDocByDocID(messageMapper.getMsgByMsgID(systemMessage.getMsgID()).getTeamID()).getDocTitle());
            }
            else
            {
                systemMessage.setName(docUserMapper.getUserByID(messageMapper.getMsgByMsgID(systemMessage.getMsgID()).getUserID()).getUserName());
            }

            if(systemMessage.getIsRead() == 0)
            {
                Map<String, Object> map = new HashMap<>();
                map.put("msgID", systemMessage.getMsgID());
                map.put("type", 1);
                messageMapper.setMsgReadState(map);
            }
        }
        return list;
    }

    public int getMsgNum(String userID)
    {
        return messageMapper.getMsgNum(userID);
    }

    public int getInvitationMsgNum(String userID)
    {
        return messageMapper.getInvitationMsgNum(userID);
    }

    public int getApplicationMsgNum(String userID)
    {
        return messageMapper.getApplicationMsgNum(userID);
    }

    public int getReplyMsgNum(String userID)
    {
        return messageMapper.getReplyMsgNum(userID);
    }

    public int getSystemMsgNum(String userID)
    {
        return messageMapper.getSystemMsgNum(userID);
    }

    public void deleteMsg(String msgID) throws Exception
    {
        messageMapper.deleteMessage(msgID);
    }

}
