package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.entity.db.QTagEntity;
import com.haramasu.daomin.entity.db.TagEntity;
import com.haramasu.daomin.entity.dto.ResponseDTO;
import com.haramasu.daomin.entity.dto.TagDTO;
import com.haramasu.daomin.entity.viewo.TagVO;
import com.haramasu.daomin.repo.PostTagRelationRepo;
import com.haramasu.daomin.repo.TagRepo;
import com.haramasu.daomin.repo.dsl.TagDslRepo;
import com.haramasu.daomin.service.TagService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepo tagRepo;
    @Autowired
    private PostTagRelationRepo postTagRelationRepo;
    @Autowired
    private TagDslRepo tagDslRepo;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    EntityManager entityManager;

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

    @Override
    public Page<TagVO> getTagWithPostNoPageable(int pageNo,int pageSize) {
        Page<TagVO> tagVOPage = postTagRelationRepo.countGroupByTagId(pageNo, pageSize);
        return tagVOPage;
    }




    @Override
    public void dslTest() {
        QTagEntity tagEntity=QTagEntity.tagEntity;

        TagEntity tagEntity1 = jpaQueryFactory.selectFrom(tagEntity)
                .where(tagEntity.id.eq(3))
                .fetchOne();

        List<TagDTO> collect = jpaQueryFactory.select(
                tagEntity.id,
                tagEntity.tagName
        )
                .from(tagEntity)
                .offset(0)
                .limit(10)
                .fetch()
                .stream()
                .map(x -> {
                    TagDTO tagDTO = new TagDTO();
                    tagDTO.setId(x.get(tagEntity.id));
                    tagDTO.setTagName(x.get(tagEntity.tagName));
                    return tagDTO;
                })
                .collect(Collectors.toList());
    }
}