package org.quark.microapidemo.config;

import org.quark.microapidemo.interceptor.ApproveInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration registration = registry
                .addInterceptor(new ApproveInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/**.html");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .exposedHeaders(GlobalConfig.WebConfig.HEADER_AUTHORIZE, GlobalConfig.WebConfig.HEADER_REFRESHTOKEN);
    }
}
