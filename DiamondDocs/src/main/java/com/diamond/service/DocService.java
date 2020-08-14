package com.diamond.service;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocPreview;
import com.diamond.mapper.BrowsesMapper;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.FavoriteMapper;
import com.diamond.pojo.Browses;
import com.diamond.pojo.Doc;
import com.diamond.pojo.Favorite;
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

    public DocPlus getDoc(String docID) throws Exception{
        Doc doc = docMapper.getDocByDocID(docID);
        return new DocPlus(doc);
    }

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

    public void editDocTitle(String docID, String docTitle) throws Exception{
        docMapper.updateDocTitle(docID,docTitle);
    }

    public void editDoc(String docID, String docTitle,
                        String docContent, int docLimit) throws Exception{

        Map<String, Object> map = new HashMap<>();
        map.put("docID", docID);
        map.put("docTitle", docTitle);
        map.put("docContent", docContent);
        map.put("docLimit", docLimit);

        docMapper.updateDoc(map);

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

    public void batchDeleteDoc(List<String> docIDs) throws Exception{
        for(String docID : docIDs) {
            deleteDoc(docID);
        }
    }

    public void batchCollectDoc(String userID, List<String> docIDs) throws Exception{
        for(String docID : docIDs) {
            collectDoc(userID, docID);
        }
    }
}
