package com.poc.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.poc.solr"})
public class SolrPocApplication {
    public static void main(String[] args) {
        SpringApplication.run(SolrPocApplication.class, args);
    }
}
