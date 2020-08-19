package com.diamond.service;

import com.diamond.dto.DocUserForCK;
import com.diamond.mapper.SuggestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SuggestionService
{
    @Autowired
    private SuggestionMapper suggestionMapper;

    public List<DocUserForCK> getCollaboratorInfo(String docID) throws Exception
    {
        return DocUserForCK.getDocUserForCKList(suggestionMapper.getCollaboratorInfo(docID));
    }

    public int addSuggestion(String id, Object suggestion) throws Exception
    {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("content", suggestion);
        suggestionMapper.addSuggestion(map);
        return 0;
    }

    public String getSuggestion(String id) throws Exception
    {
        return suggestionMapper.getSuggestion(id);
    }

    public int deleteSuggestion(String id) throws Exception
    {
        suggestionMapper.deleteSuggestion(id);
        return 0;
    }

}
