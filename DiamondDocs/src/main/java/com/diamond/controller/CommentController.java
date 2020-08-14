package com.diamond.controller;

import com.diamond.dto.CommentPlus;
import com.diamond.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/addComment")
    public int addComment(@RequestParam("docID") String docID, @RequestParam("userID") String userID,
                          @RequestParam("commentContent") String commentContent,@RequestParam("replyID") String replyID)
    {
        try {
            return commentService.addComment(docID, userID, commentContent, replyID);
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }

    @RequestMapping("/getDocComment")
    public List<CommentPlus> getDocComment(@RequestParam("docID") String docID)
    {
        try {
            return commentService.getDocComment(docID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/getUserComment")
    public List<CommentPlus> getUserComment(@RequestParam("userID") String userID)
    {
        try {
            return commentService.getUserComment(userID);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/deleteComment")
    public int deleteComment(@RequestParam("commentID") String commentID)
    {
        try {
            return commentService.deleteComment(commentID);
        }
        catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }
}
