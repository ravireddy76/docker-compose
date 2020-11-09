package com.optum.reporting.data.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.google.common.collect.Lists.newArrayList;

/**
 * SwaggerConfig class used for API documentation and provides html page.
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to UHC E&A team. It's Illegal to reproduce this code.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Reporting Data Enrichment Service")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.optum.reporting.data.controller"))
                .build()
                .useDefaultResponseMessages(true)
                .tags(new Tag("Reporting Data Enrichment Service", "Reporting Data Enrichment Service"))
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(getResponseMessageBuilder(200),
                                getResponseMessageBuilder(500)))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(getResponseMessageBuilder(200),
                                getResponseMessageBuilder(500)))
                .globalResponseMessage(RequestMethod.PUT,
                        newArrayList(getResponseMessageBuilder(200),
                                getResponseMessageBuilder(500)))
                .globalResponseMessage(RequestMethod.DELETE,
                        newArrayList(getResponseMessageBuilder(200),
                                getResponseMessageBuilder(500)))
                .apiInfo(apiInfo());
    }

    /**
     * <p>This method is for building the ResponseMessage.<p/>
     *
     * @param statusCode
     */
    private ResponseMessage getResponseMessageBuilder(int statusCode) {
        ResponseMessageBuilder messageBuilder = new ResponseMessageBuilder();
        return messageBuilder.code(statusCode)
                .message("Success")
                .responseModel(new ModelRef("Void")).build();

    }

    /**
     * This method to build api info.
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Reporting Data Enrichment Service")
                .description("Reporting data enrichment service to tap tha reporting data from kafka streams of nimbus ecosystem and push into reporting data store")
                .contact(new Contact("Reporting Data Enrichment Service", null, "ravi.nancherla@uhg.com"))
                .version("2.0")
                .build();
    }

}
