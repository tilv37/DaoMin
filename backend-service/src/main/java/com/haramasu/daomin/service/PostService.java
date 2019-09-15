package com.haramasu.daomin.service;

import com.haramasu.daomin.entity.db.PostEntity;
import com.haramasu.daomin.entity.dto.PostDTO;
import com.haramasu.daomin.entity.viewo.PostSummaryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    PostEntity addNewPost(PostDTO postDTO);

    Page<PostEntity> getAllPost(int pageNo, int pageSize);

    Page<PostSummaryVO> getAllPostSummary(Pageable pageable);

    void deletePostById(Integer postId);
}
