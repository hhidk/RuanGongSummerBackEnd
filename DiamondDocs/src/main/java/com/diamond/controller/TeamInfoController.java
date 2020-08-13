package com.diamond.controller;

import com.diamond.service.MessageService;
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
    @Autowired
    private MessageService messageService;

    @RequestMapping("/applyTeam")
    public int applyTeam(@RequestParam("userID") String userID, @RequestParam("targetTeamID") String teamID, @RequestParam("content") String msgContent){
        try {
            messageService.applyTeam(userID, teamID, msgContent);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/acceptMember")
    public int acceptMember(@RequestParam("userID") String userID, @RequestParam("targetUserID") String targetUserID,
                            @RequestParam("teamID") String teamID, @RequestParam("msgID") String msgID){
        try {
            messageService.accpetMember(userID,targetUserID,teamID);
            teamInfoService.accpetMember(targetUserID,teamID);
            messageService.deleteMsg(msgID);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/refuseMember")
    public int refuseMember(@RequestParam("userID") String userID, @RequestParam("targetUserID") String targetUserID,
                            @RequestParam("teamID") String teamID, @RequestParam("msgID") String msgID){
        try {
            messageService.refuseMember(userID,targetUserID,teamID);
            messageService.deleteMsg(msgID);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/inviteMember")
    public int inviteMember(@RequestParam("userID") String userID, @RequestParam("teamID") String teamID,
                            @RequestParam("targetUserID") String targetUserID, @RequestParam("content") String msgContent){
        try {
            messageService.inviteMember(userID,teamID,targetUserID,msgContent);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/joinTeam")
    public int joinTeam(@RequestParam("userID") String userID, @RequestParam("targetUserID") String targetUserID,
                        @RequestParam("teamID") String teamID, @RequestParam("msgID") String msgID){
        try {
            messageService.joinTeam(userID,targetUserID,teamID);
            teamInfoService.joinTeam(userID,teamID);
            messageService.deleteMsg(msgID);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/refuseTeam")
    public int refuseTeam(@RequestParam("userID") String userID, @RequestParam("targetUserID") String targetUserID,
                          @RequestParam("teamID") String teamID, @RequestParam("msgID") String msgID){
        try {
            messageService.refuseTeam(userID,targetUserID,teamID);
            messageService.deleteMsg(msgID);
            return 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

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

    @RequestMapping("/setTeamName")
    public int setTeamName(@RequestParam("teamID") String teamID, @RequestParam("teamName") String teamName){
        try {
            return teamInfoService.setTeamName(teamID, teamName);
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
    public int setAdmin(@RequestParam("userID") String userID, @RequestParam("teamID") String teamID, @RequestParam("userIdentity") int userIdentity)
    {
        try {
            return teamInfoService.setAdmin(userID, teamID, userIdentity);
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

}
