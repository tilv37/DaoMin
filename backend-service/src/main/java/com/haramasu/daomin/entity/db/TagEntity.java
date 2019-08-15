package com.haramasu.daomin.entity.db;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@Entity
public class TagEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String tagName;

    private Date createTM;

    private Date modifyTM;

    @ManyToMany(mappedBy = "tagEntities")
    private List<PostEntity> postEntityList;

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

    public Date getCreateTM() {
        return createTM;
    }

    public void setCreateTM(Date createTM) {
        this.createTM = createTM;
    }

    public Date getModifyTM() {
        return modifyTM;
    }

    public void setModifyTM(Date modifyTM) {
        this.modifyTM = modifyTM;
    }

    public List<PostEntity> getPostEntityList() {
        return postEntityList;
    }

    public void setPostEntityList(List<PostEntity> postEntityList) {
        this.postEntityList = postEntityList;
    }
}
