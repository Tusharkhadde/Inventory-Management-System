package com.inventory.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // We fetch credentials from Environment Variables, or use placeholders if not set.
    // For NeonDB, the URL looks like: jdbc:postgresql://ep-...,neon.tech/neondb?sslmode=require
    private static final String URL = "jdbc:postgresql://ep-late-tree-adnlm9el-pooler.c-2.us-east-1.aws.neon.tech/neondb?sslmode=require&channel_binding=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_hBDdik4TVC6G";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL Driver not found. Add it to your pom.xml.");
            e.printStackTrace();
            throw new SQLException("Database driver missing", e);
        }
    }
}
