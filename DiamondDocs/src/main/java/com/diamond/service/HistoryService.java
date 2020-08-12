package com.diamond.service;

import com.diamond.mapper.HistoryMapper;
import com.diamond.pojo.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    public void editDoc(String userID, String docID) throws Exception{

    }

    public void addDoc(String userID, String docID) throws Exception{
        History history = new History();
        history.setDocID(docID);
        history.setIteration(1);
        history.setUserID(userID);
        history.setOperation(1);
        historyMapper.addHistory(history);
    }

    public void deleteDoc(String userID, String docID) throws Exception{

    }

    public void recoverDoc(String userID, String docID) throws Exception{

    }

}
