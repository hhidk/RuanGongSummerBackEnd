package com.diamond.controller;

import com.diamond.dto.DocUserForCK;
import com.diamond.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class SuggestionController
{

    @Autowired
    private SuggestionService suggestionService;

    @RequestMapping("/getCollaboratorInfo")
    public List<DocUserForCK> getCollaboratorInfo(@RequestParam("docID") String docID)
    {
        try {
            return suggestionService.getCollaboratorInfo(docID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/addSuggestion")
    public int addSuggestion(@RequestParam("id") String id, @RequestParam("suggestion") String suggestion)
    {
        try {
            return suggestionService.addSuggestion(id, suggestion);
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/getSuggestion")
    public Object getSuggestion(@RequestParam("id") String id)
    {
        try {
            return suggestionService.getSuggestion(id);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/deleteSuggestion")
    public int deleteSuggestion(@RequestParam("id") String id)
    {
        try {
            suggestionService.deleteSuggestion(id);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

}
