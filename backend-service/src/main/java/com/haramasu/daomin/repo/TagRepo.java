package com.haramasu.daomin.repo;

import com.haramasu.daomin.entity.db.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<TagEntity,Integer>, QuerydslPredicateExecutor<TagEntity> {

    @Override
    Page<TagEntity> findAll(Pageable pageable);

    long countByTagName(String tagName);

}