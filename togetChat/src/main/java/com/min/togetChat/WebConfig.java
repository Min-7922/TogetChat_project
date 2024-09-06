package com.min.togetChat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	// /uploadFiles 요청이 들어오면 D:/uploadFiles 에서 파일을 찾는다.
        // 외부 경로 D:/uploadFiles/** 로 접근할 수 있도록 설정
        registry.addResourceHandler("/uploadFiles/**")
                .addResourceLocations("file:///" + uploadDir + "/");
        
        // /static 요청이 들어오면 프로젝트 내부의 /static 폴더에서 파일을 찾는다
        // 내부 정적 리소스 경로 (예: /static/)
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}