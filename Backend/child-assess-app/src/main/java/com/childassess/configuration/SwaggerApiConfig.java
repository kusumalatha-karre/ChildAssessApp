package com.childassess.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import com.childassess.ChildAssessAppApplication;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerApiConfig {
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage(ChildAssessAppApplication.class.getPackage().getName()))
				.paths(PathSelectors.any()).build().securitySchemes(Arrays.asList(apiKey()));
	}

	private ApiKey apiKey() {
		return new ApiKey(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION,"header");
	}
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("ChildAssess Spring Boot Application")
				.description("Used to assess child's progress")
				.contact(new Contact("Kusumalatha Karre",
						"",
						"kusumalatha.karre@gmail.com"))
				.version("1.0").build();
	}
}
