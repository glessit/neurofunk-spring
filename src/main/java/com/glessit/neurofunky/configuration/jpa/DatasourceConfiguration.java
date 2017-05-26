package com.glessit.neurofunky.configuration.jpa;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:configuration/application.properties")
public class DatasourceConfiguration {

    @Value(value = "${datasource.driver.className}")
    private String driverClassName;

    @Value(value = "${datasource.driver.JDBCUrl}")
    private String JDBCUrl;

    @Value(value = "${datasource.driver.username}")
    private String username;

    @Value(value = "${datasource.driver.password}")
    private String password;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(JDBCUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        hikariConfig.setMaximumPoolSize(20);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("springHikariCP");

        hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        return dataSource;
    }
}
