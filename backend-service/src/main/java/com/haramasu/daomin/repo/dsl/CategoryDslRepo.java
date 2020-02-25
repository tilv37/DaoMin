package com.haramasu.daomin.repo.dsl;

import com.haramasu.daomin.entity.db.QCategoryEntity;
import com.haramasu.daomin.entity.db.QPostEntity;
import com.haramasu.daomin.entity.vos.CategoryVO;
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
 * @date: 8/22/2019
 */
@Repository
public class CategoryDslRepo {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public Page<CategoryVO> findWithPostNo(Pageable pageable){
        QCategoryEntity categoryEntity=QCategoryEntity.categoryEntity;
        QPostEntity postEntity=QPostEntity.postEntity;
        QueryResults<Tuple> tupleQueryResults = jpaQueryFactory.select(
                categoryEntity.id,
                categoryEntity.categoryName,
                postEntity.id.count())
                .from(categoryEntity)
                .leftJoin(postEntity)
                .on(categoryEntity.id.eq(postEntity.categoryEntity.id))
                .groupBy(categoryEntity.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<CategoryVO> categoryVOS = tupleQueryResults.getResults().stream().map(tuple ->
                CategoryVO
                        .builder()
                        .id(tuple.get(categoryEntity.id))
                        .categoryName(tuple.get(categoryEntity.categoryName))
                        .postCount(tuple.get(postEntity.id.count()).intValue())
                        .build()
        ).collect(Collectors.toList());
        return new PageImpl<>(categoryVOS,pageable,tupleQueryResults.getTotal());
    }
}
