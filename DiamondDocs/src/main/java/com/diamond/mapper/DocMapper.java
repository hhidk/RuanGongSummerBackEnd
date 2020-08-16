package com.diamond.mapper;

import com.diamond.dto.DocPlus;
import com.diamond.pojo.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DocMapper
{

    int addDoc(Doc doc);

    Doc getDocByDocID(@Param("docID") String docID);

    List<Doc> getDocByDocTitle(@Param("docTitle") String docTitle);

    //key:userID, keyword
    List<Doc> getRelatedDocByUserID(Map<String, Object> map);

    List<Doc> getDocByTeamID(@Param("teamID") String teamID);

    List<Doc> getDocByUserID(@Param("userID") String userID);

    List<Doc> getDeletedDocByUserID(@Param("userID") String userID);

    int getDocDeleteState(@Param("docID") String docID);

    /*
    key:docID, docContent
     */
    int updateDoc(Map<String, Object> map);

    int updateDocTitle(@Param("docID") String docID, @Param("docTitle") String docTitle);

    int movDocToCycleBin(@Param("docID") String docID);

    int recoverDoc(@Param("docID") String docID);

    int deleteDoc(@Param("docID") String docID);
}
