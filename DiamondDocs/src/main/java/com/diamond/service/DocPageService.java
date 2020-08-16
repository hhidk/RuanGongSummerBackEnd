package com.diamond.service;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocUserPreview;
import com.diamond.dto.HistoryPlus;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.DocUserMapper;
import com.diamond.mapper.TeamMapper;
import com.diamond.pojo.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocPageService {

    @Autowired
    private DocMapper docMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private DocUserMapper docUserMapper;

    public DocPlus getDoc(String docID) throws Exception{
        Doc doc = docMapper.getDocByDocID(docID);
        DocPlus docPlus = new DocPlus(doc);
        String teamID = doc.getTeamID();
        String creatorID = doc.getCreatorID();
        if(teamID != null){
            docPlus.setTeamName(teamMapper.getTeamByTeamID(teamID).getTeamName());
        }
        docPlus.setCreatorName(docUserMapper.getUserByID(creatorID).getUserName());
        return docPlus;
    }

    public List<HistoryPlus> getDocHistory(String docID){
        return null;
    }

    public List<DocUserPreview> getDocCollaborator(String docID){
        return null;
    }

    public int tryEditDoc(String userID, String docID){
        return 0;
    }

    public int completeEditDoc(String userID, String docID, int editState){
        return 0;
    }

    public int setDocLimit(String userID, String docID){
        return 0;
    }

    public int getDocLimit(String userID, String docID){
        return 0;
    }

    public void editDoc(String docID, String docContent) throws Exception{

        Map<String, Object> map = new HashMap<>();
        map.put("docID", docID);
        map.put("docContent", docContent);

        docMapper.updateDoc(map);

    }
}
