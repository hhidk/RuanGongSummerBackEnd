package com.diamond.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Doc
{
    private String docID;
    private String docContent;
    private String docTitle;
    private String teamID;
    private String creatorID;
    private String createTime;
    private String lastEditTime;
    private int editState;
    private int docLimit;
    private int isDeleted;

}
