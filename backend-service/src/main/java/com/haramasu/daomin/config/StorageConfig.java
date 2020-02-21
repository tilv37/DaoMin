package com.haramasu.daomin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 2/21/2020
 */
@Configuration
@ConfigurationProperties(prefix = "storage")
@EnableConfigurationProperties({StorageConfig.class})
public class StorageConfig {

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
