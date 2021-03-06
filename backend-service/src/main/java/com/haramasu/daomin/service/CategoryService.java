package com.haramasu.daomin.service;

import com.haramasu.daomin.entity.db.CategoryEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.vos.CategoryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    ResponseDTO<CategoryEntity> addNewCategory(String categoryName);

    void deleteCategory(Integer id);

    List<CategoryEntity> getAllCategory();

    CategoryEntity getCategoryByName(String cateName);

    List<String> getAllCateNames();

    boolean isCategoryExist(String categoryName);

    Page<CategoryVO> getCategoryPageable(Pageable pageable);
}
