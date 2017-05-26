package com.glessit.neurofunky.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

    @Value(value = "${liquibase.changelog.location}")
    private String changeLogLocation;

    @Autowired
    private DataSource primaryDatasource;

    @Bean
    public SpringLiquibase springLiquibaseBean() {
        SpringLiquibase springLiquibaseBean = new SpringLiquibase();
        springLiquibaseBean.setDataSource(primaryDatasource);
        springLiquibaseBean.setChangeLog(changeLogLocation);
        return springLiquibaseBean;
    }
}
