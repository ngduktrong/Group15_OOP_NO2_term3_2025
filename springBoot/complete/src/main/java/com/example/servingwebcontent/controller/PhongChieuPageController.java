package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.models.PhongChieu;
import com.example.servingwebcontent.service.PhongChieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/nhanvien/phongchieu")
public class PhongChieuPageController {

    @Autowired
    private PhongChieuService phongChieuService;

    /**
     * 1. Hiển thị danh sách phòng chiếu
     */
    @GetMapping
    public String listPhong(Model model) {
        List<PhongChieu> list = phongChieuService.getAllPhongChieu();
        model.addAttribute("listPhong", list);
        return "list-phong";
    }

    /**
     * 2. Hiển thị form thêm phòng chiếu
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongChieu", new PhongChieu());
        return "add-phong";
    }

    /**
     * 3. Xử lý lưu phòng chiếu mới, rồi nạp lại danh sách để trả về list-phong
     */
    @PostMapping("/add")
    public String doAdd(@ModelAttribute("phongChieu") PhongChieu phongChieu,
                        Model model) {
        phongChieuService.createPhongChieu(phongChieu);
        model.addAttribute("listPhong", phongChieuService.getAllPhongChieu());
        return "list-phong";
    }

    /**
     * 4. Hiển thị form sửa phòng chiếu
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        PhongChieu existing = phongChieuService.getPhongChieuById(id);
        model.addAttribute("phongChieu", existing != null ? existing : new PhongChieu());
        return "edit-phong";
    }

    /**
     * 5. Xử lý cập nhật phòng chiếu, rồi nạp lại danh sách để trả về list-phong
     */
    @PostMapping("/edit")
    public String doEdit(@ModelAttribute("phongChieu") PhongChieu phongChieu,
                         Model model) {
        phongChieuService.updatePhongChieu(phongChieu);
        model.addAttribute("listPhong", phongChieuService.getAllPhongChieu());
        return "list-phong";
    }

    /**
     * 6. Xóa phòng chiếu, rồi nạp lại danh sách để trả về list-phong
     */
    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable("id") int id, Model model) {
        phongChieuService.deletePhongChieu(id);
        model.addAttribute("listPhong", phongChieuService.getAllPhongChieu());
        return "list-phong";
    }

    
}
