package com.diamond.mapper;

import com.diamond.pojo.Suggestion;
import org.apache.ibatis.annotations.Param;

public interface SuggestionMapper
{

    Suggestion getSuggestionByID(@Param("id") String id);

}
