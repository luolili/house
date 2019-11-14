package com.luo.house.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebmvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthInterceptor authInterceptor;
    @Autowired
    private AuthActionInterceptor authActionInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //先执行authInterceptor
        registry.addInterceptor(authInterceptor).excludePathPatterns("/static").addPathPatterns("/**");
        registry.addInterceptor(authActionInterceptor).addPathPatterns("/accounts/profile");
        super.addInterceptors(registry);
    }
}
