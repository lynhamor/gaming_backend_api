package com.api.auth.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.api.auth.database.repository",
        entityManagerFactoryRef = "coreEntityManagerFactory",
        transactionManagerRef = "coreTransactionManager"
)
public class CoreDatasourceConfig {

    @Primary
    @Bean( name = "coreDatasource")
    public DataSource datasource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "coreEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("coreDatasource") DataSource datasource
    ) {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(datasource);
        factory.setPackagesToScan("com.api.auth.database.entity");
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Map<String, Object> jpaProperties = new HashMap<>();

        jpaProperties.put("hibernate.jdbc.batch_size", 100);
        jpaProperties.put("hibernate.order_inserts", true);
        jpaProperties.put("hibernate.order_updates", true);
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        jpaProperties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        jpaProperties.put("hibernate.hbm2ddl.auto", "none");
        factory.setJpaPropertyMap(jpaProperties);

        factory.setPersistenceUnitName("corePU");

        return factory;
    }

    @Bean(name = "coreTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("coreEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
