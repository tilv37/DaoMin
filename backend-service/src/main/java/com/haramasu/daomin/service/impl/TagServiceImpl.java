package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.repo.TagRepo;
import com.haramasu.daomin.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class TagServiceImpl implements TagService {

    @Autowired
    TagRepo tagRepo;

    @Override
    public ResponseDTO<TagEntity> addNewTag(String tagName) {
        if(isTagExist(tagName)){
            return ResponseDTO.error("该标签已存在，请更换");
        }
        TagEntity tagEntity=new TagEntity();
        tagEntity.setCreateTM(new Date());
        tagEntity.setModifyTM(new Date());
        tagEntity.setTagName(tagName);
        TagEntity tagEntity1 = tagRepo.save(tagEntity);
        return ResponseDTO.success(tagEntity1);
    }

    @Override
    public void deleteTag(Integer id) {
        tagRepo.deleteById(id);
    }

    @Override
    public List<TagEntity> getAllTags() {
        return tagRepo.findAll();
    }

    @Override
    public Page<TagEntity> getTagsPageable(int pageNo,int pageSize) {
        return tagRepo.findAll(PageRequest.of(pageNo,pageSize,Sort.by("id")));
    }

    @Override
    public boolean isTagExist(String tagName) {
        return tagRepo.countByTagName(tagName)>0?true:false;
    }
}
