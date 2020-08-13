package com.diamond.service;

import com.diamond.mapper.MemberMapper;
import com.diamond.mapper.TeamMapper;
import com.diamond.pojo.Member;
import com.diamond.pojo.Team;
import com.diamond.utils.DiyUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeamInfoService {

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private MemberMapper memberMapper;

    public int addTeam(String userID, String TeamName) throws Exception
    {
        Team team = new Team();
        String teamID = DiyUUID.generateTeamID();
        team.setCreatorID(userID);
        team.setTeamName(TeamName);
        team.setTeamID(teamID);
        teamMapper.createTeam(team);
        memberMapper.addMember(new Member(userID,2,teamID));
        return 0;
    }

    public int setTeamName(String teamID, String teamName) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("teamID", teamID);
        map.put("teamName", teamName);
        teamMapper.updateTeamName(map);
        return 0;
    }

    public int quitTeam(String userID, String teamID) throws Exception
    {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("teamID", teamID);
        memberMapper.deleteMember(map);
        return 0;
    }

    public int disbandTeam(String teamID) throws Exception
    {
        teamMapper.deleteTeam(teamID);
        return 0;
    }

    public int setAdmin(String userID, String teamID, int userIdentity) throws Exception
    {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("teamID", teamID);
        map.put("userIdentity", userIdentity);
        memberMapper.setAdmin(map);
        return 0;
    }
}
