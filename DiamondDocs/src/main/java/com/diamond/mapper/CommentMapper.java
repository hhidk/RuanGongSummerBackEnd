package com.diamond.mapper;

import com.diamond.dto.CommentPlus;
import com.diamond.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper
{

    int addComment(Comment comment);

    List<CommentPlus> getCommentByDocID(String docID);

    List<CommentPlus> getCommentByUserID(String userID);

    int deleteComment(String commentID);

}
