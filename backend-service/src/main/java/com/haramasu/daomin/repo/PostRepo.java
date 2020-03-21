package com.haramasu.daomin.repo;

import com.haramasu.daomin.entity.db.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostEntity,Integer> {

    @EntityGraph(value = "findAll",type = EntityGraph.EntityGraphType.FETCH)
    @Override
    Page<PostEntity> findAll(Pageable pageable);

    PostEntity findTopByTitleEn(String titleEn);
}
