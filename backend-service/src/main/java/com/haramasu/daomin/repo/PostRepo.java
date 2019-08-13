package com.haramasu.daomin.repo;

import com.haramasu.daomin.entity.db.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<PostEntity,Integer> {
}
