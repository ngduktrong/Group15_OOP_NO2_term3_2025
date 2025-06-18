package com.example.servingwebcontent.service;

import com.example.servingwebcontent.models.Phim;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhimService {
    
    public List<Phim> getAllPhim() {
        List<Phim> list = new ArrayList<>();
       
        list.add(new Phim(1,"The Great Adventure",120,"2026-06-18","Mỹ","2D","Bộ phim hành động kịch tính về hành trình mạo hiểm.", "Đạo diễn A","/images/poster1.jpg" ));
        list.add(new Phim(2,"Romance in Paris",95,"2026-07-05","Pháp","2D","Câu chuyện tình lãng mạn giữa bối cảnh thành phố tình yêu.","Đạo diễn B","/images/poster2.jpg"));
        list.add(new Phim(3,"Sci-Fi Future",110,"2026-08-10","Anh","3D","Cuộc phiêu lưu khám phá vũ trụ tương lai đầy bí ẩn.","Đạo diễn C","/images/poster3.jpg"));
        
        return list;
    }

    public Phim getPhimById(int id) {
        for (Phim p : getAllPhim()) {
            if (p.getMaPhim() == id) return p;
        }
        return null;
    }
}
