package com.diamond.service;

import com.diamond.mapper.DocMapper;
import com.diamond.mapper.HistoryMapper;
import com.diamond.pojo.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private DocMapper docMapper;

    public void editDoc(String userID, String docID) throws Exception{
        History history = new History();
        history.setDocID(docID);
        history.setIteration(historyMapper.getLastHistory(docID)+1);
        history.setUserID(userID);
        history.setOperation(2);
        historyMapper.addHistory(history);
    }

    public void editDocTitle(String userID, String docID) throws Exception{
        History history = new History();
        history.setDocID(docID);
        history.setIteration(historyMapper.getLastHistory(docID)+1);
        history.setUserID(userID);
        history.setOperation(5);
        historyMapper.addHistory(history);
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
        if(docMapper.getDocDeleteState(docID) == 1)
            return;
        History history = new History();
        history.setDocID(docID);
        history.setIteration(historyMapper.getLastHistory(docID)+1);
        history.setUserID(userID);
        history.setOperation(3);
        historyMapper.addHistory(history);
    }

    public void recoverDoc(String userID, String docID) throws Exception{
        History history = new History();
        history.setDocID(docID);
        history.setIteration(historyMapper.getLastHistory(docID)+1);
        history.setUserID(userID);
        history.setOperation(4);
        historyMapper.addHistory(history);
    }

    public void batchDeleteDoc(String userID, List<String> docIDs) throws Exception{
        for(String docID : docIDs) {
            deleteDoc(userID, docID);
        }
    }

}
