package com.example.NetRDStockExchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
@ServletComponentScan
//@EnableWebMvc
public class NetRdStockExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetRdStockExchangeApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/*/*"))
				.apis(RequestHandlerSelectors.basePackage("com.example.NetRDStockExchange"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"NetRD Stock Exchange API",
				"API for NetRD Stock Exchange Web Services",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Orkun Doğan", "http://javabrains.io", "od.orkun.dogan@gmail.com"),
				"API License",
				"http://javabrains.io",
				Collections.emptyList());
	}
}
