package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Phim;
import com.example.servingwebcontent.service.PhimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/phim")
public class PhimController {
    @Autowired
    private PhimService phimService;

    @GetMapping({"", "/list"})
    public String listPhim(Model model) {
        model.addAttribute("phims", phimService.getAllPhim());
        return "phim/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phim", new Phim());
        return "phim/add";
    }

    @PostMapping("/add")
    public String addPhim(@ModelAttribute("phim") Phim phim, Model model) {
        phimService.createPhim(phim);
        model.addAttribute("phims", phimService.getAllPhim());
        return "phim/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        // Luôn load đối tượng (không if)
        model.addAttribute("phim", phimService.getPhimById(id));
        return "phim/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPhim(@PathVariable int id,@ModelAttribute("phim") Phim phim,Model model) {
        phim.setMaPhim(id);
        phimService.updatePhim(phim);
        model.addAttribute("phims", phimService.getAllPhim());
        return "phim/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePhim(@PathVariable int id, Model model) {
        phimService.deletePhim(id);
        model.addAttribute("phims", phimService.getAllPhim());
        return "phim/list";
    }
}
