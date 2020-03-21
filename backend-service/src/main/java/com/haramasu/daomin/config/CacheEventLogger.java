package com.haramasu.daomin.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 3/21/2020
 */
public class CacheEventLogger implements CacheEventListener<Object,Object> {

    private final Logger logger= LoggerFactory.getLogger(CacheEventLogger.class);
    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        logger.info("Key: {} | EventType: {} | Old value: {} | New value: {}",
                cacheEvent.getKey(), cacheEvent.getType(), cacheEvent.getOldValue(),
                cacheEvent.getNewValue());
    }
}
