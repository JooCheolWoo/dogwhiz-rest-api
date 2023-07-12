package com.galaxypoby.dogwhiz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders("X-AUTH-TOKEN")
                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedOrigins("https://dev.front.hellodogwhiz.com"
//                        , "http://localhost:3000"
//                        , "https://w6gqmm-3000.csb.app")
                .allowedMethods("*")
                .maxAge(3000);
    }
}
