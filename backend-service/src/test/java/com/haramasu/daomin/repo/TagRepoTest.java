package com.haramasu.daomin.repo;

import com.haramasu.daomin.Daomin3ApplicationTests;
import com.haramasu.daomin.entity.db.TagEntity;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagRepoTest extends Daomin3ApplicationTests {

    @Autowired
    TagRepo tagRepo;

    @Test
    public void Sd(){
        List<TagEntity> all = tagRepo.findAll();
        System.out.println(all);
    }

}
