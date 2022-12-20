package com.hordiienko.keycloak_test.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.hordiienko.keycloak_test.repository.source",
        entityManagerFactoryRef = "userSourceManagerFactory",
        transactionManagerRef = "userSourceTransactionManager")
public class SourceUserInfoConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.user-source")
    public DataSourceProperties userSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.user-source.configuration")
    public DataSource userInfoSource() {
        return userSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "userSourceManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean userSourceManagerFactoryBean(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(userInfoSource())
                .packages("com.hordiienko.keycloak_test.entity.source")
                .build();
    }

    @Bean(name = "userSourceTransactionManager")
    @Primary
    public PlatformTransactionManager userSourceTransactionManager(
            final @Qualifier("userSourceManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean
            ) {
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
