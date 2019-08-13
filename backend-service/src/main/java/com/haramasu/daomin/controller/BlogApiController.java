package com.haramasu.daomin.controller;

import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.viewo.PostVO;
import com.haramasu.daomin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@RestController
@RequestMapping("api/v1/")
public class BlogApiController {

    @Autowired
    PostService postService;

    @GetMapping(value = "post")
    public ResponseDTO<Page<PostVO>> getAllPost(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int pageSize){
        Page<PostEntity> allPost = postService.getAllPost(pageNo, pageSize);
        List<PostEntity> content = allPost.getContent();
        //转换成页面需要的VO
        return null;
    }
}
