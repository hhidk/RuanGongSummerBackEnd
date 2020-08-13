package com.diamond.dto;

import com.diamond.pojo.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamMessage {
    String msgID;
    String userID;
    String teamID;
    String userName;
    String imagePath;
    String teamName;
    String msgContent;
    String createTime;
}
