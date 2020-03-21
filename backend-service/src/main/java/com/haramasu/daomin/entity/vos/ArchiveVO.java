package com.haramasu.daomin.entity.vos;

import java.io.Serializable;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 3/20/2020
 */
public class ArchiveVO implements Serializable {

    private static final long serialVersionUID = 5969738731717960836L;
    private String archiveDate;
    private Integer count;

    public String getArchiveDate() {
        return archiveDate;
    }

    public void setArchiveDate(String archiveDate) {
        this.archiveDate = archiveDate;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
