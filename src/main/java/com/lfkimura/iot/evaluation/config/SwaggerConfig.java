package com.lfkimura.iot.evaluation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        final List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("NotFound").build());
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.lfkimura.iot.evaluation.rest"))
                .paths(PathSelectors.any()).build().apiInfo(this.infoApi()).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, responseMessageList).useDefaultResponseMessages(false);
    }

    private ApiInfo infoApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("IOT Manager");
        apiInfoBuilder.description("pipeline for IOTs");
        apiInfoBuilder.version("1.0.0");
        apiInfoBuilder.contact(this.contact());

        return apiInfoBuilder.build();
    }

    private Contact contact() {
        return new Contact("Luis Fernando Kimura  ", null, "lfkimura@gmail.com");
    }
}
