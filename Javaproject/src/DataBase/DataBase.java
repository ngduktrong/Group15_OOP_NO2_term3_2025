package DataBase;
package com.finalproj.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBase {

    private static final String URL = "jdbc:mysql://localhost:3306/quanlyrcp?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "15102005"; 

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Kết nối đến cơ sở dữ liệu
            Connection connect = DriverManager.getConnection(URL, USER, PASSWORD);
            return connect;
        } catch (ClassNotFoundException e) {
            System.err.println("Driver không được tìm thấy: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Kết nối thất bại: " + e.getMessage());
        }
        return null;
    }
}

