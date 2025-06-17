package com.group15;

import java.sql.Connection;

import com.group15.DataBase.DataBase;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DataBase.getConnection()) {
            if (conn != null) {
                System.out.println("Ket noi thanh cong MySQL!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ket noi that bai !");
        }
    }
}
