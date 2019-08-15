package com.haramasu.daomin.controller;

import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.viewo.TagVO;
import com.haramasu.daomin.service.CategoryService;
import com.haramasu.daomin.service.PostService;
import com.haramasu.daomin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    PostService postService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    TagService tagService;

    @GetMapping(value = "tags")
    public ResponseDTO<Page<TagVO>> getTags(@RequestParam(name = "pageNo",defaultValue = "0")int pageNo,
                                          @RequestParam(name = "pageSize",defaultValue = "10")int pageSize){
        Page<TagEntity> tagsPageable = tagService.getTagsPageable(pageNo, pageSize);
        List<TagVO> tagVOS = tagsPageable.getContent().stream().map(x -> {
            TagVO tagVO = new TagVO();
            tagVO.setId(x.getId());
            tagVO.setTagName(x.getTagName());
            tagVO.setPostCount(x.getPostEntityList().size());
            return tagVO;
        }).collect(Collectors.toList());
        Page<TagVO> tagVOPage=new PageImpl<TagVO>(tagVOS,tagsPageable.getPageable(),tagsPageable.getTotalElements());
        return ResponseDTO.success(tagVOPage);
    }

    @PostMapping(value = "tags/{tagName}")
    public ResponseDTO<TagEntity> addNewTag(@PathVariable(name = "tagName")String tagName){
        ResponseDTO<TagEntity> tagEntityResponseDTO = tagService.addNewTag(tagName);
        return tagEntityResponseDTO;
    }
}
