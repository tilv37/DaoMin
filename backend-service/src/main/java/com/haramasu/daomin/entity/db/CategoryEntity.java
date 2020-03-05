package com.haramasu.daomin.entity.db;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@Entity
public class CategoryEntity extends AbstractAuditable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
