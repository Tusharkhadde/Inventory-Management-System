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

    // Local PostgreSQL defaults for pgAdmin (use .env to override)
    private static final String URL = env.get("DB_URL", "jdbc:postgresql://localhost:5432/inventory_db");
    private static final String USER = env.get("DB_USER", "postgres");
    private static final String PASSWORD = env.get("DB_PASSWORD", "password");

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
