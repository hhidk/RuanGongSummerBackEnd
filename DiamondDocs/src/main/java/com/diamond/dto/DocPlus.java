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
    String createTime;
    String lastEditTime;
    String creatorID;
    int docLimit;

    String creatorName;
    String teamName;

    public DocPlus(Doc doc){
        this.docID = doc.getDocID();
        this.docTitle = doc.getDocTitle();
        this.docContent = doc.getDocContent();
        this.createTime = doc.getCreateTime();
        this.lastEditTime = doc.getLastEditTime();
        this.creatorID = doc.getCreatorID();
        this.docLimit = doc.getDocLimit();
    }

}
