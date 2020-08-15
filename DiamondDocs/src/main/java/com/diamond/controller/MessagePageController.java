package com.diamond.controller;

import com.diamond.dto.CommentMessage;
import com.diamond.dto.SystemMessage;
import com.diamond.dto.TeamMessage;
import com.diamond.mapper.MessageMapper;
import com.diamond.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class MessagePageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/getCommonMsg")
    public List<TeamMessage> getCommonMsg(@RequestParam("type") int type, @RequestParam("userID") String userID)
    {
        try {
            return messageService.getCommonMsg(type, userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getCommentMsg")
    public List<CommentMessage> getCommentMsg(@RequestParam("userID") String userID)
    {
        try {
            return messageService.getCommentMsg(userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getSystemMsg")
    public List<SystemMessage> getSystemMsg(@RequestParam("userID") String userID)
    {
        try {
            return messageService.getSystemMsg(userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getMsgNum")
    public int getMsgNum(@RequestParam("userID") String userID)
    {
        try {
            return messageService.getMsgNum(userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping("/getInvitationMsgNum")
    public int getInvitationMsgNum(@RequestParam("userID") String userID)
    {
        try {
            return messageService.getInvitationMsgNum(userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping("/getApplicationMsgNum")
    public int getApplicationMsgNum(@RequestParam("userID") String userID)
    {
        try {
            return messageService.getApplicationMsgNum(userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping("/getReplyMsgNum")
    public int getReplyMsgNum(@RequestParam("userID") String userID)
    {
        try {
            return messageService.getReplyMsgNum(userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping("/getSystemMsgNum")
    public int getSystemMsgNum(@RequestParam("userID") String userID)
    {
        try {
            return messageService.getSystemMsgNum(userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping("/deleteMsg")
    public int deleteMsg(@RequestParam("msgID") String msgID)
    {
        try {
            messageService.deleteMsg(msgID);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

}
