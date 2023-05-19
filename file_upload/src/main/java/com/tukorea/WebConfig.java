package com.tukorea;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.dir.save}")
    private String saveDir; // 실제 저장 디렉토리

    @Value("${file.dir.view}")
    private String viewDir; // 보여지는 저장 디렉토리

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(viewDir + "**") // /upload/**
                .addResourceLocations("file:///" + saveDir);
    }
}
