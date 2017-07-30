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

import static com.glessit.neurofunky.configuration.servlet.ApplicationConfigurationInitializer.DISPATCHER_API_PREFIX;

@Configuration
@ComponentScan(basePackages = {"com.glessit.neurofunky.configuration.security.beans"})
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private class SecurityURL {
        private final static String DIALOG_URL = DISPATCHER_API_PREFIX + "facebook/login/dialog/url";
    }

    public final static String HEADER_AUTH_NAME = "X-NFK-AUTH";

    @Autowired
    private FacebookAuthenticationProvider facebookAuthenticationProvider;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(facebookAuthenticationProvider);
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/api/mix/get").denyAll().

                anyRequest().permitAll();

        http
            .authorizeRequests()
            .antMatchers("/api/facebook/login/dialog/url")
            .permitAll();
        // Token based authentication via filter implementation
        http.addFilterBefore(tokenAuthenticationFilter(), BasicAuthenticationFilter.class);
        http.csrf().disable().cors();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
