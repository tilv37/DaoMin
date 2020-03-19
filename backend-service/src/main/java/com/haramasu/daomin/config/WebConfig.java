package com.haramasu.daomin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 3/19/2020
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ClassLoaderTemplateResolver templateResolver(){
        ClassLoaderTemplateResolver configurer=new ClassLoaderTemplateResolver();
        configurer.setPrefix("/templates");
        configurer.setCacheable(false);
        //如果设置了，则只能加载.html文件，css等后缀的文件都加载不了了
//        configurer.setSuffix(".html");
        configurer.setTemplateMode(TemplateMode.HTML);
        configurer.setCharacterEncoding("UTF-8");
        configurer.setOrder(0);
        configurer.setCheckExistence(true);
        return configurer;
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}
