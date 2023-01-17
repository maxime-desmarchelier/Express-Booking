package com.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3306/trainbooking";
    private final String username = "root";
    private final String password = "root";

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