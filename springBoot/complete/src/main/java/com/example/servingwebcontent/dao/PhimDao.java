package com.example.servingwebcontent.dao;


import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.Phim;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class PhimDao {
  
    public List<Phim> getAll() {
        List<Phim> list = new ArrayList<>();
        String sql = "SELECT * FROM Phim";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Phim p = new Phim();
                p.setMaPhim(rs.getInt("MaPhim"));
                p.setTenPhim(rs.getString("TenPhim"));
                p.setThoiLuong(rs.getInt("ThoiLuong"));
                p.setNgayKhoiChieu(rs.getString("NgayKhoiChieu"));
                p.setNuocSanXuat(rs.getString("NuocSanXuat"));
                p.setDinhDang(rs.getString("DinhDang"));
                p.setMoTa(rs.getString("MoTa"));
                p.setDaoDien(rs.getString("DaoDien"));
                p.setDuongDanPoster(rs.getString("DuongDanPoster"));
                list.add(p);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public Phim getById(int id) {
        String sql = "SELECT * FROM Phim WHERE MaPhim = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Phim p = new Phim();
                    p.setMaPhim(rs.getInt("MaPhim"));
                    p.setTenPhim(rs.getString("TenPhim"));
                    p.setThoiLuong(rs.getInt("ThoiLuong"));
                    p.setNgayKhoiChieu(rs.getString("NgayKhoiChieu"));
                    p.setNuocSanXuat(rs.getString("NuocSanXuat"));
                    p.setDinhDang(rs.getString("DinhDang"));
                    p.setMoTa(rs.getString("MoTa"));
                    p.setDaoDien(rs.getString("DaoDien"));
                    p.setDuongDanPoster(rs.getString("DuongDanPoster"));
                    return p;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public void create(Phim p) {
        String sql = "INSERT INTO Phim(MaPhim, TenPhim, ThoiLuong, NgayKhoiChieu, NuocSanXuat, DinhDang, MoTa, DaoDien, DuongDanPoster) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, p.getMaPhim());
            pst.setString(2, p.getTenPhim());
            pst.setInt(3, p.getThoiLuong());
            pst.setString(4, p.getNgayKhoiChieu());
            pst.setString(5, p.getNuocSanXuat());
            pst.setString(6, p.getDinhDang());
            pst.setString(7, p.getMoTa());
            pst.setString(8, p.getDaoDien());
            pst.setString(9, p.getDuongDanPoster());
            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

  
    public void update(Phim p) {
        String sql = "UPDATE Phim SET TenPhim = ?, ThoiLuong = ?, NgayKhoiChieu = ?, NuocSanXuat = ?, DinhDang = ?, MoTa = ?, DaoDien = ?, DuongDanPoster = ? WHERE MaPhim = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, p.getTenPhim());
            pst.setInt(2, p.getThoiLuong());
            pst.setString(3, p.getNgayKhoiChieu());
            pst.setString(4, p.getNuocSanXuat());
            pst.setString(5, p.getDinhDang());
            pst.setString(6, p.getMoTa());
            pst.setString(7, p.getDaoDien());
            pst.setString(8, p.getDuongDanPoster());
            pst.setInt(9, p.getMaPhim());
            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

  
    public void delete(int id) {
        String sql = "DELETE FROM Phim WHERE MaPhim = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
