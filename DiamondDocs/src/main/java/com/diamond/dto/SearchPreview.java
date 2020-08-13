package com.diamond.dto;

import com.diamond.pojo.Doc;
import com.diamond.pojo.DocUser;
import com.diamond.pojo.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SearchPreview {
    String ID;
    String name;

    public SearchPreview(DocUser user){
        this.name = user.getUserName();
        this.ID = user.getUserID();
    }

    public SearchPreview(Team team){
        this.ID = team.getTeamID();
        this.name = team.getTeamName();
    }

    public SearchPreview(Doc doc){
        this.ID = doc.getDocID();
        this.name = doc.getDocTitle();
    }

    public static List<SearchPreview> getUserList(List<DocUser> userList){
        List<SearchPreview> list = new ArrayList<>();
        for(DocUser user : userList){
            list.add(new SearchPreview(user));
        }
        return list;
    }

    public static List<SearchPreview> getTeamList(List<Team> teamList){
        List<SearchPreview> list = new ArrayList<>();
        for(Team team : teamList){
            list.add(new SearchPreview(team));
        }
        return list;
    }

    public static List<SearchPreview> getDocList(List<Doc> docList){
        List<SearchPreview> list = new ArrayList<>();
        for(Doc doc : docList){
            list.add(new SearchPreview(doc));
        }
        return list;
    }
}
