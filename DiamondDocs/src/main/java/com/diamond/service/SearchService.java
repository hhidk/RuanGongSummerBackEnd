package com.diamond.service;

import com.diamond.dto.SearchPreview;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.DocUserMapper;
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

    public List<SearchPreview> searchDoc(String userID, String keyword) {
        List<Doc> list = new ArrayList<>();
        list.add(docMapper.getDocByDocID(keyword));

        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("keyword", keyword);
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
        map.put("keyword", keyword);
        list.addAll(teamMapper.getRelatedTeamByUserID(map));
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
