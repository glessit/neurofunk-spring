package com.glessit.neurofunky.configuration.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@Import(value = {DatasourceConfiguration.class, JpaPropertyConfiguration.class})
@EnableJpaRepositories(basePackages = {"com.glessit.neurofunky.repository"})
@EnableTransactionManagement
public class JPAConfiguration {

    @Autowired
    private DataSource primaryDatasource;

    @Resource(name = "jpaPropertyMap")
    private Map<String,Object> jpaPropertyMap;

    private final String[] entityPackages = {"com.glessit.neurofunky.entity"};

    /* see http://www.baeldung.com/the-persistence-layer-with-spring-and-jpa */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(primaryDatasource);
        entityManagerFactoryBean.setPackagesToScan(entityPackages);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaPropertyMap(jpaPropertyMap);
//        entityManagerFactoryBean.afterPropertiesSet();

        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
