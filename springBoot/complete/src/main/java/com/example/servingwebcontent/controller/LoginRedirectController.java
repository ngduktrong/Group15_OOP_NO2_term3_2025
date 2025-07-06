package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRedirectController {

    /**
     * Khi người dùng truy cập đường dẫn gốc "/", 
     * sẽ tự động redirect đến "/login".
     */
    @GetMapping("")
    public String redirectToLogin() {
        return "login";
    }
}
