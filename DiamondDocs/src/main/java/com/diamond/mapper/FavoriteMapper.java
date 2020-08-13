package com.diamond.mapper;

import com.diamond.dto.DocPreview;
import com.diamond.pojo.Doc;
import com.diamond.pojo.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface FavoriteMapper
{
    //key:docID, userID
    int addFavorite(Map<String, Object> map);

    List<DocPreview> getFavoriteDocByUserID(@Param("userID") String userID);

    //key:docID, userID
    int deleteFavorite(Map<String, Object> map);
}
