package com.diamond.mapper;

import com.diamond.pojo.Template;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TemplateMapper {

    List<Template> getAllTemplate();

    Template getTemplateByTemplateID(@Param("templateID") String templateID);

}
