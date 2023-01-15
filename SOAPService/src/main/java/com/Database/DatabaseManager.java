package com.Database;

import java.sql.*;

public class DatabaseManager {
    private static DatabaseManager instance;
    private final String host;
    private final String user;
    private final String password;
    private final String database;
    private Connection conn;
    private Statement stmt;

    private DatabaseManager(String host, String user, String password, String database) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager("localhost", "root", "root", "trainbooking");
        }
        return instance;
    }

    public void connect() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?user=" + user + "&password=" + password);
        stmt = conn.createStatement();
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return stmt.executeQuery(query);
    }

    public void closeConnection() throws SQLException {
        stmt.close();
        conn.close();
    }

    public Connection getConnection() {
        return conn;
    }
}
