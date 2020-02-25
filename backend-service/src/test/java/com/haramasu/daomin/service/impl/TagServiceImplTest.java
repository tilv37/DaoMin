package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.Daomin3ApplicationTests;
import com.haramasu.daomin.entity.vos.TagVO;
import com.haramasu.daomin.repo.PostTagRelationRepo;
import com.haramasu.daomin.repo.TagRepo;
import com.haramasu.daomin.service.TagService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public class TagServiceImplTest extends Daomin3ApplicationTests {

    @Autowired
    TagService tagService;
    @Autowired
    TagRepo tagRepo;

    @Autowired
    PostTagRelationRepo postTagRelationRepo;

    @Test
    public void ssdads(){
        Page<TagVO> tagVOPage = postTagRelationRepo.countGroupByTagId(0, 10);
        System.out.println(tagVOPage);
    }

    @Test
    public void dasda(){
        tagService.dslTest();
    }
}
