package com.group15.dao;
// PhimDao.java
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group15.DataBase.DataBase;
import com.group15.models.Phim;

public class PhimDao {
    // Thêm phim
    public boolean insert(Phim phim) {
        String sql = "INSERT INTO phim (MaPhim, TenPhim, ThoiLuong, NgayKhoiChieu, NuocSanXuat, DinhDang, MoTa, DaoDien, DuongDanPoster) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBase.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, phim.getMaPhim());
            ps.setString(2, phim.getTenPhim());
            ps.setInt(3, phim.getThoiLuong());
            ps.setString(4, phim.getNgayKhoiChieu());
            ps.setString(5, phim.getNuocSanXuat());
            ps.setString(6, phim.getDinhDang());
            ps.setString(7, phim.getMoTa());
            ps.setString(8, phim.getDaoDien());
            ps.setString(9, phim.getDuongDanPoster());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy tất cả phim
    public List<Phim> getAll() {
        List<Phim> list = new ArrayList<>();
        String sql = "SELECT * FROM phim";
        try (Connection conn = DataBase.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Phim phim = new Phim(
                    rs.getString("TenPhim"),
                    rs.getInt("MaPhim"),
                    rs.getInt("ThoiLuong"),
                    rs.getString("NgayKhoiChieu"),
                    rs.getString("NuocSanXuat"),
                    rs.getString("DinhDang"),
                    rs.getString("MoTa"),
                    rs.getString("DaoDien"),
                    rs.getString("DuongDanPoster")
                );
                list.add(phim);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Xóa phim theo mã
    public boolean delete(int maPhim) {
        String sql = "DELETE FROM phim WHERE MaPhim = ?";
        try (Connection conn = DataBase.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhim);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sửa phim
    public boolean update(Phim phim) {
        String sql = "UPDATE phim SET TenPhim=?, ThoiLuong=?, NgayKhoiChieu=?, NuocSanXuat=?, DinhDang=?, MoTa=?, DaoDien=?, DuongDanPoster=? WHERE MaPhim=?";
        try (Connection conn = DataBase.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phim.getTenPhim());
            ps.setInt(2, phim.getThoiLuong());
            ps.setString(3, phim.getNgayKhoiChieu());
            ps.setString(4, phim.getNuocSanXuat());
            ps.setString(5, phim.getDinhDang());
            ps.setString(6, phim.getMoTa());
            ps.setString(7, phim.getDaoDien());
            ps.setString(8, phim.getDuongDanPoster());
            ps.setInt(9, phim.getMaPhim());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}