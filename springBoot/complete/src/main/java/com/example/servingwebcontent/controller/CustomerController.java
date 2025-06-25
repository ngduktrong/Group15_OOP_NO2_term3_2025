package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.service.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private PhimService phimService;

    @GetMapping("/movies")
    public String listMoviesForCustomer(Model model) {
        List<Phim> phims = phimService.getAllPhim();
        model.addAttribute("phims", phims);
        return "customer/movies";  // templates/customer/movies.html
    }
}
