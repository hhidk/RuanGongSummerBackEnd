package com.diamond.service;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocPreview;
import com.diamond.mapper.BrowsesMapper;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.FavoriteMapper;
import com.diamond.mapper.TemplateMapper;
import com.diamond.pojo.Browses;
import com.diamond.pojo.Doc;
import com.diamond.pojo.Favorite;
import com.diamond.pojo.Template;
import com.diamond.utils.DiyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocService {

    @Autowired
    private DocMapper docMapper;
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private TemplateMapper templateMapper;

    public String addDoc(String userID, String teamID) throws Exception{
        Doc doc = new Doc();
        String docID = DiyUUID.generateDocID();

        doc.setCreatorID(userID);
        doc.setDocID(docID);
        doc.setDocTitle("无标题");
        doc.setDocContent("");
        doc.setDocLimit(0);
        doc.setIsDeleted(0);
        if(!teamID.equals("0"))
            doc.setTeamID(teamID);

        docMapper.addDoc(doc);
        return docID;
    }

    public String addNewDoc(String userID) throws Exception{
        Doc doc = new Doc();
        String docID = DiyUUID.generateDocID();
        doc.setCreatorID(userID);
        doc.setDocID(docID);
        doc.setDocTitle("带得栏文档欢迎你");
        doc.setDocContent("<h1 style=\"text-align:center;\">欢迎来到带得栏文档</h1><h2>在这里，你可以做什么？</h2><p>&nbsp;</p><p>团队协作，文档共享，贴心交互</p><p>&nbsp;</p><h2>关于我们</h2><p>&nbsp;</p><p>本网站由带得栏项目团队开发，同时欢迎光临<span style=\"background-color:hsl(180, 75%, 60%);\"><u>带得栏论坛（现在还不能用）</u></span></p><p>&nbsp;</p><hr><p>我编不下去了，谁来帮我多编一点。</p>");
        doc.setDocLimit(0);
        doc.setIsDeleted(0);
        docMapper.addDoc(doc);
        return docID;
    }

    public Map<String,String> addDocWithTemplate(String userID, String teamID, String templateID) throws Exception {
        Doc doc = new Doc();
        String docID = DiyUUID.generateDocID();
        Template template = templateMapper.getTemplateByTemplateID(templateID);

        doc.setCreatorID(userID);
        doc.setDocID(docID);
        doc.setDocTitle("[模板]" + template.getTemplateName());
        doc.setDocContent(template.getTemplateContent());
        doc.setDocLimit(0);
        doc.setIsDeleted(0);
        if(!teamID.equals("0"))
            doc.setTeamID(teamID);

        docMapper.addDoc(doc);
        Map<String,String> map = new HashMap<>();
        map.put("docID", docID);
        map.put("docTitle", doc.getDocTitle());
        return map;
    }

    public void editDocTitle(String docID, String docTitle) throws Exception{
        docMapper.updateDocTitle(docID,docTitle);
    }

    public void deleteDoc(String docID) throws Exception{

        int isInCycleBin = docMapper.getDocDeleteState(docID);
        if(isInCycleBin == 1)
        {
            docMapper.deleteDoc(docID);
        }
        else
        {
            docMapper.movDocToCycleBin(docID);
        }

    }

    public void recoverDoc(String docID) throws Exception{

        docMapper.recoverDoc(docID);

    }

    public void collectDoc(String userID, String docID) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("docID", docID);
        favoriteMapper.addFavorite(map);
    }

    public void cancelCollectDoc(String userID, String docID) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("docID", docID);
        favoriteMapper.deleteFavorite(map);
    }

    public void batchDeleteDoc(List<String> docIDs) throws Exception{
        for(String docID : docIDs) {
            deleteDoc(docID);
        }
    }

    public void batchRecoverDoc(List<String> docIDs) throws Exception{
        for(String docID : docIDs) {
            recoverDoc(docID);
        }
    }

    public void batchCancelCollectDoc(String userID, List<String> docIDs) throws Exception{
        for(String docID : docIDs) {
            cancelCollectDoc(userID, docID);
        }
    }

    /*public void batchCollectDoc(String userID, List<String> docIDs) throws Exception{
        for(String docID : docIDs) {
            collectDoc(userID, docID);
        }
    }*/
}
