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
        return "phim/list"; // tương ứng templates/phim/list.html
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phim", new Phim());
        return "phim/add";
    }

    @PostMapping("/add")
    public String addPhim(@ModelAttribute("phim") Phim phim) {
        phimService.createPhim(phim);
        return "redirect:/phim/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Phim phim = phimService.getPhimById(id);
        if (phim == null) {
            return "redirect:/phim/list";
        }
        model.addAttribute("phim", phim);
        return "phim/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPhim(@PathVariable("id") int id, @ModelAttribute("phim") Phim phim) {
        phim.setMaPhim(id);
        phimService.updatePhim(phim);
        return "redirect:/phim/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePhim(@PathVariable("id") int id) {
        phimService.deletePhim(id);
        return "redirect:/phim/list";
    }
}
