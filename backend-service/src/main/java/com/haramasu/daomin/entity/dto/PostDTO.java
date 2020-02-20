package com.haramasu.daomin.entity.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@Data
@Builder
public class PostDTO {

    private String title;

    private String titleEn;

    private String summary;

    private String content;

    private Date createTM;

    private Integer categoryId;

    private List<Integer> tagIds;
}
