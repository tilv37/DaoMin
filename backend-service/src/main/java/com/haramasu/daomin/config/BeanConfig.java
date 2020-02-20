package com.haramasu.daomin.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
}
