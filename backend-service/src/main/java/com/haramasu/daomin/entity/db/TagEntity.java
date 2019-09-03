package com.haramasu.daomin.entity.db;

import com.haramasu.daomin.entity.viewo.TagVO;

import javax.persistence.*;
import java.util.Date;
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
public class TagEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String tagName;

    private Date createTM;

    private Date modifyTM;

    @ManyToMany(mappedBy = "tagEntities",fetch = FetchType.LAZY)
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
