package com.haramasu.daomin.entity.db;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/19/2020
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditable {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @LastModifiedBy
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifyTIme;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTIme() {
        return modifyTIme;
    }

    public void setModifyTIme(Date modifyTIme) {
        this.modifyTIme = modifyTIme;
    }
}
