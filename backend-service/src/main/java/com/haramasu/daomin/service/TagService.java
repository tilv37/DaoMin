package com.haramasu.daomin.service;

import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.vos.TagVO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TagService {

    ResponseDTO<TagEntity> addNewTag(String tagName);

    void deleteTag(Integer id);

    List<TagEntity> getAllTags();

    Page<TagEntity> getTagsPageable(int pageNo,int pageSize);

    boolean isTagExist(String tagName);

    Page<TagVO>  getTagWithPostNoPageable(int pageNo,int pageSize);

    void  dslTest();
}
