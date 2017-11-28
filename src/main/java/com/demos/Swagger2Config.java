package com.demos;

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

/**
 * Function:通过@Configuration注解，让Spring来加载该类配置，@EnableSwagger2注解来启用Swagger2。
 再通过createRestApi函数创建Docket的Bean之后，apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
 select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
 本例采用指定扫描的包路径来定义，Swagger会扫描该包下所有Controller定义的API，并产生文档内容（除了被@ApiIgnore注解的API）
 * Author: created by liguoliang
 * Date: 2017/11/28 0028 下午 3:11
 * Version: 1.0
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.demos"))
                .paths(PathSelectors.any())
//                .paths(Predicates.or(PathSelectors.regex("/api/.*")))//过滤的接口
                .build();
    }
    //构建 api文档的详细信息函数
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 测试使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("nihao", "http://www.bytebeats.com", "123@163.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
//                .license("Apache 2.0")
//                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .build();
    }
}
