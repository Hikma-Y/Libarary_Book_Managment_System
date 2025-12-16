package com.example.library.utils;

import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/librarydb?zeroDateTimeBehavior=convertToNull";
    private static final String USER = "root";
    private static final String PASS = "root"; // change as needed

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // for MySQL 5.x
            // For MySQL 8+ driver: com.mysql.cj.jdbc.Driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASS);
        return DriverManager.getConnection(URL, props);
    }

    public static void closeQuietly(AutoCloseable c) {
        if (c != null) try { c.close(); } catch (Exception ignored) {}
    }
}
