package com.cba.mpos.aoer.repository.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.query.JpaParameters;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.cba.mpos.aoer.repository.internal",
        entityManagerFactoryRef = "internalEntityManagerFactory",
        transactionManagerRef = "internalTransactionManager"
)
public class InternalDatasourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.internal")
    public DataSourceProperties internalDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    //@ConfigurationProperties("spring.datasource.internal.hikari")
    public DataSource internalDataSource() {
        return internalDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean internalEntityManagerFactory(
            @Qualifier("internalDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.cba.mpos.aoer.model.internal")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager internalTransactionManager(
            @Qualifier("internalEntityManagerFactory") LocalContainerEntityManagerFactoryBean internalEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(internalEntityManagerFactory.getObject()));
    }
}
