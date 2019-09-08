package com.haramasu.daomin.repo.dsl;

import com.haramasu.daomin.Daomin3ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class TagDslRepoTest extends Daomin3ApplicationTests {

    @Autowired
    TagDslRepo tagDslRepo;

    @Test
    public void findTagsByPostIds() {
        tagDslRepo.findTagsByPostIds(Arrays.asList(1,3,4));
    }
}
