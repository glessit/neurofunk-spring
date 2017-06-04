package com.glessit.neurofunky.configuration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityMetadataSourceAdvisor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.util.MultiValueMap;

/**
 * Created by benj on 02.06.17.
 */

class AdvisorRegistart implements
        ImportBeanDefinitionRegistrar {

    /**
     * Register, escalate, and configure the AspectJ auto proxy creator based on the value
     * of the @{@link EnableGlobalMethodSecurity#proxyTargetClass()} attribute on the
     * importing {@code @Configuration} class.
     */
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {

        BeanDefinitionBuilder advisor = BeanDefinitionBuilder
                .rootBeanDefinition(MethodSecurityMetadataSourceAdvisor.class);
        advisor.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        advisor.addConstructorArgValue("methodSecurityInterceptor");
        advisor.addConstructorArgReference("methodSecurityMetadataSource");
        advisor.addConstructorArgValue("methodSecurityMetadataSource");

//        MultiValueMap<String,Object> attributes = importingClassMetadata.getAllAnnotationAttributes(EnableGlobalMethodSecurity.class.getName());
        Integer order = 2147483647;
        if(order != null) {
            advisor.addPropertyValue("order", order);
        }

        registry.registerBeanDefinition("metaDataSourceAdvisor",
                advisor.getBeanDefinition());
    }
}