package com.diamond.controller;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocPreview;
import com.diamond.pojo.Template;
import com.diamond.service.BrowsesService;
import com.diamond.service.DocService;
import com.diamond.service.HistoryService;
import com.diamond.service.TemplateService;
import com.diamond.utils.FormatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class DocController {

    @Autowired
    private DocService docService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private TemplateService templateService;

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

    @RequestMapping("copyDoc")
    public String copyDoc(@RequestParam("userID") String userID, @RequestParam("copyDocID") String copyDocID){
        try {
            String docID = docService.copyDoc(userID, copyDocID);
            historyService.addDoc(userID,docID);
            return docID;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "1";
        }
    }

    @RequestMapping("/getAllTemplate")
    public List<Template> getAllTemplate(){
        try {
            return templateService.getAllTemplate();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/addDocWithTemplate")
    public Map<String,String> addDocWithTemplate(@RequestParam("userID") String userID, @RequestParam("teamID") String teamID,
                                     @RequestParam("templateID") String templateID){
        try {
            Map<String, String> map = docService.addDocWithTemplate(userID,teamID,templateID);
            historyService.addDoc(userID,map.get("docID"));
            return map;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
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
            historyService.batchDeleteDoc(userID,list);
            docService.batchDeleteDoc(list);
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

    @RequestMapping("/docBatchCancelCollect")
    public int batchCancelCollectDoc(@RequestParam("userID") String userID, @RequestParam("docIDs") String docIDs){
        try {
            List<String> list = FormatHandler.getListString(docIDs);
            docService.batchCancelCollectDoc(userID, list);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    /*@RequestMapping("/docBatchFavorite")
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
    }*/
}
