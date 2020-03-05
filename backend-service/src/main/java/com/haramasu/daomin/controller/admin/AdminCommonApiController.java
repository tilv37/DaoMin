package com.haramasu.daomin.controller.admin;

import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.service.TranslateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/26/2020
 */
@RestController
@RequestMapping("api/admin/v1/")
public class AdminCommonApiController {

    @Autowired
    private TranslateService translateService;

    @GetMapping(value = "translate")
    public ResponseDTO<String> translate(String q){
        String translate = translateService.translate(q);
        if(StringUtils.isNotBlank(translate)){
            String replace = StringUtils.replace(translate, " ", "-");
            return ResponseDTO.success(replace);
        }
        return ResponseDTO.success(translate);
    }
}
