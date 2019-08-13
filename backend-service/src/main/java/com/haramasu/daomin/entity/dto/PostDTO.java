package com.haramasu.daomin.entity.dto;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/12/2019
 */
public class PostDTO {
    private String title;

    private String titleUrl;

    private String summary;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleUrl() {
        return titleUrl;
    }

    public void setTitleUrl(String titleUrl) {
        this.titleUrl = titleUrl;
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
}
