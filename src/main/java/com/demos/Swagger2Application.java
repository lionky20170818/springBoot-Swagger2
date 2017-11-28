package com.demos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"io.swagger", "com.demos"})
@EntityScan("com.demos.entity") //entity对应的包路径
@MapperScan(basePackages = "com.demos")
@EnableJpaRepositories(basePackages={"com.demos"})//dao层对应的包路径
@EnableCaching
@EnableSwagger2
public class Swagger2Application {

	public static void main(String[] args) {
		SpringApplication.run(Swagger2Application.class, args);
	}
}
