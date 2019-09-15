package com.haramasu.daomin.controller;

import com.haramasu.daomin.entity.db.CategoryEntity;
import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.PostDTO;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.viewo.CategoryVO;
import com.haramasu.daomin.entity.viewo.PostSummaryVO;
import com.haramasu.daomin.entity.viewo.TagVO;
import com.haramasu.daomin.service.CategoryService;
import com.haramasu.daomin.service.PostService;
import com.haramasu.daomin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@RestController
@RequestMapping("api/admin/v1/")
public class AdminApiController {

    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    @GetMapping(value = "tags")
    public ResponseDTO<Page<TagVO>> getTags(@RequestParam(name = "pageNo",defaultValue = "0")int pageNo,
                                          @RequestParam(name = "pageSize",defaultValue = "10")int pageSize){

        Page<TagVO> tagWithPostNoPageable = tagService.getTagWithPostNoPageable(pageNo, pageSize);
        return ResponseDTO.success(tagWithPostNoPageable);
    }

    @PostMapping(value = "tags/{tagName}")
    public ResponseDTO<TagEntity> addNewTag(@PathVariable(name = "tagName")String tagName){
        ResponseDTO<TagEntity> tagEntityResponseDTO = tagService.addNewTag(tagName);
        return tagEntityResponseDTO;
    }

    @PostMapping(value = "category/{catgoryName}")
    public ResponseDTO<CategoryEntity> addNewCategory(@PathVariable(name = "catgoryName")String catgoryName){
        ResponseDTO<CategoryEntity> categoryEntityResponseDTO = categoryService.addNewCategory(catgoryName);
        return categoryEntityResponseDTO;
    }

    @GetMapping(value = "category")
    public ResponseDTO<Page<CategoryVO>> getCategories(@RequestParam(name = "pageNo",defaultValue = "0")int pageNo,
                                                       @RequestParam(name = "pageSize",defaultValue = "10")int pageSize){
        Page<CategoryVO> categoryPageable = categoryService.getCategoryPageable(PageRequest.of(pageNo, pageSize));
        return ResponseDTO.success(categoryPageable);
    }

    @GetMapping(value = "post")
    public ResponseDTO<Page<PostSummaryVO>> getPosts(@RequestParam(name = "pageNo",defaultValue = "0")int pageNo,
                                               @RequestParam(name = "pageSize",defaultValue = "10")int pageSize){
        Page<PostSummaryVO> allPostSummary = postService.getAllPostSummary(PageRequest.of(pageNo, pageSize));
        return ResponseDTO.success(allPostSummary);
    }

    @DeleteMapping(value = "post/{postId}")
    public ResponseDTO<String> deletePost(@PathVariable(name = "postId") Integer postId){
        postService.deletePostById(postId);
        return ResponseDTO.success();
    }

    @PostMapping(value = "post")
    public ResponseDTO<String> addNewPost(@RequestBody PostDTO postDTO){
        PostEntity postEntity = postService.addNewPost(postDTO);
        return ResponseDTO.success();
    }
}
