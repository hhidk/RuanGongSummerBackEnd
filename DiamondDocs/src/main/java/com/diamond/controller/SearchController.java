package com.diamond.controller;

import com.diamond.dto.DocUserPreview;
import com.diamond.dto.MemberPreview;
import com.diamond.dto.SearchPreview;
import com.diamond.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class SearchController<T> {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/searchDoc")
    public List<SearchPreview> searchDoc(@RequestParam("userID") String userID, @RequestParam("searchText") String searchText){
        try {
            return searchService.searchDoc(userID, searchText);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/searchTeam")
    public List<SearchPreview> searchTeam(@RequestParam("userID") String userID, @RequestParam("searchText") String searchText){
        try {
            return searchService.searchTeam(userID, searchText);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/searchTeamMember")
    public List<MemberPreview> searchTeamMember(@RequestParam("teamID") String teamID, @RequestParam("searchText") String searchText){
        try {
            return searchService.searchTeamMember(teamID, searchText);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/searchOutSideUser")
    public List<DocUserPreview> searchOutSideUser(@RequestParam("teamID") String teamID, @RequestParam("searchText") String searchText){
        try {
            return searchService.searchOutsideUser(teamID, searchText);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

/*
    @RequestMapping("/searchAll")
    public List<SearchPreview> searchAll(String type, String searchText){
        try {
            if(type.equals("user"))
                return searchService.searchUser(searchText);
            else
                return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }*/

}
