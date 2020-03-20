package com.haramasu.daomin.repo.dsl;

import com.haramasu.daomin.entity.db.QCategoryEntity;
import com.haramasu.daomin.entity.db.QPostEntity;
import com.haramasu.daomin.entity.vos.ArchiveVO;
import com.haramasu.daomin.entity.vos.PostSummaryVO;
import com.haramasu.daomin.repo.PostRepo;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 9/3/2019
 */
@Repository
public class PostDslRepo {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public PostRepo getJpaInterface(){
        return this.postRepo;
    }

    public Page<PostSummaryVO> findAllPageable(Pageable pageable){
        QPostEntity postEntity=QPostEntity.postEntity;
        QCategoryEntity categoryEntity=QCategoryEntity.categoryEntity;
        QueryResults<Tuple> tupleQueryResults = jpaQueryFactory.select(
                postEntity.id,
                postEntity.title,
                postEntity.createTime,
                categoryEntity.categoryName
        )
                .from(postEntity)
                .leftJoin(categoryEntity)
                .on(postEntity.categoryEntity.id.eq(categoryEntity.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<PostSummaryVO> postSummaryVOList=tupleQueryResults.getResults().stream().map(tuple ->
                PostSummaryVO
                        .builder()
                        .id(tuple.get(postEntity.id))
                        .title(tuple.get(postEntity.title))
                        .category(tuple.get(categoryEntity.categoryName))
                        .createTime(tuple.get(postEntity.createTime))
                        .build()).collect(Collectors.toList());
        return new PageImpl<>(postSummaryVOList,pageable,tupleQueryResults.getTotal());
    }

    public List<ArchiveVO> findArchiveCountBy(){
        return findArchiveCountBy(1,null);
    }

    public List<ArchiveVO> findArchiveCountBy(int dateType, Date date){
        QPostEntity postEntity=QPostEntity.postEntity;
        List<Tuple> fetch = jpaQueryFactory.select(
                postEntity.createTime.year(),
                postEntity.id.count())
                .from(postEntity)
                .groupBy(postEntity.createTime.year())
                .orderBy(postEntity.createTime.year().desc())
                .fetch();

        List<ArchiveVO> collect = fetch.stream().map(tuple -> {
            ArchiveVO archiveVO = new ArchiveVO();
            archiveVO.setArchiveDate(tuple.get(postEntity.createTime.year()).toString());
            archiveVO.setCount(tuple.get(postEntity.id.count()).intValue());
            return archiveVO;
        }).collect(Collectors.toList());

        return collect;
    }
}
