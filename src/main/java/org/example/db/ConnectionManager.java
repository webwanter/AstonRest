package org.example.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

//    String url = "jdbc:postgresql://localhost:5432/mydatabase";
//    String username = "myuser";
//    String password = "mypassword";


    private static HikariDataSource dataSource;

    static {
        Properties properties = new Properties();
        try (InputStream input = ConnectionManager.class.getClassLoader().getResourceAsStream("db.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.username"));
        config.setPassword(properties.getProperty("db.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.pool.size")));

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

}