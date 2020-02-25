package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.entity.db.CategoryEntity;
import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.PostDTO;
import com.haramasu.daomin.entity.vos.PostSummaryVO;
import com.haramasu.daomin.repo.CategoryRepo;
import com.haramasu.daomin.repo.PostRepo;
import com.haramasu.daomin.repo.TagRepo;
import com.haramasu.daomin.repo.dsl.PostDslRepo;
import com.haramasu.daomin.repo.dsl.TagDslRepo;
import com.haramasu.daomin.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    private TagRepo tagRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostEntity addNewPost(PostDTO postDTO) {
        PostEntity postEntity=new PostEntity();
        BeanUtils.copyProperties(postDTO,postEntity);
        return postRepo.save(postEntity);
    }

    @Override
    public PostEntity modifyPost(Integer postId, PostDTO postDTO) {
        PostEntity postEntity = postRepo.findById(postId).get();
        BeanUtils.copyProperties(postDTO,postEntity);

        postEntity.getTagEntities().clear();
        for (Integer tagId : postDTO.getTagIds()) {
            TagEntity tagEntity = tagRepo.findById(tagId).get();

            if(!postEntity.getTagEntities().contains(tagEntity)){
                postEntity.getTagEntities().add(tagEntity);
            }
        }

        if(postDTO.getCategoryId()!=null){
            CategoryEntity categoryEntity = categoryRepo.findById(postDTO.getCategoryId()).get();
            if(categoryEntity!=null){
                postEntity.setCategoryEntity(categoryEntity);
            }
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
}
