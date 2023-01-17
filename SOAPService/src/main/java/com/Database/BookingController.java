package com.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingController {
    private final DatabaseManager db;

    public BookingController() {
        db = DatabaseManager.getInstance();
    }

    public boolean book(String company, String idTrain, int nbSeats) {
        return true;
    }
}
