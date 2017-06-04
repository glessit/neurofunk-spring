package com.glessit.neurofunky.configuration.converter;

import com.glessit.neurofunky.service.converter.ApplicationGenericConverter;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;

@Configuration
public class ConverterConfiguration {

    @Bean(name = "primaryConverterService")
    public ConversionServiceFactoryBean conversionServiceFactoryBean() {
        ConversionServiceFactoryBean conversionService = new ConversionServiceFactoryBean();
        conversionService.setConverters(Sets.newHashSet(new ApplicationGenericConverter()));
        return conversionService;
    }

}
