package com.action.reader.readerwriterreplica.datasource;

import org.hibernate.type.descriptor.java.spi.JsonJavaType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReadWriteDataSourceConfig {

    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDataSource() {

        System.out.println("write data source");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.read")
    public DataSource readDataSource() {
        System.out.println("Reading from read data source");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier("writeDataSource") DataSource writeDataSource,
                                 @Qualifier("readDataSource") DataSource readDataSource) {
        AbstractRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.WRITE, writeDataSource);
        dataSourceMap.put(DataSourceType.READ, readDataSource);

        routingDataSource.setDefaultTargetDataSource(writeDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        return routingDataSource;
    }
}