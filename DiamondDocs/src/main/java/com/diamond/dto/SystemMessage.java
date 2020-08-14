package com.diamond.dto;

import lombok.Data;

@Data
public class SystemMessage
{
    String msgID;
    int msgType;
    String name;
    String createTime;
    int isRead;
}
