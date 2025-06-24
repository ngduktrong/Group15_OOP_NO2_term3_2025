package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.HoaDon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HoaDonDao {
    
    public void create(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (MaKhachHang, NgayLap, TongTien) VALUES (?, ?, ?)";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
           
            pst.setInt(1, hd.getMaKhachHang());
            pst.setString(2, hd.getNgayLap());
            pst.setDouble(3, hd.getTongTien());
            pst.executeUpdate();
            
            try (ResultSet keys = pst.getGeneratedKeys()) {
                if (keys.next()) {
                    hd.setMaHoaDon(keys.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            
        }
    }

    // Lấy hóa đơn theo ID
    public HoaDon getById(int id) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToHoaDon(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try (Connection conn = AivenConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToHoaDon(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

   
    public void update(HoaDon hd) {
        String sql = "UPDATE HoaDon SET MaKhachHang = ?, NgayLap = ?, TongTien = ? WHERE MaHoaDon = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, hd.getMaKhachHang());
            pst.setString(2, hd.getNgayLap());
            pst.setDouble(3, hd.getTongTien());
            pst.setInt(4, hd.getMaHoaDon());
            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   
    private HoaDon mapResultSetToHoaDon(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(rs.getInt("MaHoaDon"));
        hd.setMaKhachHang(rs.getInt("MaKhachHang"));
        hd.setNgayLap(rs.getString("NgayLap"));
        hd.setTongTien(rs.getDouble("TongTien"));
        return hd;
    }
}