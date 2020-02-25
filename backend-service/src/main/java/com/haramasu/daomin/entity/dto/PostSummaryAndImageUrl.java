package com.haramasu.daomin.entity.dto;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/25/2020
 */
public class PostSummaryAndImageUrl {
    private String summary;
    private String firstImageUri;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getFirstImageUri() {
        return firstImageUri;
    }

    public void setFirstImageUri(String firstImageUri) {
        this.firstImageUri = firstImageUri;
    }
}
