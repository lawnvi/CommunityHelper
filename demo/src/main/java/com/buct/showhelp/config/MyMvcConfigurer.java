package com.buct.showhelp.config;

import com.buct.showhelp.utils.Global;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 资源映射路径
 */
@Configuration
public class MyMvcConfigurer implements WebMvcConfigurer {
    public static String StaticPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+Global.ABSOLUTEPATH+"/upload/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        WebMvcConfigurer.super.addResourceHandlers(registry);

    }
}

