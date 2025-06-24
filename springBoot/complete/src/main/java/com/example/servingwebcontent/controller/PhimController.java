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

    // Trang web: hiển thị danh sách phim qua Thymeleaf
   @GetMapping("/view")
public String viewPhimList(Model model) {
    List<Phim> phims = phimService.getAllPhim();
    if (phims == null) {
        phims = new ArrayList<>();
    }
    model.addAttribute("phims", phims);
    return "phim/list";
}

    // REST API: Lấy tất cả phim
    @ResponseBody
    @GetMapping("/api")
    public List<Phim> getAllPhim() {
        return phimService.getAllPhim();
    }

    // REST API: Lấy phim theo ID
    @ResponseBody
    @GetMapping("/api/{id}")
    public Phim getPhimById(@PathVariable int id) {
        return phimService.getPhimById(id);
    }

    // REST API: Thêm phim
    @ResponseBody
    @PostMapping("/api")
    public String createPhim(@RequestBody Phim phim) {
        phimService.createPhim(phim);
        return "✅ Đã thêm phim!";
    }

    // REST API: Cập nhật phim
    @ResponseBody
    @PutMapping("/api/{id}")
    public String updatePhim(@PathVariable int id, @RequestBody Phim phim) {
        phim.setMaPhim(id);
        phimService.updatePhim(phim);
        return "✅ Đã cập nhật phim!";
    }

    // REST API: Xóa phim
    @ResponseBody
    @DeleteMapping("/api/{id}")
    public String deletePhim(@PathVariable int id) {
        phimService.deletePhim(id);
        return "✅ Đã xoá phim!";
    }
}
