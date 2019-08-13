package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.entity.db.CategoryEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.repo.CategoryRepo;
import com.haramasu.daomin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public ResponseDTO<CategoryEntity> addNewCategory(String categoryName) {
        if(isCategoryExist(categoryName)){
            return ResponseDTO.error("该分类已经存在，请更换");
        }
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setCategoryName(categoryName);
        categoryEntity.setCreateTM(new Date());
        categoryEntity.setModifyTM(new Date());
        CategoryEntity categoryEntity1 = categoryRepo.save(categoryEntity);
        return ResponseDTO.success(categoryEntity1);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public List<CategoryEntity> getAllCategory() {
        return categoryRepo.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @Override
    public boolean isCategoryExist(String categoryName) {
        return categoryRepo.countByCategoryName(categoryName)>0?true:false;
    }
}
