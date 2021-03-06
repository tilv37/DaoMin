package com.haramasu.daomin.entity.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.haramasu.daomin.entity.vos.TagVO;

import javax.persistence.*;
import java.util.List;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "TagWithPostCount",
                classes = @ConstructorResult(
                        targetClass = TagVO.class,
                        columns = {
                                @ColumnResult(name = "tid",type = Integer.class),
                                @ColumnResult(name = "tagName",type = String.class),
                                @ColumnResult(name = "postNo",type = Integer.class)
                        }
                )
        )
}
)
@Entity
@Table(name = "dm_tag")
public class TagEntity extends AbstractAuditable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String tagName;

    @ManyToMany(mappedBy = "tagEntities",fetch = FetchType.LAZY)
    @JsonBackReference
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

    public List<PostEntity> getPostEntityList() {
        return postEntityList;
    }

    public void setPostEntityList(List<PostEntity> postEntityList) {
        this.postEntityList = postEntityList;
    }
}
