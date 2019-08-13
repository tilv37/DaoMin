package com.haramasu.daomin.repo;

import com.haramasu.daomin.entity.db.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepo extends JpaRepository<TagEntity,Integer> {

    long countByTagName(String tagName);
}