package com.haramasu.daomin.entity.dto;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
public class TagDTO {
    private Integer id;

    private String tagName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
