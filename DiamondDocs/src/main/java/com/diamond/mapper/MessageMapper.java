package com.diamond.mapper;

import com.diamond.dto.TeamMessage;
import com.diamond.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    int addMessage(Message message);

    List<TeamMessage> getInvitationMsg(@Param("userID") String userID);

    List<TeamMessage> getApplicationMsg(@Param("userID") String userID);

    int deleteMessage(@Param("msgID") String msgID);

}
