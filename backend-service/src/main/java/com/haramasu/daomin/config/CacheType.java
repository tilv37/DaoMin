package com.haramasu.daomin.config;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 4/9/2020
 */
public enum CacheType {
    //一次管1小时
    CATE_NAMES(60),
    ALL_TAGS(60),
    ARCHIVE(30);

    private int expires;

    CacheType(int expires) {
        this.expires = expires;
    }

    public int getExpires() {
        return expires;
    }

}
