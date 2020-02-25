package com.haramasu.daomin.service;

import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.dto.PostDTO;
import com.haramasu.daomin.entity.vos.PostSummaryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostEntity addNewPost(PostDTO postDTO);

    PostEntity modifyPost(Integer postId,PostDTO postDTO);

    PostEntity getOneById(int postId);

    Page<PostEntity> getAllPost(int pageNo, int pageSize);

    Page<PostSummaryVO> getAllPostSummary(Pageable pageable);

    void deletePostById(Integer postId);
}
