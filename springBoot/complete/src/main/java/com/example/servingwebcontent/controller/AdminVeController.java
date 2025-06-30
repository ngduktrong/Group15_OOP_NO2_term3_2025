// AdminVeController.java
package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanvien/ve")
public class AdminVeController {

    @Autowired
    private VeService veService;

    @GetMapping
    public String listVe(Model model) {
        model.addAttribute("listVe", veService.getAllVe());
        return "list-ve";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("ve", new Ve());
        return "add-ve";
    }

    @PostMapping("/add")
    public String addVe(@ModelAttribute Ve ve, Model model) {
        veService.createVe(ve);
        model.addAttribute("listVe", veService.getAllVe());
        return "list-ve";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Ve ve = veService.getVeById(id);
        model.addAttribute("ve", ve);
        return "edit-ve";
    }

    @PostMapping("/edit/{id}")
    public String updateVe(@PathVariable int id, @ModelAttribute Ve ve, Model model) {
        ve.setMaVe(id);
        veService.updateVe(ve);
        model.addAttribute("listVe", veService.getAllVe());
        return "list-ve";
    }

    @GetMapping("/delete/{id}")
    public String deleteVe(@PathVariable int id, Model model) {
        veService.deleteVe(id);
        model.addAttribute("listVe", veService.getAllVe());
        return "list-ve";
    }
}
