package com.haramasu.daomin.controller.blog;

import com.haramasu.daomin.service.CategoryService;
import com.haramasu.daomin.service.PostService;
import com.haramasu.daomin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 3/20/2020
 */
public abstract class BaseController {

    @Autowired
    protected TagService tagService;
    @Autowired
    protected CategoryService categoryService;
    @Autowired
    protected PostService postService;

    public Map<String,Object> getTagNamesAndCateNames(){
        List<String> allTagNames = tagService.getAllTagNames();
        List<String> allCateNames = categoryService.getAllCateNames();
        return new HashMap<String,Object>(){{
            put("allTagNames",allTagNames);
            put("allCateNames",allCateNames);
        }};

    }
}
