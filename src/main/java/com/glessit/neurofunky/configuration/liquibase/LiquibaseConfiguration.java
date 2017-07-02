package com.glessit.neurofunky.configuration.liquibase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

    @Value(value = "${liquibase.changelog.location}")
    private String changeLogLocation;

    @Autowired
    private DataSource primaryDatasource;

//    @Bean
    public CustomSpringLiquibaseBean springLiquibaseBean() {
        // need to override class see meta-inf issues
        CustomSpringLiquibaseBean springLiquibaseBean = new CustomSpringLiquibaseBean();
        springLiquibaseBean.setDataSource(primaryDatasource);
        springLiquibaseBean.setChangeLog(changeLogLocation);
        return springLiquibaseBean;
    }
}
