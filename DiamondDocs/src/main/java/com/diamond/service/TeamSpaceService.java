package com.diamond.service;

import com.diamond.dto.DocPreview;
import com.diamond.dto.MemberPreview;
import com.diamond.dto.TeamPreview;
import com.diamond.mapper.FavoriteMapper;
import com.diamond.mapper.MemberMapper;
import com.diamond.mapper.TeamMapper;
import com.diamond.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamSpaceService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private FavoriteMapper favoriteMapper;

    public List<TeamPreview> getMyTeam(String userID) throws Exception{
        List<Team> teamList = memberMapper.getTeamByUserID(userID);
        return TeamPreview.getPreviewList(teamList);
    }

    public List<MemberPreview> getTeamMembers(String teamID) throws Exception{
        return memberMapper.getTeamMemberPreviewByTeamID(teamID);
    }

    public List<DocPreview> getTeamDocs(String userID, String teamID, int bytes) throws Exception{
        List<Doc> docList = memberMapper.getDocByTeamID(teamID);
        List<DocPreview> docPreviewList = DocPreview.getPreviewList(docList, bytes);
        for(DocPreview docPreview : docPreviewList) {
            Favorite favorite = favoriteMapper.checkExistFavorite(userID, docPreview.getDocID());
            if (favorite != null)
                docPreview.setIsFavorite(1);
            else
                docPreview.setIsFavorite(0);
        }
        return docPreviewList;
    }

    public int getUserIdentity(String userID, String teamID) throws Exception {
        memberMapper.updateLastVisitTime(userID, teamID);
        return memberMapper.getDocUserIdentity(userID, teamID);
    }
}
