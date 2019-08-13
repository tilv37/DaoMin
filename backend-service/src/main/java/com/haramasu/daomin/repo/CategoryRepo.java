package com.haramasu.daomin.repo;

import com.haramasu.daomin.entity.db.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<CategoryEntity,Integer> {

    long countByCategoryName(String categoryName);
}
