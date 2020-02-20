package com.haramasu.daomin.repo.dsl;

import com.haramasu.daomin.entity.db.QCategoryEntity;
import com.haramasu.daomin.entity.db.QPostEntity;
import com.haramasu.daomin.entity.viewo.PostSummaryVO;
import com.haramasu.daomin.repo.PostRepo;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
                        .createTM(tuple.get(postEntity.createTime))
                        .build()).collect(Collectors.toList());
        return new PageImpl<>(postSummaryVOList,pageable,tupleQueryResults.getTotal());
    }
}
