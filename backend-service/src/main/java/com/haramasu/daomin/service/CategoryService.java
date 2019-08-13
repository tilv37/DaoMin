package com.haramasu.daomin.service;

import com.haramasu.daomin.entity.db.CategoryEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;

import java.util.List;

public interface CategoryService {

    ResponseDTO<CategoryEntity> addNewCategory(String categoryName);

    void deleteCategory(Integer id);

    List<CategoryEntity> getAllCategory();

    boolean isCategoryExist(String categoryName);
}
