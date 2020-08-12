package com.diamond.controller;

import com.diamond.service.TeamInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class TeamInfoController {

    @Autowired
    private TeamInfoService teamInfoService;

    @RequestMapping("/addTeam")
    public int addTeam(@RequestParam("userID") String userID, @RequestParam("teamName") String teamName)
    {
        try {
            return teamInfoService.addTeam(userID, teamName);
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/quitTeam")
    public int quitTeam(@RequestParam("userID") String userID, @RequestParam("teamID") String teamID)
    {
        try {
            return teamInfoService.quitTeam(userID, teamID);
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/disbandTeam")
    public int disbandTeam(@RequestParam("teamID") String teamID)
    {
        try {
            return teamInfoService.disbandTeam(teamID);
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/setAdmin")
    public int setAdmin(@RequestParam("userID") String userID, @RequestParam("teamID") String teamID)
    {
        try {
            return teamInfoService.setAdmin(userID, teamID);
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

}
