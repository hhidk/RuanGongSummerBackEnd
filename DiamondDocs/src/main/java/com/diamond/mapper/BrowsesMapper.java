package com.diamond.mapper;

import com.diamond.dto.DocPreview;
import com.diamond.pojo.Browses;
import com.diamond.pojo.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BrowsesMapper {

    public int addBrowses(Browses browses);

    public List<Doc> getBrowsesDocByUserID(String userID);

}
