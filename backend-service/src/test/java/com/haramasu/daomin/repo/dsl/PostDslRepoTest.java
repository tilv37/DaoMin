package com.haramasu.daomin.repo.dsl;

import com.haramasu.daomin.Daomin3ApplicationTests;
import com.haramasu.daomin.entity.vos.ArchiveVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostDslRepoTest extends Daomin3ApplicationTests {

    @Autowired
    PostDslRepo postDslRepo;

    @Test
    public void getJpaInterface() {
    }

    @Test
    public void findAllPageable() {
    }

    @Test
    public void findArchiveCountBy() {
        List<ArchiveVO> archiveCountBy = postDslRepo.findArchiveCountBy();
        System.out.println(archiveCountBy);
    }
}