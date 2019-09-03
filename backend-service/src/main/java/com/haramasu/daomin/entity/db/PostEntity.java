package com.haramasu.daomin.entity.db;

import com.haramasu.daomin.entity.viewo.CategoryVO;
import com.haramasu.daomin.entity.viewo.TagVO;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "CategoryWithPostCount",
                classes = @ConstructorResult(
                        targetClass = CategoryVO.class,
                        columns = {
                                @ColumnResult(name = "tid",type = Integer.class),
                                @ColumnResult(name = "categoryName",type = String.class),
                                @ColumnResult(name = "postNo",type = Integer.class)
                        }
                )
        )
}
)
@Entity
public class PostEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String titleUrl;

    private String summary;

    @Lob
    private String content;

    private Date createTM;

    private Date modifyTM;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_id")
    private CategoryEntity categoryEntity;

    @ManyToMany
    @JoinTable(
            name = "post_tag_relation",
            joinColumns = @JoinColumn(name = "post_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id")
    )
    private Set<TagEntity> tagEntities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
    }
}
