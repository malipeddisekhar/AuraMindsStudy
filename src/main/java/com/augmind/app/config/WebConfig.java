package com.augmind.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final @NonNull AccessGateInterceptor accessGateInterceptor;

    public WebConfig(@NonNull AccessGateInterceptor accessGateInterceptor) {
        this.accessGateInterceptor = accessGateInterceptor;
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
    }
}
