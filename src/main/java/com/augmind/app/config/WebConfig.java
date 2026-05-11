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
        registry.addInterceptor(accessGateInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns(
                // Public pages
                "/", "/access", "/access.html", "/denied", "/denied.html",
                // Health check (used by Render and load balancers)
                "/health",
                // Access API (no auth needed)
                "/api/access/**",
                // Static resources
                "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**",
                "/**/*.css", "/**/*.js", "/**/*.ico", "/**/*.png", "/**/*.jpg",
                "/**/*.svg", "/**/*.woff", "/**/*.woff2", "/**/*.ttf",
                // Spring error page
                "/error"
            );
    }
}
