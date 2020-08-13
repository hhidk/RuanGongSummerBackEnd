package com.diamond.service;

import com.diamond.dto.DocPreview;
import com.diamond.dto.DocUserPreview;
import com.diamond.dto.MemberPreview;
import com.diamond.dto.SearchPreview;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.DocUserMapper;
import com.diamond.mapper.MemberMapper;
import com.diamond.mapper.TeamMapper;
import com.diamond.pojo.Doc;
import com.diamond.pojo.DocUser;
import com.diamond.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {

    @Autowired
    private DocUserMapper docUserMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private DocMapper docMapper;
    @Autowired
    private MemberMapper memberMapper;

    public List<SearchPreview> searchUser(String keyword) {
        List<DocUser> list = new ArrayList<>();
        list.addAll(docUserMapper.getDocUserByUserName(keyword));
        list.add(docUserMapper.getDocUserByEmailAddress(keyword));
        list.add(docUserMapper.getUserByID(keyword));
        list.add(docUserMapper.getUserByGithubID(keyword));
        list.addAll(docUserMapper.getDocUserByFuzzyQuery("%"+keyword+"%"));
        if(list.isEmpty())
            return null;
        list = removeDuplicated(list);
        return SearchPreview.getUserList(list);
    }

    public List<MemberPreview> searchTeamMember(String teamID, String searchText) {
        return memberMapper.getTeamMemberPreviewByTeamIDWithSearch(teamID, "%"+searchText+"%");
    }

    public List<DocUserPreview> searchOutsideUser(String teamID, String keyword) {
        List<DocUser> list = new ArrayList<>();
        list.addAll(docUserMapper.getDocUserByUserName(keyword));
        list.add(docUserMapper.getDocUserByEmailAddress(keyword));
        list.add(docUserMapper.getUserByID(keyword));
        list.add(docUserMapper.getUserByGithubID(keyword));
        list.addAll(docUserMapper.getDocUserByFuzzyQuery("%"+keyword+"%"));
        if(list.isEmpty())
            return null;
        list = removeDuplicated(list);
        List<DocUserPreview> newList = new ArrayList<>();
        for(DocUser user : list){
            if(memberMapper.checkIsInGroup(user.getUserID(), teamID) == null)
                newList.add(new DocUserPreview(user));
        }
        return newList;
    }

    public List<SearchPreview> searchDoc(String userID, String keyword) {
        List<Doc> list = new ArrayList<>();
        list.add(docMapper.getDocByDocID(keyword));

        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("keyword", "%"+keyword+"%");
        list.addAll(docMapper.getRelatedDocByUserID(map));
        if(list.isEmpty())
            return null;
        list = removeDuplicated(list);
        return SearchPreview.getDocList(list);
    }

    public List<SearchPreview> searchTeam(String userID, String keyword) {
        List<Team> list = new ArrayList<>();
        list.add(teamMapper.getTeamByTeamID(keyword));

        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("keyword", "%"+keyword+"%");
        list.addAll(teamMapper.getNotRelatedTeamByUserID(map));
        if(list.isEmpty())
            return null;
        list = removeDuplicated(list);
        return SearchPreview.getTeamList(list);
    }

    public List removeDuplicated(List list){
        List newList = new ArrayList();
        for (Object object : list){
            if(!newList.contains(object) && object != null)
                newList.add(object);
        }
        return newList;
    }
}
