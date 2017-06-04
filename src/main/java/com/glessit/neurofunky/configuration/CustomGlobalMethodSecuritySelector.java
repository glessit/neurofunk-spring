package com.glessit.neurofunky.configuration;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityAspectJAutoProxyRegistrar;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//import org.springframework.security.config.annotation.method.configuration.Jsr250MetadataSourceConfiguration;
//import org.springframework.security.config.annotation.method.configuration.MethodSecurityMetadataSourceAdvisorRegistrar;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


final class CustomGlobalMethodSecuritySelector implements ImportSelector {

    public final String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> methodSecurityAttrs = new HashMap<>();
        methodSecurityAttrs.put("mode", AdviceMode.PROXY);
        methodSecurityAttrs.put("proxyTargetClass", false);
        methodSecurityAttrs.put("prePostEnabled", true);
        methodSecurityAttrs.put("securedEnabled", false);
        methodSecurityAttrs.put("jsr250Enabled", false);
        methodSecurityAttrs.put("order", 2147483647);
        AnnotationAttributes attributes = AnnotationAttributes
                .fromMap(methodSecurityAttrs);


        // TODO would be nice if could use BeanClassLoaderAware (does not work)
        Class<?> importingClass = ClassUtils
                .resolveClassName(importingClassMetadata.getClassName(),
                        ClassUtils.getDefaultClassLoader());
        boolean skipMethodSecurityConfiguration = CustomGlobalMethodSecurityConfiguration.class
                .isAssignableFrom(importingClass);

        AdviceMode mode = attributes.getEnum("mode");
        boolean isProxy = AdviceMode.PROXY == mode;

        String autoProxyClassName = null;

        if (isProxy) {
            autoProxyClassName = AutoProxyRegistrar.class.getName();
        }
        else {
            System.out.println();
//            GlobalMethodSecurityAspectJAutoProxyRegistrar.class.getName();
        }

        boolean jsr250Enabled = attributes.getBoolean("jsr250Enabled");

        List<String> classNames = new ArrayList<String>(4);
        if(isProxy) {
            System.out.println();
            classNames.add(AdvisorRegistart.class.getName());
        }

        classNames.add(autoProxyClassName);

        if (!skipMethodSecurityConfiguration) {
//            classNames.add(GlobalMethodSecurityConfiguration.class.getName());
            classNames.add(CustomGlobalMethodSecurityConfiguration.class.getName());
        }

        if (jsr250Enabled) {
            System.out.println();
            //classNames.add(Jsr250MetadataSourceConfiguration.class.getName());
        }

        return classNames.toArray(new String[0]);
    }
}
