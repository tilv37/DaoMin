package com.haramasu.daomin.service.impl;

import com.haramasu.daomin.Daomin3ApplicationTests;
import com.haramasu.daomin.service.LuceneService;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.Assert.*;

public class LuceneServiceImplTest extends Daomin3ApplicationTests {

    @Resource(name = "myLuceneWriter")
    private IndexWriter indexWriter;

    @Autowired
    private LuceneService luceneService;

    @Test
    public void luceneTest() throws IOException {

        Document document = luceneService.createPostDocument("我爱北京天安门", "I-love-china", "", "对新冠肺炎疫情对我国经济运行产生哪些影响？在就业、物价、投资、外资领域如何应对？17日举行的国家发展改革委例行新闻发布会上对这些热点问题作出回应。相关负责人表示，随着企业复工复产率不断提升，外资企业在华生产经营逐步走向正常，订单完成情况不断好转，企业信心加快恢复。");
        Document document1 = luceneService.createPostDocument("他们扼住了ICU的生死时速", "", "", "由于不像浅表的静脉清晰可视，深静脉穿刺只能凭借解剖关系进行，在戴着厚厚的手套、穿着防护服视线受阻的情况下，赵荣仍然一针见血，成功穿刺。 ");

        indexWriter.deleteAll();
        indexWriter.addDocument(document);
        indexWriter.addDocument(document1);
        indexWriter.commit();
        indexWriter.close();
    }

    @Test
    public void search() throws IOException, ParseException {
        luceneService.searchByCondition("content", "肺炎");
    }
}