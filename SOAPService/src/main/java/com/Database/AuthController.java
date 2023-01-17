package com.Database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthController {
    //Declare the DatabaseManager as a private final variable
    private final DatabaseManager db;

    public AuthController() {
        //Initialize the DatabaseManager
        db = DatabaseManager.getInstance();
    }

    public boolean createUser(String username, String password) {
        try {
            //Hash the password
            String hashedPassword = hashPassword(password);
            //Create the query
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            //Create a prepared statement with the query
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            //Execute the update
            statement.executeUpdate();
            //Return true if the user was created
            return true;
        } catch (SQLException e) {
            //Print the stack trace and return false if there was an exception
            e.printStackTrace();
            return false;
        }
    }


    public boolean userExists(String username, String password) {
        try {
            //Create the query
            String query = "SELECT username, password FROM users WHERE username = ?";
            //Create a prepared statement with the query
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, username);
            //Execute the query
            ResultSet rs = statement.executeQuery();
            //Hash the given password
            String hashedPassword = hashPassword(password);
            //Check if the result set has a next value and if the password matches the hashed password in the database
            //Return if the user exists
            return rs.next() && rs.getString("password").equals(hashedPassword);
        } catch (SQLException e) {
            //Print the stack trace and return false if there was an exception
            e.printStackTrace();
            return false;
        }
    }

    //Method to hash a given password
    private String hashPassword(String password) {
        try {
            //Create an instance of the SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Get the bytes of the password
            byte[] hash = digest.digest((password).getBytes());
            //Create a StringBuilder to store the hashed password
            StringBuilder hashedPassword = new StringBuilder();
            //Iterate through the bytes of the hash
            for (byte b : hash) {
                //Append each byte to the StringBuilder in hexadecimal format
                hashedPassword.append(String.format("%02x", b));
            }
            //Return the hashed password as a string
            return hashedPassword.toString();
        } catch (NoSuchAlgorithmException e) {
            //Throw a runtime exception if the algorithm is not found
            throw new RuntimeException(e);
        }
    }

    //Method to get a token from the database for a given user
    public String getToken(String subject) {
        try {
            //Create the query
            String query = "SELECT token FROM users WHERE username = ?";
            //Create a prepared statement with the query
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, subject);
            //Execute the query
            ResultSet rs = statement.executeQuery();
            //Get the token from the result set, or return null if it doesn't exist
            //Return the token
            return rs.next() ? rs.getString("token") : null;
        } catch (SQLException e) {
            //Print the stack trace and return null if there was an exception
            e.printStackTrace();
            return null;
        }
    }

    //Method to save a token to the database for a given user
    public void saveToken(String user, String jwtToken) {
        try {
            //Create the query
            String query = "UPDATE users SET token = ? WHERE username = ?";
            //Create a prepared statement with the query
            PreparedStatement statement = db.getConnection().prepareStatement(query);
            statement.setString(1, jwtToken);
            statement.setString(2, user);
            //Execute the update
            statement.executeUpdate();
        } catch (SQLException e) {
            //Print the stack trace if there was an exception
            e.printStackTrace();
        }
    }
}

