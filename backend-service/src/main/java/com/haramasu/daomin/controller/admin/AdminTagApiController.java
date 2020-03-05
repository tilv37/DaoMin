package com.haramasu.daomin.controller.admin;

import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.vos.TagVO;
import com.haramasu.daomin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/26/2020
 */
@RestController
@RequestMapping("api/admin/v1/")
public class AdminTagApiController {

    @Autowired
    private TagService tagService;


    @GetMapping(value = "tags")
    public ResponseDTO<Page<TagVO>> getTags(@RequestParam(name = "pageNo",defaultValue = "0")int pageNo,
                                            @RequestParam(name = "pageSize",defaultValue = "10")int pageSize){

        Page<TagVO> tagWithPostNoPageable = tagService.getTagWithPostNoPageable(pageNo, pageSize);
        return ResponseDTO.success(tagWithPostNoPageable);
    }

    @GetMapping(value = "tagNames")
    public ResponseDTO<List<String>> getTagNames(){

        List<String> allTagNames = tagService.getAllTagNames();
        return ResponseDTO.success(allTagNames);
    }

    @PostMapping(value = "tags/{tagName}")
    public ResponseDTO<TagEntity> addNewTag(@PathVariable(name = "tagName")String tagName){
        ResponseDTO<TagEntity> tagEntityResponseDTO = tagService.addNewTag(tagName);
        return tagEntityResponseDTO;
    }
}
