package com.diamond.mapper;

import com.diamond.pojo.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DocMapper
{

    int addDoc(Doc doc);

    Doc getDocByDocID(String docID);

    List<Doc> getDocByDocTitle(String docTitle);

    List<Doc> getDocByTeamID(String teamID);

    int getDocDeleteState(String docID);

    /*
    key:docID, docTitle, docContent, docLimit
     */
    int updateDoc(Map<String, Object> map);

    int movDocToCycleBin(String docID);

    int recoverDoc(String docID);

    int deleteDoc(String docID);
}
