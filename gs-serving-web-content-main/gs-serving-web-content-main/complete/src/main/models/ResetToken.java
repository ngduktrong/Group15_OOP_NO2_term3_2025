package com.example.servingwebcontent.models;

import java.time.LocalDateTime;

public class ResetToken {
    private String Token;
    private String Email;
    private LocalDateTime ExpirationTime;

    public ResetToken(String Token, String Email, LocalDateTime ExpirationTime) {
        this.Token = Token;
        this.Email = Email;
        this.ExpirationTime = ExpirationTime;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public LocalDateTime getExpirationTime() {
        return ExpirationTime;
    }

    public void setExpirationTime(LocalDateTime ExpirationTime) {
        this.ExpirationTime = ExpirationTime;
    }
}