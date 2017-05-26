package com.glessit.neurofunky.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.glessit.neurofunky.rest")
public class RestConfiguration {
}
