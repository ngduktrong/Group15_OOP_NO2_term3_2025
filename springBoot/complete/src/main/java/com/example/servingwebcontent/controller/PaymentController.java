package com.example.servingwebcontent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/payment")
public class PaymentController {

    @GetMapping("/success")
    public String paymentSuccess(Model model) {
        // ModelAttribute "message" đã được set từ CustomerVeController
        return "payment-success";
    }
}