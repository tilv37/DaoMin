package com.haramasu.daomin.repo.impl;

import com.haramasu.daomin.entity.vos.TagVO;
import com.haramasu.daomin.repo.PostTagRelationRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/19/2019
 */
@Repository
public class PostTagRelationRepoImpl extends AbstractNativeQueryRepo implements PostTagRelationRepo {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * select
     * 	te.id,
     * 	te.tag_name as tagName,
     * 	p.postNo
     * from
     * 	tag_entity as te
     * left join (
     * 	select
     * 		ptr.tag_id as tid,
     * 		count(1) as postNo
     * 	from
     * 		post_tag_relation as ptr
     * 	group by
     * 		ptr.tag_id) as p on
     * 	te.id = p.tid
     * order by
     * 	p.tid
     * limit 100
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<TagVO> countGroupByTagId(int pageNo,int pageSize) {

        Map<String,Object> params=new HashMap<>();
        Map<String,Object> countParams=new HashMap<>();

        //构造常规查询结果sql
        StringBuilder sb=new StringBuilder();
        sb.append("select te.id as tid, te.tag_name as tagName, count(ptr.post_id) as postNo from dm_tag as te ");
        sb.append("left join post_tag_relation as ptr on te.id = ptr.tag_id ");
        sb.append("group by te.id order by te.id");

        Query nativeQuery = entityManager.createNativeQuery(sb.toString(), "TagWithPostCount");
        nativeQuery.setFirstResult(pageNo*pageSize);
        nativeQuery.setMaxResults(pageSize);
        List<TagVO> resultList = nativeQuery.getResultList();

        //查询总数，用于分页
        StringBuilder countSb=new StringBuilder();
        countSb.append("select count(1) from tag_entity ");
        Query nativeQuery1 = entityManager.createNativeQuery(countSb.toString());
        BigInteger count = (BigInteger)nativeQuery1.getSingleResult();
        Page<TagVO> tagVOPage=new PageImpl<TagVO>(resultList, PageRequest.of(pageNo,pageSize),count.longValue());
        return tagVOPage;
    }

}
