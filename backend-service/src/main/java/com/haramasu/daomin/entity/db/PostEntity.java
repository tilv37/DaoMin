package com.haramasu.daomin.entity.db;

import com.fasterxml.jackson.annotation.*;
import com.haramasu.daomin.entity.vos.CategoryVO;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class PostEntity{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(unique = true)
    private String titleEn;

    private String summary;
    @Lob
    private String content;

    private Date createTime;

    @LastModifiedDate
    private Date modifyTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cate_id")
    private CategoryEntity categoryEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_tag_relation",
            joinColumns = @JoinColumn(name = "post_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id")
    )
    @JsonManagedReference
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

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public Set<TagEntity> getTagEntities() {
        return tagEntities;
    }

    public void setTagEntities(Set<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
