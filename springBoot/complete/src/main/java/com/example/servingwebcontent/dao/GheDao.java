package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.Ghe;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GheDao {

   
    public List<Ghe> getAll() {
        List<Ghe> list = new ArrayList<>();
        String sql = "SELECT * FROM Ghe";

        try (Connection conn = AivenConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Ghe(
                        rs.getString("soGhe"),
                        rs.getString("maPhong")
                ));
            }
        } catch (Exception e) {
            System.err.println(" Lỗi khi truy vấn danh sách ghế:");
            e.printStackTrace();
        }
        return list;
    }

    
    public Ghe getById(String soGhe, String maPhong) {
        String sql = "SELECT * FROM Ghe WHERE soGhe = ? AND maPhong = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, soGhe);
            ps.setString(2, maPhong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Ghe(
                        rs.getString("soGhe"),
                        rs.getString("maPhong")
                );
            }
        } catch (Exception e) {
            System.err.println(" Lỗi khi tìm ghế theo ID:");
            e.printStackTrace();
        }
        return null;
    }
}
