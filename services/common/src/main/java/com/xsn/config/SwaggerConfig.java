package com.xsn.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableSwaggerBootstrapUi;
import org.springframework.beans.factory.annotation.Autowired;
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
@EnableSwaggerBootstrapUi
public class SwaggerConfig {

	@Autowired
	private SwaggerProperties swaggerProperties;

	 @Bean
	public Docket createRestApi() {

	 	return new Docket(DocumentationType.SWAGGER_2)
						.apiInfo(apiInfo())
						.select()
						.apis(RequestHandlerSelectors.basePackage(swaggerProperties.getScan()))
						.paths(PathSelectors.any())
						.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//自定义信息可按需求填写
				.title(swaggerProperties.getTitle())
				.description(swaggerProperties.getDescription())
				.termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())//服务条款地址
				.version(swaggerProperties.getVersion())
				.build();
	}
}
