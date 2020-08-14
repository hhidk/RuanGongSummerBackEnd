package com.diamond.service;

import com.diamond.dto.CommentPlus;
import com.diamond.mapper.CommentMapper;
import com.diamond.pojo.Comment;
import com.diamond.utils.DiyUUID;
import com.diamond.utils.FormatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public int addComment(String docID, String userID, String commentContent, String replyID) throws Exception
    {
        Comment comment = new Comment();
        comment.setCommentID(DiyUUID.generateCommentID());
        comment.setDocID(docID);
        comment.setUserID(userID);
        comment.setCommentContent(commentContent);
        commentMapper.addComment(comment);
        return 0;
    }

    public List<CommentPlus> getDocComment(String docID) throws Exception
    {
        List<CommentPlus> list = commentMapper.getCommentByDocID(docID);
        for (CommentPlus commentPlus : list)
        {
            commentPlus.setCreateTime(FormatHandler.AlterTimeFormat(commentPlus.getCreateTime()));
        }
        return list;
    }

    public List<CommentPlus> getUserComment(String userID) throws Exception
    {
        List<CommentPlus> list = commentMapper.getCommentByUserID(userID);
        for (CommentPlus commentPlus : list)
        {
            commentPlus.setCreateTime(FormatHandler.AlterTimeFormat(commentPlus.getCreateTime()));
        }
        return list;
    }

    public int deleteComment(String commentID) throws Exception
    {
        commentMapper.deleteComment(commentID);
        return 0;
    }
}
