package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.Ve;
import com.example.servingwebcontent.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ve")
public class VeController {

    @Autowired
    private VeService veService;

    // Trang web: hiển thị danh sách vé qua Thymeleaf
    @GetMapping("/view")
    public String showVeList(Model model) {
        List<Ve> danhSachVe = veService.getAllVes();
        model.addAttribute("ves", danhSachVe);
        return "ve/list"; // => templates/ve/list.html
    }

    // REST API: Lấy tất cả vé
    @ResponseBody
    @GetMapping("/api")
    public List<Ve> getAllVes() {
        return veService.getAllVes();
    }

    // REST API: Lấy vé theo ID
    @ResponseBody
    @GetMapping("/api/{id}")
    public Ve getVeById(@PathVariable int id) {
        return veService.getVeById(id);
    }

    // REST API: Thêm vé
    @ResponseBody
    @PostMapping("/api")
    public String createVe(@RequestBody Ve ve) {
        veService.createVe(ve);
        return "✅ Đã thêm vé!";
    }

    // REST API: Cập nhật vé
    @ResponseBody
    @PutMapping("/api/{id}")
    public String updateVe(@PathVariable int id, @RequestBody Ve ve) {
        ve.setMaVe(id);
        veService.updateVe(ve);
        return "✅ Đã cập nhật vé!";
    }

    // REST API: Xoá vé
    @ResponseBody
    @DeleteMapping("/api/{id}")
    public String deleteVe(@PathVariable int id) {
        veService.deleteVe(id);
        return "✅ Đã xoá vé!";
    }
}
