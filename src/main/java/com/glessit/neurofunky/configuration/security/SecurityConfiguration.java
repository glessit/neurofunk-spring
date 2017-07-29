package com.glessit.neurofunky.configuration.security;

import com.glessit.neurofunky.configuration.security.beans.FacebookAuthenticationProvider;
import com.glessit.neurofunky.configuration.security.beans.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@ComponentScan(basePackages = {"com.glessit.neurofunky.configuration.security.beans"})
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    public final static String HEADER_AUTH_NAME = "X-NFK-AUTH";

    @Autowired
    private FacebookAuthenticationProvider facebookAuthenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(facebookAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Token based authentication via filter implementation
        final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter();
        http.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);
        http.csrf().disable().cors();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://localhost"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}
