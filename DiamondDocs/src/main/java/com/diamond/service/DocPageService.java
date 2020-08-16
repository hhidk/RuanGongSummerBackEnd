package com.diamond.service;

import com.diamond.dto.DocPlus;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.DocUserMapper;
import com.diamond.mapper.TeamMapper;
import com.diamond.pojo.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DocPageService {

    @Autowired
    private DocMapper docMapper;

    public DocPlus getDoc(String docID) throws Exception{
        DocPlus docPlus = docMapper.getDocByDocID(docID);
        return docPlus;
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
}
