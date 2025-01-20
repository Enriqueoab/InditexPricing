package com.inditex.pricing.infrastructure;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    @Bean
    public LoggerFactoryBean loggerFactoryBean() {
        return new LoggerFactoryBean();
    }

    public static class LoggerFactoryBean {
        public Logger getLogger(Class<?> clazz) {
            return LogManager.getLogger(clazz);
        }
    }

}
