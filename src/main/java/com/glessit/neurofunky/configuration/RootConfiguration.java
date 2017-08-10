package com.glessit.neurofunky.configuration;

import com.glessit.neurofunky.configuration.converter.ConverterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.glessit.neurofunky.service", "com.glessit.neurofunky.configuration.startup"})
@Import(value = {ConverterConfiguration.class, NotificationConfiguration.class})
public class RootConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
