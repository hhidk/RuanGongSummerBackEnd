package com.diamond.dto;

import com.diamond.pojo.Doc;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocPlus {
    //具体有啥得看前端的需求
    String docID;
    String docTitle;

    public DocPlus(Doc doc){
        this.docID = doc.getDocID();
        this.docTitle = doc.getDocTitle();
    }
}
