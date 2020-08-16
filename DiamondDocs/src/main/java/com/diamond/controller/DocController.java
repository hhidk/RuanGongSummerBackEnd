package com.diamond.controller;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocPreview;
import com.diamond.service.BrowsesService;
import com.diamond.service.DocService;
import com.diamond.service.HistoryService;
import com.diamond.utils.FormatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class DocController {

    @Autowired
    private DocService docService;
    @Autowired
    private HistoryService historyService;

    @RequestMapping("/addDoc")
    public String addDoc(@RequestParam("userID") String userID, @RequestParam("teamID") String teamID){
        try {
            String docID = docService.addDoc(userID,teamID);
            historyService.addDoc(userID,docID);
            return docID;
        }
        catch (Exception e){
            e.printStackTrace();
            return "1";
        }
    }

    @RequestMapping("/editDocTitle")
    public int editDocTitle(@RequestParam("docID") String docID, @RequestParam("docTitle") String docTitle,
                            @RequestParam("userID") String userID){
        try {
            docService.editDocTitle(docID,docTitle);
            historyService.editDocTitle(userID,docID);
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
            historyService.deleteDoc(userID,docID);
            docService.deleteDoc(docID);
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

    @RequestMapping("/cancelCollectDoc")
    public int cancelCollectDoc(@RequestParam("userID") String userID, @RequestParam("docID") String docID){
        try {
            docService.cancelCollectDoc(userID, docID);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/docBatchDelete")
    public int batchDeleteDoc(@RequestParam("userID") String userID, @RequestParam("docIDs") String docIDs){
        try {
            List<String> list = FormatHandler.getListString(docIDs);
            docService.batchDeleteDoc(list);
            historyService.batchDeleteDoc(userID,list);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/docBatchRecover")
    public int batchRecoverDoc(@RequestParam("userID") String userID, @RequestParam("docIDs") String docIDs){
        try {
            List<String> list = FormatHandler.getListString(docIDs);
            docService.batchRecoverDoc(list);
            historyService.batchRecoverDoc(userID,list);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/docBatchFavorite")
    public int batchCollectDoc(@RequestParam("userID") String userID, @RequestParam("docIDs") String docIDs){
        try {
            List<String> list = FormatHandler.getListString(docIDs);
            docService.batchCollectDoc(userID, list);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }
}
