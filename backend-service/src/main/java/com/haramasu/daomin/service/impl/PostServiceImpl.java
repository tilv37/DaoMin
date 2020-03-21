package com.haramasu.daomin.service.impl;

import cn.hutool.core.date.DateUtil;
import com.haramasu.daomin.entity.db.CategoryEntity;
import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.PostDTO;
import com.haramasu.daomin.entity.dto.PostSummaryAndImageUrl;
import com.haramasu.daomin.entity.vos.ArchiveVO;
import com.haramasu.daomin.entity.vos.PostSummaryVO;
import com.haramasu.daomin.entity.vos.PostVO;
import com.haramasu.daomin.repo.PostRepo;
import com.haramasu.daomin.repo.dsl.PostDslRepo;
import com.haramasu.daomin.repo.dsl.TagDslRepo;
import com.haramasu.daomin.service.CategoryService;
import com.haramasu.daomin.service.PostService;
import com.haramasu.daomin.service.TagService;
import com.haramasu.daomin.util.PostUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private PostDslRepo postDslRepo;
    @Autowired
    private TagDslRepo tagDslRepo;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    @Override
    public PostEntity addNewPost(PostDTO postDTO) {
        PostEntity postEntity=new PostEntity();
        BeanUtils.copyProperties(postDTO,postEntity);
        //提取摘要和第一个图片
        if(StringUtils.isBlank(postDTO.getSummary())){
            PostSummaryAndImageUrl summaryFromMarkdownText = PostUtil.getSummaryFromMarkdownText(postDTO.getContent());
            postEntity.setSummary(summaryFromMarkdownText.getSummary());
            postEntity.setFirstImg(summaryFromMarkdownText.getFirstImageUri());
        }
        //开始添加tag和category
        return modifyPostTagAndCate(postDTO, postEntity);
    }

    @Override
    public PostEntity modifyPost(Integer postId, PostDTO postDTO) {
        PostEntity postEntity = postRepo.findById(postId).get();
        BeanUtils.copyProperties(postDTO,postEntity);
        //处理tag和category
        postEntity.getTagEntities().clear();
        postEntity.setCategoryEntity(null);
        return modifyPostTagAndCate(postDTO, postEntity);
    }

    private PostEntity modifyPostTagAndCate(PostDTO postDTO, PostEntity postEntity) {
        if(StringUtils.isNotBlank(postDTO.getCategory())){
            CategoryEntity categoryEntity = categoryService.getCategoryByName(postDTO.getCategory());
            postEntity.setCategoryEntity(categoryEntity);
        }
        if(postDTO.getTags()!=null && postDTO.getTags().size()!=0){
            List<TagEntity> tags = tagService.getTagsByNames(postDTO.getTags());
            postEntity.setTagEntities(new HashSet<>(tags));
        }
            return postRepo.save(postEntity);
    }

    @Override
    public PostEntity getOneById(int postId) {
        return postRepo.findById(postId).get();
    }

    @Override
    public Page<PostEntity> getAllPost(int pageNo, int pageSize) {
        Page<PostEntity> postEntities = postRepo.findAll(PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "createTime")));
        return postEntities;
    }

    @Override
    public Page<PostSummaryVO> getAllPostSummary(Pageable pageable) {
        Page<PostSummaryVO> allPageable = postDslRepo.findAllPageable(pageable);
        //获取所有post的id，然后关联查询出所有的tag。最后再填充回去
        List<PostSummaryVO> content = allPageable.getContent();
        List<Integer> ids = content.stream().map(postSummaryVO -> postSummaryVO.getId()).collect(Collectors.toList());
        Map<Integer, String> tagsByPostIds = tagDslRepo.findTagsByPostIds(ids);
        for (PostSummaryVO postSummaryVO : content) {
            if(tagsByPostIds.containsKey(postSummaryVO.getId())){
                postSummaryVO.setTags(tagsByPostIds.get(postSummaryVO.getId()));
            }
        }
        return allPageable;
    }

    @Override
    public void deletePostById(Integer postId) {
        postRepo.deleteById(postId);
    }

    @Override
    public Page<PostVO> loadHomePageData(int pageNo, int pageSize) {
        Page<PostEntity> allPost = getAllPost(pageNo-1, pageSize);
        List<PostVO> postVOS = allPost.getContent().stream().map(postEntity -> {
            PostVO postVO = new PostVO();
            postVO.setCategoryNames(postEntity.getCategoryEntity().getCategoryName());
            postVO.setTitle(postEntity.getTitle());
            postVO.setTitleEn(postEntity.getTitleEn());
            postVO.setCreateTime(DateUtil.format(postEntity.getCreateTime(),"EEE MMM d,yyyy"));
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
        return postVOPage;
    }

    @Override
    @Cacheable(cacheNames = "archives")
    public List<ArchiveVO> findArchive() {
        return postDslRepo.findArchiveCountBy();
    }

    @Override
    public PostVO findByTitleEn(String titleEn) {
        PostEntity postEntity = postRepo.findTopByTitleEn(titleEn);
        PostVO postVO=new PostVO();
        if(postEntity!=null){
            postVO.setTitle(postEntity.getTitle());
            postVO.setCreateTime(DateUtil.format(postEntity.getCreateTime(),"EEE MMM d,yyyy"));
            postVO.setCategoryNames(postEntity.getCategoryEntity().getCategoryName());
            postVO.setTagNames(postEntity.getTagEntities().stream().map(TagEntity::getTagName).collect(Collectors.toList()));
            postVO.setSummaryContent(postEntity.getSummary());
           //这里讲markdown转为html直接渲染
            String markdownToHtml = PostUtil.markdownToHtml(postEntity.getContent());
            postVO.setContent(markdownToHtml);
        }
        return postVO;
    }

    public static void main(String[] args) {
        String format = DateUtil.format(new Date(), "EEE MMM d,yyyy");
        System.out.println(format);
    }
}
