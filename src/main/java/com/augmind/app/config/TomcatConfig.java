package com.augmind.app.config;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    @Bean
    public TomcatServletWebServerFactory tomcatCustomizer() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            // Set relaxed query chars and path chars to handle various clients
            connector.setProperty("relaxedQueryChars", "{}[]");
            connector.setProperty("relaxedPathChars", "{}[]");
            
            // Improve request handling with longer max headers
            connector.setProperty("maxHttpHeaderSize", "65536");
            
            // Graceful handling of invalid requests
            connector.setProperty("rejectIllegalHeader", "false");
        });
        return factory;
    }
}
