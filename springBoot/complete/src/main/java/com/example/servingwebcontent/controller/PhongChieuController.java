package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.PhongChieu;
import com.example.servingwebcontent.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/phongchieu")
public class PhongChieuController {

    private final PhongChieuService phongChieuService;

    @Autowired
    public PhongChieuController(PhongChieuService phongChieuService) {
        this.phongChieuService = phongChieuService;
    }

    @GetMapping({"", "/", "/view"})
    public String viewPhongChieu(Model model) {
        List<PhongChieu> list = phongChieuService.getAllPhongChieu();
        model.addAttribute("phongList", list);
        model.addAttribute("phongchieu", new PhongChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "");
        return "phongchieu";
    }

    @GetMapping("/edit/{id}")
    public String editPhong(@PathVariable("id") int id, Model model) {
        PhongChieu pc = phongChieuService.getPhongChieuById(id);
        if (pc == null) pc = new PhongChieu();

        model.addAttribute("phongList", phongChieuService.getAllPhongChieu());
        model.addAttribute("phongchieu", pc);
        model.addAttribute("editMode", true);
        model.addAttribute("message", "");
        return "phongchieu";
    }

    @PostMapping("/add")
    public String addPhong(@ModelAttribute PhongChieu phongchieu, Model model) {
        phongChieuService.createPhongChieu(phongchieu);
        System.out.println("📥 [ADD] Thêm phòng chiếu: " + phongchieu.getTenPhong());

        model.addAttribute("phongList", phongChieuService.getAllPhongChieu());
        model.addAttribute("phongchieu", new PhongChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "✅ Thêm phòng chiếu thành công!");
        return "phongchieu";
    }

    @PostMapping("/edit")
    public String updatePhong(@ModelAttribute PhongChieu phongchieu, Model model) {
        phongChieuService.updatePhongChieu(phongchieu);
        System.out.println("✏️ [UPDATE] Cập nhật phòng chiếu (ID: " + phongchieu.getMaPhong() + ")");

        model.addAttribute("phongList", phongChieuService.getAllPhongChieu());
        model.addAttribute("phongchieu", new PhongChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "✅ Cập nhật phòng chiếu thành công!");
        return "phongchieu";
    }

    @PostMapping("/delete/{id}")
    public String deletePhong(@PathVariable("id") int id, Model model) {
        phongChieuService.deletePhongChieu(id);
        System.out.println("🗑️ [DELETE] Đã xoá phòng chiếu có ID: " + id);

        model.addAttribute("phongList", phongChieuService.getAllPhongChieu());
        model.addAttribute("phongchieu", new PhongChieu());
        model.addAttribute("editMode", false);
        model.addAttribute("message", "✅ Xoá phòng chiếu thành công!");
        return "phongchieu";
    }
}
