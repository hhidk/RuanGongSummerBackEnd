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

    String creatorName;
    String teamName;

}
