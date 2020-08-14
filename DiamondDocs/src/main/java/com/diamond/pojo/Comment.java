package com.diamond.pojo;

import lombok.Data;

@Data
public class Comment
{
    private String commentID;
    private String docID;
    private String userID;
    private String createTime;
    private String commentContent;
    private String replyID;

}
