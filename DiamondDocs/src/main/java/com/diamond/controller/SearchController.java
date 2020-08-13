package com.diamond.controller;

import com.diamond.dto.SearchPreview;
import com.diamond.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class SearchController<T> {

    @Autowired
    private SearchService searchService;

    @RequestMapping("/searchAll")
    public List<SearchPreview> searchAll(String type, String searchText){
        try {
            if(type.equals("user"))
                return searchService.searchUser(searchText);
            else if(type.equals("team"))
                return searchService.searchTeam(searchText);
            else if(type.equals("doc"))
                return searchService.searchDoc(searchText);
            else
                return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
