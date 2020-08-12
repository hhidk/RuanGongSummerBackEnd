package com.diamond.mapper;

import com.diamond.pojo.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface FavoriteMapper
{
    int addFavorite(Favorite favorite);

    //应该还需要一个查找的，现在不知道需要返回docID还是Favorite对象，所以没写

    //key:docID, userID
    int deleteFavorite(Map<String, Object> map);
}
