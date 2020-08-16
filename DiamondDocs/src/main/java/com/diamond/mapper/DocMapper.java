package com.diamond.mapper;

import com.diamond.dto.DocPlus;
import com.diamond.pojo.Doc;
import jdk.nashorn.internal.runtime.OptimisticBuiltins;
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

    String getDocTeam(@Param("docID") String docID);

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

    /*
    如果该函数返回1，则无人编辑，并且将editState置1
    如果返回0，说明有人在编辑，不进行操作
     */
    int tryEditDoc(@Param("docID") String docID);

    /*
    如果该函数返回1，则将editState置0
    如果返回0，说明该用户未获得锁，不进行操作
    key:userID, docID
     */
    int completeEditDoc(@Param("docID") String docID);

    //key:docID, docLimit
    int setDocLimit(Map<String, Object> map);

    int deleteDoc(@Param("docID") String docID);
}
