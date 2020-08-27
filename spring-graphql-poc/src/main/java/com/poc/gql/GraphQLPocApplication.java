package com.poc.gql;

import com.poc.gql.service.PingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.poc.gql"})
@Slf4j
public class GraphQLPocApplication {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(GraphQLPocApplication.class, args);
        //SpringApplication.run(GraphQLPocApplication.class, args);


        PingService pingService = context.getBean(PingService.class);
        pingService.pingMessage("I'm Testing Context Load..");
    }
}
