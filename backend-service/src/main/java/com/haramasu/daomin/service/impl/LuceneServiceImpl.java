package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.service.LuceneService;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 3/18/2020
 */
@Service
public class LuceneServiceImpl implements LuceneService {

    @Resource(name = "myLuceneWriter")
    private IndexWriter indexWriter;

    @Resource(name = "myLuceneSearcher")
    private IndexSearcher indexSearcher;

    @Override
    public Document createPostDocument(String title, String titleEN, String summary, String content){
        Document document=new Document();
        document.add(new TextField("title",title, Field.Store.YES));
        document.add(new TextField("titleEN",titleEN, Field.Store.YES));
        document.add(new TextField("summary",summary, Field.Store.YES));
        document.add(new TextField("content",content, Field.Store.YES));
        return document;
    }

    @Override
    public void searchByCondition(String condition, String query) throws ParseException, IOException {
        QueryParser queryParser=new QueryParser(condition,new SmartChineseAnalyzer());
        Query parse = queryParser.parse(query);
        TopDocs topDocs = indexSearcher.search(parse, 10);

        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
            System.out.println(scoreDoc.score);
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.println(doc);
            System.out.println("--------------------");
        }
    }
}
