package com.haramasu.daomin.repo;

import com.haramasu.daomin.entity.db.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.OrderBy;

@Repository
public interface PostRepo extends JpaRepository<PostEntity,Integer> {

    @Override
    Page<PostEntity> findAll(Pageable pageable);

    @EntityGraph(value = "findAll",type = EntityGraph.EntityGraphType.FETCH)
    Page<PostEntity> findAllByOrderByCreateTimeDesc(Pageable pageable);


    PostEntity findTopByTitleEn(String titleEn);
}
