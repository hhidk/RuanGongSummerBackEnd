package com.diamond.controller;

import com.diamond.dto.DocPlus;
import com.diamond.service.BrowsesService;
import com.diamond.service.DocService;
import com.diamond.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class DocPageController {

    @Autowired
    private DocService docService;
    @Autowired
    private BrowsesService browsesService;
    @Autowired
    private HistoryService historyService;

    @RequestMapping("/getDoc")
    public DocPlus getDoc(@RequestParam("userID") String userID, @RequestParam("docID") String docID){
        try {
            browsesService.browseDoc(userID, docID);
            return docService.getDoc(docID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/editDoc")
    public int editDoc(@RequestParam("userID") String userID, @RequestParam("docID") String docID,
                       @RequestParam("docTitle") String docTitle, @RequestParam("docContent") String docContent,
                       @RequestParam("docLimit") int docLimit){
        try {
            docService.editDoc(docID,docTitle,docContent,docLimit);
            historyService.editDoc(userID,docID);

            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }
}
