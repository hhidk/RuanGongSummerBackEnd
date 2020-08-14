package com.diamond.dto;

import com.diamond.pojo.Doc;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DocPlus {
    String docID;
    String docTitle;
    String docContent;
    String lastEditTime;
    int docLimit;
    int editState;
    String creatorID;

    String name;

    public DocPlus(Doc doc){
        this.docID = doc.getDocID();
        this.docTitle = doc.getDocTitle();
    }
}
