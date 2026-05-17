package com.qm.quantitymeasurement.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final HikariDataSource dataSource;

    static {

        try {

            Properties properties =
                    new Properties();

            InputStream inputStream =
                    DatabaseConfig.class
                            .getClassLoader()
                            .getResourceAsStream(
                                    "application.properties"
                            );

            properties.load(inputStream);

            HikariConfig config =
                    new HikariConfig();

            config.setJdbcUrl(
                    properties.getProperty(
                            "db.url"
                    )
            );

            config.setUsername(
                    properties.getProperty(
                            "db.username"
                    )
            );

            config.setPassword(
                    properties.getProperty(
                            "db.password"
                    )
            );

            config.setDriverClassName(
                    properties.getProperty(
                            "db.driver"
                    )
            );

            config.setMaximumPoolSize(10);

            dataSource =
                    new HikariDataSource(config);

        } catch (Exception exception) {

            throw new RuntimeException(
                    "Database configuration failed",
                    exception
            );
        }
    }

    private DatabaseConfig() {
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
