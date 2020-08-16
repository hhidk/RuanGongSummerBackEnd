package com.diamond.service;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocUserPreview;
import com.diamond.dto.HistoryPlus;
import com.diamond.mapper.*;
import com.diamond.pojo.Doc;
import com.diamond.utils.FormatHandler;
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
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private HistoryMapper historyMapper;

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

    public List<HistoryPlus> getDocHistory(String docID) throws Exception{
        List<HistoryPlus> list = historyMapper.getDocHistory(docID);
        for (HistoryPlus historyPlus : list)
            historyPlus.setOperateTime(FormatHandler.AlterTimeFormat(historyPlus.getOperateTime()));
        return list;
    }

    public List<DocUserPreview> getDocCollaborator(String docID) throws Exception{
        return historyMapper.getDocCollaborator(docID);
    }

    public int tryEditDoc(String docID) throws Exception{
        return 1 - docMapper.tryEditDoc(docID);
    }

    public int completeEditDoc(String docID) throws Exception{
        return 1 - docMapper.completeEditDoc(docID);
    }

    public int setDocLimit(String userID, String docID, int docLimit) throws Exception{
        if(docMapper.getCreatorID(docID).equals(userID))
        {
            Map<String, Object> map = new HashMap<>();
            map.put("docID", docID);
            map.put("docLimit", docLimit);
            docMapper.setDocLimit(map);
            return 0;
        }
        else
        {
            return 1;
        }
    }

    public int getDocLimit(String userID, String docID) throws Exception{
        Doc doc = docMapper.getDocByDocID(docID);
        String teamID = doc.getTeamID();
        String creatorID = doc.getCreatorID();
        int docLimit = doc.getDocLimit();
        //个人文档
        if(teamID == null){
            if(userID.equals(creatorID))
                return 0;
            else
                if(docLimit == 0)
                    return 1;
                else
                    return 2;
        }
        //团队文档
        else {
            //0同组管理员，1同组成员，2组外其他成员
            int userIdentity;

            //判断userIdentity
            if(memberMapper.checkIsInGroup(userID, teamID) == null)
                userIdentity = 2;
            else if(memberMapper.getDocUserIdentity(userID, teamID) == 0)
                userIdentity = 1;
            else
                userIdentity = 0;

            //判断权限
            switch (userIdentity){
                case 0:
                    return 0;
                case 1:
                    switch(docLimit){
                        case 0:
                            return 0;
                        case 1:
                            return 0;
                        case 2:
                            return 1;
                        case 3:
                            return 1;
                        case 4:
                            return 2;
                    }
                case 2:
                    switch(docLimit){
                        case 0:
                            return 1;
                        case 1:
                            return 2;
                        case 2:
                            return 1;
                        case 3:
                            return 2;
                        case 4:
                            return 2;
                    }
            }
        }
        return 3;
    }

    public void editDoc(String docID, String docContent) throws Exception{

        Map<String, Object> map = new HashMap<>();
        map.put("docID", docID);
        map.put("docContent", docContent);

        docMapper.updateDoc(map);

    }
}
