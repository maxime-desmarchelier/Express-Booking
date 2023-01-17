package com.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private final String url = System.getenv("MYSQL_HOST");
    private final String username = System.getenv("MYSQL_USER");
    private final String password = System.getenv("MYSQL_PASSWORD");
    private Connection connection;

    private DatabaseManager() {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
        return this.connection;
    }
}