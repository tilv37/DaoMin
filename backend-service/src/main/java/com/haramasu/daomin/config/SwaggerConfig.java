package com.haramasu.daomin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Shuo Ding
 * @description:
 * @date: 8/13/2019
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.haramasu.daomin.controller"))
                .paths(PathSelectors.any())
                .build();
//                .globalOperationParameters(this.getParaList());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DaoMin Blog接口文档")
                .description("estful风格接口")
                .contact(new Contact("DM-Team", "http://www.haramasu.com", "amdin@at.com"))
                .version("1.0")
                .build();
    }

    private List<Parameter> getParaList() {
        ParameterBuilder tokenParameter = new ParameterBuilder();
        List<Parameter> list = new ArrayList<>();
        Parameter parameter = tokenParameter.name("Authorization").description("JWT令牌").modelRef(new ModelRef("string"))
                .parameterType("header").required(false).defaultValue("Bearer ").build();
        list.add(parameter);
        return list;
    }
}