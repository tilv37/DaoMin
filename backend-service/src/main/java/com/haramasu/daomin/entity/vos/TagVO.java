package com.haramasu.daomin.entity.vos;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/13/2019
 */
public class TagVO {

    private Integer id;

    private String tagName;

    private Integer postCount;

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

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public TagVO() {
    }

    public TagVO(Integer id, String tagName, Integer postCount) {
        this.id = id;
        this.tagName = tagName;
        this.postCount = postCount;
    }
}
