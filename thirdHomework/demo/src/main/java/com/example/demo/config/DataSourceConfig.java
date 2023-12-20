package com.example.demo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceConfig {

    @Autowired
    private DataSource dataSource;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
            System.out.println("JDBC URL: " + hikariDataSource.getJdbcUrl());
        } else {
            System.out.println("DataSource is not an instance of HikariDataSource");
        }
    }
}
