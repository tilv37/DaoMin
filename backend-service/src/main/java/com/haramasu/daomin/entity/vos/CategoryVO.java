package com.haramasu.daomin.entity.vos;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/13/2019
 */
@Data
@Builder
public class CategoryVO {

    private Integer id;

    private String categoryName;

    private Integer postCount;

}
