package com.diamond.mapper;

import com.diamond.pojo.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DocMapper
{

    int addDoc(Doc doc);

    Doc getDocByDocID(@Param("docID") String docID);

    List<Doc> getDocByDocTitle(@Param("docTitle") String docTitle);

    List<Doc> getDocByTeamID(@Param("teamID") String teamID);

    List<Doc> getDocByUserID(@Param("userID") String userID);

    List<Doc> getDeletedDocByUserID(@Param("userID") String userID);

    int getDocDeleteState(@Param("docID") String docID);

    /*
    key:docID, docTitle, docContent, docLimit
     */
    int updateDoc(Map<String, Object> map);

    int movDocToCycleBin(@Param("docID") String docID);

    int recoverDoc(@Param("docID") String docID);

    int deleteDoc(@Param("docID") String docID);
}
