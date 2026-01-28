package com.inventory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/inventory_db";
    private static final String USER = "root";
    private static final String PASSWORD = "YOUR_PASSWORD_HERE"; // your MySQL password

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 👈 ADD THIS LINE
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/inventory_db",
                    "root",
                    "YOUR_PASSWORD_HERE"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
