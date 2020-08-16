package com.diamond.service;

import com.diamond.dto.DocPlus;
import com.diamond.dto.DocUserPreview;
import com.diamond.dto.HistoryPlus;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.DocUserMapper;
import com.diamond.mapper.MemberMapper;
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
    @Autowired
    private MemberMapper memberMapper;

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
        return null;
    }

    public List<DocUserPreview> getDocCollaborator(String docID) throws Exception{
        return null;
    }

    public int tryEditDoc(String userID, String docID) throws Exception{
        return 0;
    }

    public int completeEditDoc(String userID, String docID) throws Exception{
        return 0;
    }

    public int setDocLimit(String userID, String docID) throws Exception{
        return 0;
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
