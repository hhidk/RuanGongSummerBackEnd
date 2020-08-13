package com.diamond.service;

import com.diamond.dto.DocPreview;
import com.diamond.dto.DocUserPreview;
import com.diamond.dto.SearchPreview;
import com.diamond.dto.TeamPreview;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.DocUserMapper;
import com.diamond.mapper.TeamMapper;
import com.diamond.pojo.DocUser;
import com.diamond.pojo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return SearchPreview.getUserList(list);
    }

    public List<SearchPreview> searchDoc(String keyword) {
        List<Team> list = new ArrayList<>();
        return null;
    }

    public List<SearchPreview> searchTeam(String keyword) {
        return null;
    }
}
