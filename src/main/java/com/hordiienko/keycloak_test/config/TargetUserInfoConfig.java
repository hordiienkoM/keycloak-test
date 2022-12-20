package com.hordiienko.keycloak_test.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.hordiienko.keycloak_test.repository.target",
        entityManagerFactoryRef = "userTargetManagerFactory",
        transactionManagerRef = "userTargetTransactionManager")
public class TargetUserInfoConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.user-target")
    public DataSourceProperties userTargetProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.user-target.configuration")
    public DataSource userInfoTarget() {
        return userTargetProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "userTargetManagerFactory")
    public LocalContainerEntityManagerFactoryBean userTargetManagerFactoryBean(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(userInfoTarget())
                .packages("com.hordiienko.keycloak_test.entity.target")
                .build();
    }

    @Bean(name = "userTargetTransactionManager")
    public PlatformTransactionManager userTargetTransactionManager(
            final @Qualifier("userTargetManagerFactory") LocalContainerEntityManagerFactoryBean factoryBean
    ) {
        return new JpaTransactionManager(factoryBean.getObject());
    }
}
