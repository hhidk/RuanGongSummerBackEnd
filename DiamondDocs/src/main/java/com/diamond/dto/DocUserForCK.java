package com.diamond.dto;

import com.diamond.pojo.DocUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DocUserForCK
{
    String id;
    String name;
    String avatar;

    public DocUserForCK(DocUser docUser)
    {
        this.id = docUser.getUserID();
        this.name = docUser.getUserName();
        this.avatar = docUser.getImagePath();
    }

    public static List<DocUserForCK> getDocUserForCKList(List<DocUser> userListist)
    {
        List<DocUserForCK> list = new ArrayList<DocUserForCK>();
        for (DocUser docUser : userListist)
        {
            list.add(new DocUserForCK(docUser));
        }
        return list;
    }
}
