package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 为当前包路径
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")).paths(PathSelectors.any())
				.build();
	}

	// 构建 api文档的详细信息函数
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("Spring Boot 测试使用 Swagger2 构建RESTful API")
				// 创建人
				.contact(new Contact("池贤根", "http://www.cxg.com", "18182238772m@sina.cn"))
				// 版本号
				.version("1.0")
				// 描述
				.description("API 描述").build();
	}
}