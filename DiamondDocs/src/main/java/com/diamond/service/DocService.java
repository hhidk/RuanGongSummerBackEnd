package com.diamond.service;

import com.diamond.dto.DocPlus;
import org.springframework.stereotype.Service;

@Service
public class DocService {

    public DocPlus getDoc(String userID, String docID) throws Exception{
        return null;
    }

    public void addDoc(String userID, String teamID, String docTitle,
                       String docContent, int docLimit) throws Exception{

    }

    public void editDoc(String docID, String userID, String teamID,
                        String docTitle, String docContent, int docLimit) throws Exception{

    }

    public void deleteDoc(String userID, String docID) throws Exception{

    }

    public void recoverDoc(String userID, String docID) throws Exception{

    }

    public void collectDoc(String userID, String docID) throws Exception{

    }
}
