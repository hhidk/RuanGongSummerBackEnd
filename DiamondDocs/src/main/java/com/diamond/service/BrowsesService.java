package com.diamond.service;

import com.diamond.mapper.BrowsesMapper;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.MemberMapper;
import com.diamond.pojo.Browses;
import com.diamond.pojo.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrowsesService {

    @Autowired
    private BrowsesMapper browsesMapper;
    @Autowired
    private DocMapper docMapper;
    @Autowired
    private MemberMapper memberMapper;

    public void browseDoc(String userID, String docID) throws Exception {
        //添加个人浏览记录
        Browses browses = new Browses();
        browses.setUserID(userID);
        browses.setDocID(docID);
        browsesMapper.addBrowses(browses);

        //标记该文档所在组（如果有）
        Doc doc = docMapper.getDocByDocID(docID);
        if(doc.getTeamID() != null)
            memberMapper.updateLastVisitTime(userID, doc.getTeamID());
    }
}
