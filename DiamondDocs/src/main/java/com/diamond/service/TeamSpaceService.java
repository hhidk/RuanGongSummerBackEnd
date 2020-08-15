package com.diamond.service;

import com.diamond.dto.DocPreview;
import com.diamond.dto.MemberPreview;
import com.diamond.dto.TeamPreview;
import com.diamond.mapper.MemberMapper;
import com.diamond.mapper.TeamMapper;
import com.diamond.pojo.Doc;
import com.diamond.pojo.DocUser;
import com.diamond.pojo.Member;
import com.diamond.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamSpaceService {

    @Autowired
    private MemberMapper memberMapper;

    public List<TeamPreview> getMyTeam(String userID) throws Exception{
        List<Team> teamList = memberMapper.getTeamByUserID(userID);
        return TeamPreview.getPreviewList(teamList);
    }

    public List<MemberPreview> getTeamMembers(String teamID) throws Exception{
        return memberMapper.getTeamMemberPreviewByTeamID(teamID);
    }

    public List<DocPreview> getTeamDocs(String teamID, int type) throws Exception{
        List<Doc> docList = memberMapper.getDocByTeamID(teamID);
        return DocPreview.getPreviewList(docList, type);
    }

    public int getUserIdentity(String userID, String teamID) throws Exception {
        memberMapper.updateLastVisitTime(userID, teamID);
        return memberMapper.getDocUserIdentity(userID, teamID);
    }
}
