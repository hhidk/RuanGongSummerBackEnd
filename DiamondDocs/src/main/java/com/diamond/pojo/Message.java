package com.diamond.pojo;

import lombok.Data;

@Data
public class Message
{
    String msgID;
    String userID;
    String targetUserID;
    String msgContent;
    String createTime;
    String teamID;
    int msgType;
}
