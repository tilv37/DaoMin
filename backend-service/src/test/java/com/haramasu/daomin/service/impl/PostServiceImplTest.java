package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.Daomin3ApplicationTests;
import com.haramasu.daomin.service.PostService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.junit.Assert.*;

public class PostServiceImplTest extends Daomin3ApplicationTests {

    @Autowired
    private PostService postService;

    @Test
    public void getAllPostSummary() {

        postService.getAllPostSummary(PageRequest.of(0,10));
    }
}