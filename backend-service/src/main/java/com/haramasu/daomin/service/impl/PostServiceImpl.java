package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.dto.PostDTO;
import com.haramasu.daomin.entity.viewo.PostSummaryVO;
import com.haramasu.daomin.repo.PostRepo;
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

import java.util.Date;
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

    @Override
    public PostEntity addNewPost(PostDTO postDTO) {
        PostEntity postEntity=new PostEntity();
        BeanUtils.copyProperties(postDTO,postEntity);
        postEntity.setTitleUrl("/test");
        return postRepo.save(postEntity);
    }

    @Override
    public Page<PostEntity> getAllPost(int pageNo, int pageSize) {
        return  postRepo.findAll(PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "createTM")));
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
