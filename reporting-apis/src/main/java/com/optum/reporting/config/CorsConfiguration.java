package com.optum.reporting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Request Configuration to register interceptors for pre, post, and complete handling
 *
 * @author dataPortal Engineering Team
 * @CopyRight (C) All rights reserved to E&A team. It's Illegal to reproduce this code.
 */
@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {
    public CorsConfiguration() {
        super();
    }

    /**
     * Method to register cors browser to allow users to perform only registered HTTP methods through API calls.
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600)
                .allowCredentials(false).maxAge(3600);
    }

}
