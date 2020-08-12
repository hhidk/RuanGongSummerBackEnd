package com.diamond.controller;

import com.diamond.dto.DocPreview;
import com.diamond.service.UserSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserSpaceController {

    @Autowired
    private UserSpaceService userSpaceService;

    @RequestMapping("/getMyDocs")
    public List<DocPreview> getMyDocs(int userID){
        try {
            return userSpaceService.getMyDocs(userID);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getRecentDocs")
    public List<DocPreview> getRecentDocs(int userID){
        try {
            return userSpaceService.getRecentDocs(userID);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getFavoriteDocs")
    public List<DocPreview> getFavoriteDocs(int userID){
        try {
            return userSpaceService.getFavoriteDocs(userID);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
