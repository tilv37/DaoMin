package com.haramasu.daomin.controller.admin;

import com.haramasu.daomin.entity.db.CategoryEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.vos.CategoryVO;
import com.haramasu.daomin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/26/2020
 */
@RestController
@RequestMapping("api/admin/v1/")
public class AdminCateApiController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "category/{catgoryName}")
    public ResponseDTO<CategoryEntity> addNewCategory(@PathVariable(name = "catgoryName")String catgoryName){
        ResponseDTO<CategoryEntity> categoryEntityResponseDTO = categoryService.addNewCategory(catgoryName);
        return categoryEntityResponseDTO;
    }

    @GetMapping(value = "category")
    public ResponseDTO<Page<CategoryVO>> getCategories(@RequestParam(name = "pageNo",defaultValue = "0")int pageNo,
                                                       @RequestParam(name = "pageSize",defaultValue = "10")int pageSize){
        Page<CategoryVO> categoryPageable = categoryService.getCategoryPageable(PageRequest.of(pageNo, pageSize));
        return ResponseDTO.success(categoryPageable);
    }
    @GetMapping(value = "categoryNames")
    public ResponseDTO<List<String>> getCategoryNames(){
        List<String> allCateNames = categoryService.getAllCateNames();
        return ResponseDTO.success(allCateNames);
    }

}
