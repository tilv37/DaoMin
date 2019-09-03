package com.haramasu.daomin.entity.viewo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 9/3/2019
 */
@Data
@Builder
public class PostSummaryVO {
    private Integer id;

    private String title;

    private Date createTM;

    private String tags;

    private String category;
}
