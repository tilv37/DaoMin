package com.haramasu.daomin.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/20/2020
 */
@Component
public class BeanConfig {

    @Bean
    public OkHttpClient okHttpClient(@Qualifier("pool") ConnectionPool connectionPool){
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectionPool(connectionPool)
                .connectTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .hostnameVerifier(((hostname, session) -> true))
                .build();
    }

    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(100, 300, TimeUnit.SECONDS);
    }

    @Bean("myLuceneWriter")
    public IndexWriter indexWriter() throws IOException {
        FSDirectory directory = FSDirectory.open(Paths.get("luceneIndex"));
        IndexWriterConfig config=new IndexWriterConfig(new SmartChineseAnalyzer());
        IndexWriter writer=new IndexWriter(directory,config);
        return writer;
    }

    @Bean("myLuceneSearcher")
    public IndexSearcher indexSearcher() throws IOException {
        FSDirectory directory = FSDirectory.open(Paths.get("luceneIndex"));
        IndexReader reader= DirectoryReader.open(directory);
        IndexSearcher searcher=new IndexSearcher(reader);
        return searcher;
    }
}
