package com.diamond.mapper;

import com.diamond.dto.DocUserForCK;
import com.diamond.pojo.DocUser;
import com.diamond.pojo.Suggestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SuggestionMapper
{

    List<DocUser> getCollaboratorInfo(@Param("docID") String docID);

    //key:id, content
    int addSuggestion(Map<String, Object> map);

    String getSuggestion(@Param("id") String id);

    int deleteSuggestion(@Param("id") String id);
}
