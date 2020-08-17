package com.diamond.service;

import com.diamond.mapper.TemplateMapper;
import com.diamond.pojo.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {

    @Autowired
    private TemplateMapper templateMapper;

    public List<Template> getAllTemplate(){
        return templateMapper.getAllTemplate();
    }

}
