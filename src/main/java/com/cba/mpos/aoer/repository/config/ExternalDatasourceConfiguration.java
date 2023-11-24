package com.cba.mpos.aoer.repository.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.cba.mpos.aoer.repository.external",
        entityManagerFactoryRef = "externalEntityManagerFactory",
        transactionManagerRef = "externalTransactionManager"
)
public class ExternalDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.external")
    public DataSourceProperties externalDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource externalDataSource() {
        return externalDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean externalEntityManagerFactory(
            @Qualifier("externalDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.cba.mpos.aoer.model.external")
                .properties(jpaProperties())
                .build();
    }

    @Bean
    public PlatformTransactionManager externalTransactionManager(
            @Qualifier("externalEntityManagerFactory") LocalContainerEntityManagerFactoryBean externalEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(externalEntityManagerFactory.getObject()));
    }

    protected Map<String, Object> jpaProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.naming.physical-strategy", PhysicalNamingStrategyStandardImpl.class.getName());
        return props;
    }
}
