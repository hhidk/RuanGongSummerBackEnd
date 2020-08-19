package com.diamond.controller;

import com.diamond.dto.DocUserPreview;
import com.diamond.mapper.DocUserMapper;
import com.diamond.pojo.DocUser;
import com.diamond.service.BrowsesService;
import com.diamond.service.DocService;
import com.diamond.service.HistoryService;
import com.diamond.service.UserInfoService;
import com.diamond.utils.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserInfoController {

    @Autowired
    private MailClient mailClient;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private DocService docService;
    @Autowired
    private BrowsesService browsesService;
    @Autowired
    private HistoryService historyService;

    @RequestMapping("/emailVerification")
    public int emailVerification(@RequestParam("emailAddress") String emailAddress){
        try {
            int verificationCode = mailClient.generateVerificationCode();
            mailClient.sendEmailVerificationCode(emailAddress, verificationCode);
            return verificationCode;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("/signUp")
    public int signUp(@RequestParam("name") String name, @RequestParam("emailAddress") String emailAddress,
                      @RequestParam("password") String password, @RequestParam("password2") String password2){
        try {
            String result = userInfoService.signUp(name,emailAddress,password,password2);
            if(result.length() != 1){
                String docID = docService.addNewDoc(result);
                historyService.addDoc(result, docID);
                browsesService.browseDoc(result, docID);
                return 0;
            }
            return Integer.parseInt(result);
        }
        catch (Exception e){
            e.printStackTrace();
            return 5;
        }
    }

    @RequestMapping("/login")
    public DocUser login(@RequestParam("name") String name, @RequestParam("password") String password){
        try {
            return userInfoService.login(name,password);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/resetPwd")
    public int resetPwd(@RequestParam("emailAddress") String emailAddress, @RequestParam("password") String password,
                        @RequestParam("password2") String password2) {
        try {
            return userInfoService.resetPwd(emailAddress, password, password2);
        }
        catch (Exception e){
            e.printStackTrace();
            return 5;
        }
    }

    @RequestMapping("/setUserName")
    public int setUserName(@RequestParam("userID") String userID, @RequestParam("userName") String userName)
    {
        try
        {
            return userInfoService.setUserName(userID, userName);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/setUserPassword")
    public int setUserPassword(@RequestParam("userID") String userID, @RequestParam("userPassword") String userPassword)
    {
        try
        {
            return userInfoService.setUserPassword(userID, userPassword);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/setEmailAddress")
    public int setEmailAddress(@RequestParam("userID") String userID, @RequestParam("emailAddress") String emailAddress)
    {
        try
        {
            return userInfoService.setEmailAddress(userID, emailAddress);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/setImagePath")
    public int setImagePath(@RequestParam("userID") String userID, @RequestParam("imagePath") String imagePath)
    {
        try
        {
            return userInfoService.setImagePath(userID, imagePath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/setMobile")
    public int setMobile(@RequestParam("userID") String userID, @RequestParam("mobile") String mobile)
    {
        try
        {
            return userInfoService.setMobile(userID, mobile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/getUserAchievement")
    public Map<String,Integer> getUserAchievement(@RequestParam("userID") String userID){
        try{
            return userInfoService.getUserAchievement(userID);
        }
        catch (Exception e) {
            e.printStackTrace();
            Map<String, Integer> map = new HashMap<>();
            map.put("teamNum",0);
            map.put("collaboratorNum",0);
            map.put("docNum",0);
            return map;
        }
    }



    @RequestMapping("/getUserInfo")
    public DocUserPreview getUserInfo(@RequestParam("userID") String userID)
    {
        try {
            return userInfoService.getUserInfo(userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
