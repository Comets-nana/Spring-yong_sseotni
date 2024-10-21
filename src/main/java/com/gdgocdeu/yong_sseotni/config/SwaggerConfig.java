package com.gdgocdeu.yong_sseotni.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.my.project"))  // API 패키지 경로 설정
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());  // API 정보 설정
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("새로운 API 타이틀")       // 타이틀 설정
                .description("API 설명")         // 설명 설정
                .version("1.0.0")                // 버전 설정
                .build();
    }
}
