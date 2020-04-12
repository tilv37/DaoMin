package com.haramasu.daomin.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: Shuo Ding
 * @description: 缓存配置
 * @date: 4/9/2020
 */
@Configuration
@EnableCaching
public class CaffeineConfig {

    @Bean
    public CacheManager caffeineCacheManager(){
        SimpleCacheManager cacheManager=new SimpleCacheManager();
        List<CaffeineCache> caffeineCaches = new ArrayList<>();

        for (CacheType cacheType : CacheType.values()) {
            caffeineCaches.add(new CaffeineCache(cacheType.name(),
                    Caffeine.newBuilder()
                            .expireAfterWrite(cacheType.getExpires(), TimeUnit.MINUTES)
                            .build()));
        }

        cacheManager.setCaches(caffeineCaches);

        return cacheManager;
    }

}
