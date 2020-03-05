package com.haramasu.daomin.repo;

import com.haramasu.daomin.entity.db.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepo extends JpaRepository<TagEntity,Integer>, QuerydslPredicateExecutor<TagEntity> {

    @Override
    Page<TagEntity> findAll(Pageable pageable);

    List<TagEntity> findAllByTagNameIn(List<String> tags);

    long countByTagName(String tagName);

}