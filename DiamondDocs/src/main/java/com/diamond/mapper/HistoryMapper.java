package com.diamond.mapper;

import com.diamond.dto.DocUserPreview;
import com.diamond.dto.HistoryPlus;
import com.diamond.pojo.Doc;
import com.diamond.pojo.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HistoryMapper
{
    int addHistory(History history);

    int getLastHistory(@Param("docID") String docID);

    List<DocUserPreview> getDocCollaborator(@Param("docID") String docID);

    List<HistoryPlus> getDocHistory(@Param("docID") String docID);

    int getDocNum(@Param("userID") String userID);
}
