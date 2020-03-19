package com.haramasu.daomin.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 3/18/2020
 */
public interface LuceneService {

    /**
     * 给博客文章做倒排索引
     * @param title 文章的中文标题
     * @param titleEN 文章的英文标题
     * @param summary 文章的摘要
     * @param content 文章的正文
     * @return
     */
    Document createPostDocument(String title,String titleEN,String summary,String content);

    void searchByCondition(String condition,String query) throws ParseException, IOException;
}
