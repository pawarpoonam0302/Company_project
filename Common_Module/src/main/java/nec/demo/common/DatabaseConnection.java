package nec.demo.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

        private static String url;
        private static String username;
        private static String password;

        // Static block to initialize static fields
        static {
            ConfigLoader configLoader = new ConfigLoader();
            url = configLoader.getProperty("db.url");
            username = configLoader.getProperty("db.username");
            password = configLoader.getProperty("db.password");
        }

        public static Connection getConnection() {
            try {
                if (url == null || username == null || password == null) {
                    throw new RuntimeException("Database configuration is incomplete. Please check the configuration file.");
                }
                return DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException("Failed to establish database connection.", e);
            }
        }
    }
