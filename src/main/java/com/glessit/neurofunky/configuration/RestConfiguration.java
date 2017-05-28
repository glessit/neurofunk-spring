package com.glessit.neurofunky.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewRequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.glessit.neurofunky.web.facebook", "com.glessit.neurofunky.web.rest"})
public class RestConfiguration extends WebMvcConfigurationSupport {

    @Bean(value = "simpleHttpClient")
    @Scope(value = "prototype")
    public HttpClient httpClientBean() {
        return HttpClientBuilder.create().build();
    }

    @Bean(value = "simpleObjectMapper")
    public ObjectMapper objectMapperBean() {
        return new ObjectMapper();
    }
/*
    @Bean(value = "jsonMessageMapper")
    public HttpMessageConverter mappingJackson2HttpMessageConverterBean(@Autowired ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapterBean(
            @Autowired MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter
    ) {
        RequestMappingHandlerAdapter requestMappingAdapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> la = new LinkedList<>();
        la.add(mappingJackson2HttpMessageConverter);
        requestMappingAdapter.setMessageConverters(la);

        requestMappingAdapter.setRequestBodyAdvice(
                Collections.<RequestBodyAdvice>singletonList(new JsonViewRequestBodyAdvice()));

        requestMappingAdapter.setResponseBodyAdvice(
                Collections.<ResponseBodyAdvice<?>>singletonList(new JsonViewResponseBodyAdvice()));

        return requestMappingAdapter;
    }*/

    /*@Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter adapter = createRequestMappingHandlerAdapter();
        adapter.setContentNegotiationManager(mvcContentNegotiationManager());
        adapter.setMessageConverters(getMessageConverters());
        adapter.setWebBindingInitializer(getConfigurableWebBindingInitializer());
        adapter.setCustomArgumentResolvers(getArgumentResolvers());
        adapter.setCustomReturnValueHandlers(getReturnValueHandlers());

        if (jackson2Present) {
            adapter.setRequestBodyAdvice(
                    Collections.<RequestBodyAdvice>singletonList(new JsonViewRequestBodyAdvice()));
            adapter.setResponseBodyAdvice(
                    Collections.<ResponseBodyAdvice<?>>singletonList(new JsonViewResponseBodyAdvice()));
        }

        AsyncSupportConfigurer configurer = new AsyncSupportConfigurer();
        configureAsyncSupport(configurer);
        if (configurer.getTaskExecutor() != null) {
            adapter.setTaskExecutor(configurer.getTaskExecutor());
        }
        if (configurer.getTimeout() != null) {
            adapter.setAsyncRequestTimeout(configurer.getTimeout());
        }
        adapter.setCallableInterceptors(configurer.getCallableInterceptors());
        adapter.setDeferredResultInterceptors(configurer.getDeferredResultInterceptors());

        return adapter;
    }*/
}
