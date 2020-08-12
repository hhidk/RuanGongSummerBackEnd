package com.diamond.controller;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocPreview;
import com.diamond.service.DocService;
import com.diamond.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class DocController {

    @Autowired
    private DocService docService;
    //和文档表相关的内容
    @Autowired
    private HistoryService historyService;
    //和历史记录相关的内容，两个拆开来清晰一点，controller分别调用这两个service的方法

    @RequestMapping("/getDoc")
    public DocPlus getDoc(@RequestParam("userID") String userID, @RequestParam("docID") String docID){
        try {
            return docService.getDoc(userID,docID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/addDoc")
    public DocPreview addDoc(@RequestParam("userID") String userID, @RequestParam("teamID") String teamID){
        try {
            DocPreview docPreview = docService.addDoc(userID,teamID);
            historyService.addDoc(userID,docPreview.getDocID());
            return docPreview;
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

    @RequestMapping("/deleteDoc")
    public int deleteDoc(@RequestParam("userID") String userID, @RequestParam("docID") String docID){
        try {
            docService.deleteDoc(docID);
            historyService.deleteDoc(userID,docID);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/recoverDoc")
    public int recoverDoc(@RequestParam("userID") String userID, @RequestParam("docID") String docID){
        try {
            docService.recoverDoc(docID);
            historyService.recoverDoc(userID,docID);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/collectDoc")
    public int collectDoc(@RequestParam("userID") String userID, @RequestParam("docID") String docID){
        try {
            docService.collectDoc(userID, docID);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }
}
