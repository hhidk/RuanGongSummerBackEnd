package com.diamond.service;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocPreview;
import org.springframework.stereotype.Service;

@Service
public class DocService {

    public DocPlus getDoc(String userID, String docID) throws Exception{
        return null;
    }

    public DocPreview addDoc(String userID, String teamID) throws Exception{
        return null;
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
