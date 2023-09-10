package com.pennhacks.ecolens;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webconfig implements WebMvcConfigurer {
    public void addCorsMapping(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedMethods("*");
    }
}
