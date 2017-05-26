package com.glessit.neurofunky.configuration;

import com.glessit.neurofunky.configuration.converter.ConverterConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {"com.glessit.neurofunky.service"})
@Import(value = {ConverterConfiguration.class})
public class RootConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
