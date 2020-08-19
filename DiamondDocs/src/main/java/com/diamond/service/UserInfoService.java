package com.diamond.service;

import com.diamond.dto.DocUserPreview;
import com.diamond.mapper.DocUserMapper;
import com.diamond.mapper.HistoryMapper;
import com.diamond.mapper.MemberMapper;
import com.diamond.pojo.DocUser;
import com.diamond.utils.DiyUUID;
import com.diamond.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoService {

    @Autowired
    private DocUserMapper docUserMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private HistoryMapper historyMapper;

    public String signUp(String name, String emailAddress, String password, String password2) throws Exception{
        if(!Validation.checkEmailFormat(emailAddress))
            return "1";
        else if(docUserMapper.getDocUserByEmailAddress(emailAddress) != null)
            return "4";
        else if(!password.equals(password2))
            return "2";
        else if(!Validation.checkPasswordFormat(password))
            return "3";

        DocUser user = new DocUser();
        String userID = DiyUUID.generateUserID();
        user.setUserID(userID);
        user.setEmailAddress(emailAddress);
        user.setUserName(name);
        user.setUserPassword(password);
        user.setImagePath("http://39.99.154.244:8080/IceCent/deadline2.jpg");
        docUserMapper.addDocUser(user);
        return userID;
    }

    public DocUser login(String name, String password) throws Exception {
        DocUser user = null;
        if(Validation.checkEmailFormat(name))
            user = docUserMapper.getDocUserByEmailAddress(name);
        if(user != null)
            if (user.getUserPassword().equals(password))
                return user;

        List<DocUser> userList = docUserMapper.getDocUserByUserName(name);
        for(DocUser candidate : userList)
            if(password.equals(candidate.getUserPassword()))
                return candidate;

        user = docUserMapper.getUserByID(name);
        if(user != null)
            if(user.getUserPassword().equals(password))
                return user;

        return null;
    }

    public int resetPwd(String emailAddress, String password, String password2) throws Exception
    {
        if(!Validation.checkEmailFormat(emailAddress))
            return 1;
        else if(!password.equals(password2))
            return 2;
        else if(!Validation.checkPasswordFormat(password))
            return 3;
        else if(docUserMapper.getDocUserByEmailAddress(emailAddress)==null)
            return 4;

        String userID = docUserMapper.getDocUserByEmailAddress(emailAddress).getUserID();
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", password);
        docUserMapper.updateDocUserPassword(map);
        return 0;
    }

    public int setUserName(String userID, String userName) throws Exception
    {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("userName", userName);
        docUserMapper.updateDocUserName(map);
        return 0;
    }

    public int setUserPassword(String userID, String userPassword) throws Exception
    {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        docUserMapper.updateDocUserPassword(map);
        return 0;
    }

    public int setEmailAddress(String userID, String emailAddress) throws Exception
    {
        if(docUserMapper.getDocUserByEmailAddress(emailAddress) != null)
            return 1;
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("emailAddress", emailAddress);
        docUserMapper.updateDocUserEmailAddress(map);
        return 0;
    }

    public int setImagePath(String userID, String imagePath) throws Exception
    {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("imagePath", imagePath);
        docUserMapper.updateDocUserImagePath(map);
        return 0;
    }

    public int setMobile(String userID, String mobile) throws Exception
    {
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userID);
        map.put("mobile", mobile);
        docUserMapper.updateDocUserMobile(map);
        return 0;
    }

    public Map<String, Integer> getUserAchievement(String userID) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("teamNum", memberMapper.getTeamNum(userID));
        map.put("collaboratorNum", memberMapper.getCollaboratorNum(userID));
        map.put("docNum", historyMapper.getDocNum(userID));
        return map;
    }

    public DocUserPreview getUserInfo(String userID) throws Exception
    {
        DocUserPreview docUserPreview = new DocUserPreview(docUserMapper.getUserByID(userID));
        return docUserPreview;
    }

}
