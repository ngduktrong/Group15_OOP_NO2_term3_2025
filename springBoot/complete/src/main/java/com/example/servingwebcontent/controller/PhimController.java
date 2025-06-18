package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.service.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/phim")
public class PhimController {

    @Autowired
    private PhimService phimService;

    
    @GetMapping("/list")
    public String listPhim(Model model) {
        List<Phim> ds = phimService.getAllPhim();
        model.addAttribute("phimList", ds);
        model.addAttribute("pageTitle", "Danh sách Phim ");
        return "phim/list"; 
    }

   
    @GetMapping("/view/{id}")
    public String viewPhim(@PathVariable("id") int id, Model model) {
        Phim phim = phimService.getPhimById(id);
        if (phim == null) {
            return "redirect:/phim/list";
        }
        model.addAttribute("phim", phim);
        return "phim/view"; 
    }
}
