package com.diamond.dto;

import lombok.Data;

@Data
public class CommentMessage
{
    String msgID;
    String userID;
    String userName;
    String imagePath;
    String createTime;
    String ID;
    String content;
    int msgType;
    int isRead;
}
