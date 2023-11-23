package com.sio2whocodes.wibor.routingDB;

import com.sio2whocodes.wibor.databaseRegistry.RegistryDto;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
@PropertySource({ "classpath:application.yml" })
@EnableJpaRepositories(basePackages = "com.sio2whocodes.wibor.repository")
public class DataSourceConfig {
    private Map<Object, Object> tenantDataSources = new ConcurrentHashMap<>();
    private AbstractRoutingDataSource multiTenantDataSource;

    @Bean
    public DataSource routingDataSource() {
        multiTenantDataSource =  new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return LocationContextHolder.getLocationContext();
            }
        };
        multiTenantDataSource.setTargetDataSources(targetDataSources());
        multiTenantDataSource.setDefaultTargetDataSource(localDataSource());
        multiTenantDataSource.afterPropertiesSet();
        return multiTenantDataSource;
    }

    @Primary
    @Bean
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    private Map<Object, Object> targetDataSources() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("cloud", cloudDataSource());
        targetDataSources.put("local", localDataSource());

        tenantDataSources = targetDataSources;
        return targetDataSources;
    }

    public void setCurrentTenant(RegistryDto databaseInfo) throws SQLException {
        addTenant(databaseInfo);
        LocationContextHolder.setLocationContext(databaseInfo.getDbid());
        log.debug("[d] Tenant '{}' set as current.", databaseInfo.getDbid());
    }
    public void addTenant(RegistryDto databaseInfo) throws SQLException {
        DataSource dataSource = DataSourceBuilder.create()
            .driverClassName("org.h2.Driver")
            .url(databaseInfo.getDburl()+";AUTO_SERVER=true;MODE=MySQL;NON_KEYWORDS=USER")
            .username(databaseInfo.getDbusername())
            .password(databaseInfo.getDbpassword())
            .build();

        try(Connection c = dataSource.getConnection()) {
            this.tenantDataSources.put(databaseInfo.getDbid(), dataSource);
            multiTenantDataSource.afterPropertiesSet();
            log.debug("[d] Tenant '{}' added.", databaseInfo.getDbid());
        }
    }

    // cloud data source
    @Bean
    @ConfigurationProperties("spring.datasource.cloud")
    public DataSourceProperties globalDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource cloudDataSource() {
        return globalDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    // local data source
    @Bean
    @ConfigurationProperties("spring.datasource.local")
    public DataSourceProperties localDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource localDataSource() {
        return localDataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

}
