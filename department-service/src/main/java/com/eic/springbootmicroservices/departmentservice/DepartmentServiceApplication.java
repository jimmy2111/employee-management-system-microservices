package com.eic.springbootmicroservices.departmentservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
		info = @Info(
				title="Department Service Application for EMS",
				description="Employee Management System",
				version="v1.0",
				contact = @Contact(
						name="Jimmy Patel",
						email="pateljimi@gmail.com"
						),
				license = @License(
						name = "Apache 2.0"
						)
				),
		externalDocs = @ExternalDocumentation(
				description = "Department Service Application Description",
				url = "http://localhost:8080/swagger-ui/index.html"
				)
		)
@SpringBootApplication
public class DepartmentServiceApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
