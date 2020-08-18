package com.diamond.controller;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocUserPreview;
import com.diamond.dto.HistoryPlus;
import com.diamond.service.BrowsesService;
import com.diamond.service.DocPageService;
import com.diamond.service.DocService;
import com.diamond.service.HistoryService;
import com.diamond.utils.DiyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class DocPageController {

    @Autowired
    private DocPageService docPageService;
    @Autowired
    private BrowsesService browsesService;
    @Autowired
    private HistoryService historyService;

    @RequestMapping("/getDoc")
    public DocPlus getDoc(@RequestParam("userID") String userID, @RequestParam("docID") String docID){
        try {
            browsesService.browseDoc(userID, docID);
            return docPageService.getDoc(docID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getDocHistory")
    public List<HistoryPlus> getDocHistory(@RequestParam("docID") String docID){
        try {
            return docPageService.getDocHistory(docID);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getDocCollaborator")
    public List<DocUserPreview> getDocCollaborator(@RequestParam("docID") String docID){
        try {
            return docPageService.getDocCollaborator(docID);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/tryEditDoc")
    public int tryEditDoc(@RequestParam("docID") String docID){
        try {
            return docPageService.tryEditDoc(docID);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    @RequestMapping("/completeEditDoc")
    public int completeEditDoc(@RequestParam("userID") String userID, @RequestParam("docID") String docID){
        try {
            historyService.editDoc(userID, docID);
            return docPageService.completeEditDoc(docID);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/setDocLimit")
    public int setDocLimit(@RequestParam("userID") String userID, @RequestParam("docID") String docID, @RequestParam("docLimit") int docLimit){
        try {
            return docPageService.setDocLimit(userID, docID, docLimit);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/getDocLimit")
    public int getDocLimit(@RequestParam("userID") String userID, @RequestParam("docID") String docID){
        try {
            return docPageService.getDocLimit(userID, docID);
        }
        catch (Exception e) {
            e.printStackTrace();
            return 3;
        }
    }

    @RequestMapping("/editDoc")
    public int editDoc( @RequestParam("docID") String docID, @RequestParam("docContent") String docContent){
        try {
            docPageService.editDoc(docID, docContent);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/uploadImage")
    public Map<String, Object> uploadImage(MultipartFile upload){
        try{
            String dirPath = "/root/docimages";
            File dir = new File(dirPath);
            if(!dir.exists())
                dir.mkdirs();

            String imageName = DiyUUID.generateImageID() + "_" +upload.getOriginalFilename();
            String filePath = dirPath + "/" + imageName;
            File savedImg=new File(filePath);
            upload.transferTo(savedImg);

            String url = "http://39.99.154.244:8080/docimages/"+imageName;
            Map<String, Object> map = new HashMap<>();
            map.put("url",url);
            return map;
        }
        catch (Exception e){
            System.out.println("图片上传产生异常");
            e.printStackTrace();
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> subMap = new HashMap<>();
            subMap.put("message","file upload failed.");
            map.put("error",subMap);
            return map;
        }
    }
}
