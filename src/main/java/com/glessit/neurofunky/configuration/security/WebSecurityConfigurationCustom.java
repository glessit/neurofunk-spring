package com.glessit.neurofunky.configuration.security;

import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

import java.util.Map;

/**
 * Created by benj on 28.05.17.
 */
public class WebSecurityConfigurationCustom extends WebSecurityConfiguration {
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
    }
}
