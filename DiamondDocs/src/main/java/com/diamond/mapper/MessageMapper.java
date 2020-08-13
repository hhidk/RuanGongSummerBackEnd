package com.diamond.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MessageMapper {

    int deleteMessage(@Param("msgID") String msgID);
}
