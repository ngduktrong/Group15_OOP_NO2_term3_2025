package com.example.servingwebcontent.test;

import java.sql.Connection;
import com.example.servingwebcontent.database.AivenConnection;

public class TestConnection {
     public static void main(String[] args) {
        AivenConnection test = new AivenConnection();
        test.testConnection();
    }
}