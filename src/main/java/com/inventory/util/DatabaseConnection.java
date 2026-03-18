package com.inventory.util;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    // Load Dotenv from the parent directory where .env is located, or ignore if not found
    private static final Dotenv env = loadDotenv();

    private static Dotenv loadDotenv() {
        try {
            return Dotenv.configure().directory("../").load();
        } catch (Exception e) {
            return Dotenv.configure().ignoreIfMissing().load();
        }
    }

    // Safely get variables using the keys present in your .env file
    private static final String URL = env.get("DB_URL", "jdbc:postgresql://ep-late-tree-adnlm9el-pooler.c-2.us-east-1.aws.neon.tech/neondb?sslmode=require&channel_binding=require");
    private static final String USER = env.get("DB_USER", "neondb_owner");
    private static final String PASSWORD = env.get("DB_PASSWORD", "npg_hBDdik4TVC6G");

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
