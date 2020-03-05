package com.haramasu.daomin.controller.admin;

import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.PostDTO;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.vos.PostSummaryVO;
import com.haramasu.daomin.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/26/2020
 */
@RestController
@RequestMapping("api/admin/v1/")
public class AdminPostApiController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "post")
    public ResponseDTO<Page<PostSummaryVO>> getPosts(@RequestParam(name = "pageNo",defaultValue = "0")int pageNo,
                                                     @RequestParam(name = "pageSize",defaultValue = "10")int pageSize){
        Page<PostSummaryVO> allPostSummary = postService.getAllPostSummary(PageRequest.of(pageNo, pageSize));
        return ResponseDTO.success(allPostSummary);
    }

    @GetMapping(value = "post/{postId}")
    public ResponseDTO<PostDTO> getOnePostById(@PathVariable(name = "postId") Integer postId){
        PostEntity oneById = postService.getOneById(postId);
        PostDTO dto = PostDTO.builder().category(oneById.getCategoryEntity().getCategoryName()).build();
        BeanUtils.copyProperties(oneById,dto);
        List<String> tagNames = oneById.getTagEntities().stream().map(TagEntity::getTagName).collect(Collectors.toList());
        dto.setTags(tagNames);

        return ResponseDTO.success(dto);
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

    @PutMapping(value = "post/{postId}")
    public ResponseDTO<String>  modifyPost(@PathVariable(name = "postId") Integer postId,@RequestBody PostDTO postDTO){
        PostEntity postEntity = postService.modifyPost(postId, postDTO);
        return ResponseDTO.success();
    }
}
