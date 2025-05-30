package com.group15;

import java.sql.Connection;

import com.group15.DataBase.DataBase;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DataBase.getConnection()) {
            if (conn != null) {
                System.out.println("Kết nối thành công tới MySQL!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối thất bại!");
        }
    }
}
