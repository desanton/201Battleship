package com.battleship201;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Database URL and credentials
        String jdbcUrl = "jdbc:mysql://localhost:3306/BattleshipDB";
        String username = "root"; //Create a connection on mySQL workbench and replace with your own username
        String password = "Ea031903"; //Create a connection on mySQL workbench and replace with your own password

        // Load MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return;
        }

        // Connect to the database
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to the database successfully.");
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}
