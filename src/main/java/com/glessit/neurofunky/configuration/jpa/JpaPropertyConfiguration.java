package com.glessit.neurofunky.configuration.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class JpaPropertyConfiguration {

    @Bean(name = "jpaPropertyMap")
    public HashMap<String,Object> jpaPropertyBean() {
        HashMap<String, Object> jpaPropertyBean = new HashMap<>();
        jpaPropertyBean.put("hibernate.hbm2ddl.auto", "create");
        jpaPropertyBean.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
/*

        // add cache settings
        jpaPropertyBean.put("hibernate.cache.use_second_level_cache", true);
        jpaPropertyBean.put("hibernate.cache.use_query_cache", false);
        jpaPropertyBean.put("hibernate.cache.use_minimal_puts", true);
        jpaPropertyBean.put("hibernate.cache.use_structured_entries", true);

        // by default
        jpaPropertyBean.put("javax.persistence.sharedCache.mode", "ENABLE_SELECTIVE");

        jpaPropertyBean.put("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");
        jpaPropertyBean.put("hibernate.javax.cache.provider", "org.ehcache.jsr107.EhcacheCachingProvider");
        jpaPropertyBean.put("hibernate.javax.cache.uri", "classpath:jcache/ehcache.xml");
*/


        return jpaPropertyBean;
    }
}
