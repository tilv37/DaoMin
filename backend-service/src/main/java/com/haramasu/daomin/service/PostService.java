package com.haramasu.daomin.service;

import com.haramasu.daomin.entity.db.PostEntity;
import org.springframework.data.domain.Page;

public interface PostService {

    PostEntity addNewPost();

    Page<PostEntity> getAllPost(int pageNo, int pageSize);
}
