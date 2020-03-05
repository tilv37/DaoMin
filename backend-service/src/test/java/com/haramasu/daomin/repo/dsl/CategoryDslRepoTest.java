package com.haramasu.daomin.repo.dsl;

import com.haramasu.daomin.Daomin3ApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class CategoryDslRepoTest extends Daomin3ApplicationTests {

    @Autowired
    CategoryDslRepo categoryDslRepo;

    @Test
    public void findWithPostNo() {
        categoryDslRepo.findWithPostNo(PageRequest.of(0,10));
    }

    @Test
    public void findAllNames(){
        List<String> allCategoryNames = categoryDslRepo.findAllCategoryNames();
        System.out.println(allCategoryNames);
    }
}
