package com.Database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController {
    private final DatabaseManager db;

    public AuthController() {
        db = DatabaseManager.getInstance();
    }

    public boolean createUser(String username, String password) {
        try {
            db.connect();
            String hashedPassword = hashPassword(password);
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            statement.executeUpdate();
            db.closeConnection();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean userExists(String username, String password) {
        try {
            db.connect();
            String query = "SELECT username, password FROM users WHERE username = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            String hashedPassword = hashPassword(password);
            boolean exists = rs.next() && rs.getString("password").equals(hashedPassword);
            db.closeConnection();
            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((password).getBytes());
            StringBuilder hashedPassword = new StringBuilder();
            for (byte b : hash) {
                hashedPassword.append(String.format("%02x", b));
            }
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getToken(String subject) {
        try {
            db.connect();
            String query = "SELECT token FROM users WHERE username = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, subject);
            ResultSet rs = statement.executeQuery();
            String token = rs.next() ? rs.getString("token") : null;
            db.closeConnection();
            return token;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveToken(String user, String jwtToken) {
        try {
            db.connect();
            String query = "UPDATE users SET token = ? WHERE username = ?";
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, jwtToken);
            statement.setString(2, user);
            statement.executeUpdate();
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
