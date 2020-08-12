package com.diamond.service;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocPreview;
import com.diamond.mapper.DocMapper;
import com.diamond.pojo.Doc;
import com.diamond.utils.DiyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocService {

    @Autowired
    private DocMapper docMapper;

    public DocPlus getDoc(String userID, String docID) throws Exception{
        return null;
    }

    public DocPreview addDoc(String userID, String teamID) throws Exception{
        Doc doc = new Doc();

        doc.setCreatorID(userID);
        doc.setDocID(DiyUUID.generateDocID());
        doc.setDocTitle("无标题");
        doc.setDocLimit(0);
        doc.setIsDeleted(0);
        if(!teamID.equals("0"))
            doc.setTeamID(teamID);

        docMapper.addDoc(doc);
        return new DocPreview(doc);
    }

    public void editDoc(String docID, String userID,
                        String docTitle, String docContent, int docLimit) throws Exception{

    }

    public void deleteDoc(String userID, String docID) throws Exception{

    }

    public void recoverDoc(String userID, String docID) throws Exception{

    }

    public void collectDoc(String userID, String docID) throws Exception{

    }
}
