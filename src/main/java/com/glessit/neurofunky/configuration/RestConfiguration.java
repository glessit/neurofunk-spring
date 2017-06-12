package com.glessit.neurofunky.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan(basePackages = {"com.glessit.neurofunky.web"})
@EnableSpringDataWebSupport
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RestConfiguration extends DelegatingWebMvcConfiguration {

    @Bean(value = "simpleHttpClient")
    @Scope(value = "prototype")
    public HttpClient httpClientBean() {
        return HttpClientBuilder.create().build();
    }

    @Bean(value = "simpleObjectMapper")
    public ObjectMapper objectMapperBean() {
        return new ObjectMapper();
    }
}
