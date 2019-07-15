package org.cth.gmweb;

import org.cth.gmweb.interceptor.CthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Bean
    public CthInterceptor cthInterceptor(){
        return new CthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cthInterceptor())
                .addPathPatterns("/v1/**")
                .excludePathPatterns("/v1/login");
    }
}
