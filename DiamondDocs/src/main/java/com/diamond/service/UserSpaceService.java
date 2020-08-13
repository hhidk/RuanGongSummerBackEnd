package com.diamond.service;

import com.diamond.dto.DocPreview;
import com.diamond.mapper.BrowsesMapper;
import com.diamond.mapper.DocMapper;
import com.diamond.mapper.FavoriteMapper;
import com.diamond.pojo.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSpaceService {

    @Autowired
    private DocMapper docMapper;
    @Autowired
    private FavoriteMapper favoriteMapper;
    @Autowired
    private BrowsesMapper browsesMapper;

    public List<DocPreview> getMyDocs(String userID) throws Exception{
        List<Doc> list = docMapper.getDocByUserID(userID);
        return DocPreview.getPreviewList(list);
    }

    public List<DocPreview> getRecentDocs(String userID) throws Exception{
        return browsesMapper.getBrowsesDocByUserID(userID);
    }

    public List<DocPreview> getFavoriteDocs(String userID) throws Exception{
        return favoriteMapper.getFavoriteDocByUserID(userID);
    }

    public List<DocPreview> getDeletedDocs(String userID) throws Exception{
        List<Doc> list = docMapper.getDeletedDocByUserID(userID);
        return DocPreview.getPreviewList(list);
    }

}
