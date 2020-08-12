package com.diamond.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Member {
    private String userID;
    private int userIdentity;
    private String teamID;
    private String lastVisitTime;

    public Member(String userID, int userIdentity, String teamID){
        this.userID=userID;
        this.teamID=teamID;
        this.userIdentity=userIdentity;
    }
}
