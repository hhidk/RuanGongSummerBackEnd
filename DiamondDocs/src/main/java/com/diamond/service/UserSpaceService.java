package com.diamond.service;

import com.diamond.dto.DocPreview;
import com.diamond.mapper.BrowsesMapper;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.FavoriteMapper;
import com.diamond.pojo.Doc;
import com.diamond.pojo.Favorite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Service
public class UserSpaceService {

    @Autowired
    private DocMapper docMapper;
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private BrowsesMapper browsesMapper;

    public List<DocPreview> getMyDocs(String userID, int bytes) throws Exception{
        List<Doc> list = docMapper.getDocByUserID(userID);
        List<DocPreview> docPreviewList = DocPreview.getPreviewList(list, bytes);
        for(DocPreview docPreview : docPreviewList){
            Favorite favorite = favoriteMapper.checkExistFavorite(userID, docPreview.getDocID());
            if(favorite != null)
                docPreview.setIsFavorite(1);
            else
                docPreview.setIsFavorite(0);
        }
        return docPreviewList;
    }

    public List<DocPreview> getRecentDocs(String userID, int bytes) throws Exception{
        List<Doc> list = browsesMapper.getBrowsesDocByUserID(userID);
        LinkedHashSet<Doc> hashSet = new LinkedHashSet<>(list);
        list = new ArrayList<>(hashSet);
        List<DocPreview> docPreviewList = DocPreview.getPreviewList(list, bytes);
        for(DocPreview docPreview : docPreviewList) {
            Favorite favorite = favoriteMapper.checkExistFavorite(userID, docPreview.getDocID());
            if (favorite != null)
                docPreview.setIsFavorite(1);
            else
                docPreview.setIsFavorite(0);
        }
        return docPreviewList;
    }

    public List<DocPreview> getFavoriteDocs(String userID, int bytes) throws Exception{
        List<Doc> list = favoriteMapper.getFavoriteDocByUserID(userID);
        return DocPreview.getPreviewList(list, bytes);
    }

    public List<DocPreview> getDeletedDocs(String userID, int bytes) throws Exception{
        List<Doc> list = docMapper.getDeletedDocByUserID(userID);
        return DocPreview.getPreviewList(list, bytes);
    }

    public int isFavorite(String userID, String docID) throws Exception{
        Favorite favorite = favoriteMapper.checkExistFavorite(userID, docID);
        if(favorite != null)
            return 1;
        else
            return 0;
    }

}
