package com.diamond.mapper;

import com.diamond.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper
{

    int addComment(Comment comment);

    List<Comment> getCommentByDocID(String docID);

    List<Comment> getCommentByUserID(String userID);

    int deleteComment(String commentID);

}
