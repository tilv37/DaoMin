package com.haramasu.daomin.repo;

import com.haramasu.daomin.entity.viewo.TagVO;
import org.springframework.data.domain.Page;

public interface PostTagRelationRepo {

    Page<TagVO> countGroupByTagId(int pageNo, int pageSize);
}
