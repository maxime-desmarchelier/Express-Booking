package com.Database;

import generated.Reservation;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private final DatabaseManager db;

    public BookingController() {
        db = DatabaseManager.getInstance();
    }

    public int getUserIdFromToken(String token) {
        String sql = "SELECT id FROM users WHERE token = ?";
        try (Connection conn = db.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, token);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public List<Reservation> getUserReservations(int userId) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE user_id = ?";
        try (Connection conn = db.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String trainId = resultSet.getString("train_id");
                    String classId = resultSet.getString("class_id");
                    int seatsAmount = resultSet.getInt("seats_amount");
                    Date reservationDate = resultSet.getDate("reservation_date");
                    float price = resultSet.getFloat("price");
                    Reservation reservation = new Reservation();
                    reservation.setId(id);
                    reservation.setTrainId(trainId);
                    reservation.setClassId(classId);
                    reservation.setSeatsAmount(seatsAmount);
                    reservation.setReservationDate(reservationDate.toString());
                    reservation.setPrice(new BigDecimal(price));
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }


    public void createReservation(String trainId, String classId, int seatsAmount, float price, String token) {
        int userId = getUserIdFromToken(token);
        try (Connection conn = db.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO reservations (train_id, class_id, user_id, seats_amount, reservation_date, price) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, trainId);
            statement.setString(2, classId);
            statement.setInt(3, userId);
            statement.setInt(4, seatsAmount);
            statement.setTimestamp(5, new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            statement.setFloat(6, price);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
