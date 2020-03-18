package com.haramasu.daomin.controller.blog;

import cn.hutool.core.date.DateUtil;
import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.PostSummaryAndImageUrl;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.vos.PostVO;
import com.haramasu.daomin.service.PostService;
import com.haramasu.daomin.util.PostUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@RestController
@RequestMapping("api/v1/")
public class PostApiController {

    @Autowired
    PostService postService;

    @GetMapping(value = "post")
    public ResponseDTO<Page<PostVO>> getAllPost(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "5") int pageSize){
        Page<PostEntity> allPost = postService.getAllPost(pageNo-1, pageSize);
        List<PostVO> postVOS = allPost.getContent().stream().map(postEntity -> {
            PostVO postVO = new PostVO();
            postVO.setCategoryNames(postEntity.getCategoryEntity().getCategoryName());
            postVO.setTitle(postEntity.getTitle());
            postVO.setTitleEn(postEntity.getTitleEn());
            postVO.setCreateTime(DateUtil.formatTime(postEntity.getCreateTime()));
            if (StringUtils.isBlank(postEntity.getSummary())) {
                PostSummaryAndImageUrl postSummaryAndImageUrl = PostUtil.getSummaryFromMarkdownText(postEntity.getContent());
                postVO.setSummaryContent(postSummaryAndImageUrl.getSummary());
            }else{
                postVO.setSummaryContent(postEntity.getSummary());
            }
            List<String> tags = postEntity.getTagEntities().stream().map(TagEntity::getTagName).collect(Collectors.toList());
            postVO.setTagNames(tags);
            return postVO;
        }).collect(Collectors.toList());
        PageImpl<PostVO> postVOPage = new PageImpl<>(postVOS, allPost.getPageable(), allPost.getTotalElements());
        return ResponseDTO.success(postVOPage);
    }
}
