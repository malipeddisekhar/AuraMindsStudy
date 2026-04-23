package com.augmind.app.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    private static final String H2_URL = "jdbc:h2:mem:augmind;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private static final String H2_USER = "sa";
    private static final String H2_PASSWORD = "";

    @Bean
    @Primary
    public DataSource dataSource(Environment environment) {
        String url = firstNonBlank(
            environment.getProperty("SPRING_DATASOURCE_URL"),
            environment.getProperty("DB_URL")
        );

        if (url == null) {
            log.warn("No datasource URL configured. Using in-memory H2 database.");
            return h2DataSource();
        }

        String username = firstNonBlank(
            environment.getProperty("SPRING_DATASOURCE_USERNAME"),
            environment.getProperty("DB_USER"),
            "root"
        );
        String password = firstNonBlank(
            environment.getProperty("SPRING_DATASOURCE_PASSWORD"),
            environment.getProperty("DB_PASSWORD"),
            ""
        );
        String driver = firstNonBlank(
            environment.getProperty("SPRING_DATASOURCE_DRIVER_CLASS_NAME"),
            environment.getProperty("DB_DRIVER_CLASS_NAME")
        );

        HikariDataSource candidate = new HikariDataSource();
        candidate.setJdbcUrl(url);
        candidate.setUsername(username);
        candidate.setPassword(password);
        candidate.setConnectionTimeout(5000);
        candidate.setInitializationFailTimeout(5000);
        candidate.setMaximumPoolSize(5);
        if (driver != null) {
            candidate.setDriverClassName(driver);
        }

        try (Connection connection = candidate.getConnection()) {
            connection.getMetaData();
            log.info("Using configured datasource URL for startup.");
            return candidate;
        } catch (SQLException ex) {
            log.warn("Configured datasource is unreachable. Falling back to in-memory H2. Cause: {}", ex.getMessage());
            candidate.close();
            return h2DataSource();
        }
    }

    private DataSource h2DataSource() {
        HikariDataSource h2 = new HikariDataSource();
        h2.setJdbcUrl(H2_URL);
        h2.setUsername(H2_USER);
        h2.setPassword(H2_PASSWORD);
        h2.setDriverClassName("org.h2.Driver");
        h2.setConnectionTimeout(5000);
        h2.setMaximumPoolSize(5);
        return h2;
    }

    private String firstNonBlank(String... values) {
        if (values == null) {
            return null;
        }
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return null;
    }
}
