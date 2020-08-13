package com.diamond.dto;

import com.diamond.pojo.DocUser;
import com.diamond.pojo.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
 * 用户类的简略信息
 * 用于在获取各种用户列表时预览用户信息
 */

@Data
@NoArgsConstructor
public class DocUserPreview {
    String userID;
    String userName;
    String imagePath;
    String emailAddress;

    public DocUserPreview(DocUser docUser){
        this.userID = docUser.getUserID();
        this.userName = docUser.getUserName();
        this.imagePath = docUser.getImagePath();
        this.emailAddress = docUser.getEmailAddress();
    }

    public static List<DocUserPreview> getPreviewList(List<DocUser> userList){
        List<DocUserPreview> list = new ArrayList<>();
        for (DocUser user : userList)
            list.add(new DocUserPreview(user));
        return list;
    }
}
