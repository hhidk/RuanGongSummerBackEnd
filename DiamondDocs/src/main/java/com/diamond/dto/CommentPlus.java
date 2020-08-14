package com.diamond.dto;

import com.diamond.pojo.Comment;
import lombok.Data;

@Data
public class CommentPlus
{
    String commentID;
    String userID;
    String commentContent;
    String createTime;
    String userName;
    String imagePath;
    Comment reply;
}
