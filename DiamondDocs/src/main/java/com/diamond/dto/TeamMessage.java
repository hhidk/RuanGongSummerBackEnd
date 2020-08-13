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
    String userImagePath;
    String teamName;//前端
    String content;//前端
    String createTime;
}
