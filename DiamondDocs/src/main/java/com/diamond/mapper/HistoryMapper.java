package com.diamond.mapper;

import com.diamond.pojo.Doc;
import com.diamond.pojo.History;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HistoryMapper
{
    int addHistory(History history);


}