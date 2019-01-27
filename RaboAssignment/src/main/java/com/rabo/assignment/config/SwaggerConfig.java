package com.rabo.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Support for API documentation
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.apis(RequestHandlerSelectors.basePackage("com.rabo.assignment.controllers")).paths(PathSelectors.any())
				.build().useDefaultResponseMessages(false).apiInfo(apiInfo())
				.tags(new Tag("Records API", "REST API to validate transaction statements."));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Rabo Records REST API")
				.description("Service that provides interface to the Rabo bank records")
				.license("<License Type - Apache, Gnu etc...>").licenseUrl("<License Url>")
				.contact(new Contact("Rabobank", "https://www.sample.com", "contactus@sample.com")).version("0.0.1")
				.build();
	}

}