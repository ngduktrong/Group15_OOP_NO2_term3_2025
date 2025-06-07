package com.group15.dao;

import com.group15.DataBase.DataBase;
import com.group15.models.Ve;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeDao {
    // Thêm vé mới
    public void insert(Ve ve) {
        String sql = "INSERT INTO Ve (MaVe, MaSuatChieu, MaPhong, SoGhe, MaHoaDon, GiaVe, TrangThai, NgayDat) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ve.getMaVe());
            pst.setInt(2, ve.getMaSuatChieu());
            pst.setInt(3, ve.getMaPhong());
            pst.setString(4, ve.getSoGhe());
            pst.setInt(5, ve.getMaHoaDon());
            pst.setDouble(6, ve.getGiaVe());
            pst.setString(7, ve.getTrangThai());
            pst.setString(8, ve.getNgayDat());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy vé theo ID
    public Ve getById(int id) {
        String sql = "SELECT * FROM Ve WHERE MaVe = ?";
        try (Connection conn = DataBase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Ve(
                    rs.getInt("MaVe"),
                    rs.getInt("MaSuatChieu"),
                    rs.getInt("MaPhong"),
                    rs.getString("SoGhe"),
                    rs.getInt("MaHoaDon"),
                    rs.getDouble("GiaVe"),
                    rs.getString("TrangThai"),
                    rs.getString("NgayDat")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy tất cả vé
    public List<Ve> getAll() {
        List<Ve> list = new ArrayList<>();
        String sql = "SELECT * FROM Ve";
        try (Connection conn = DataBase.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Ve(
                    rs.getInt("MaVe"),
                    rs.getInt("MaSuatChieu"),
                    rs.getInt("MaPhong"),
                    rs.getString("SoGhe"),
                    rs.getInt("MaHoaDon"),
                    rs.getDouble("GiaVe"),
                    rs.getString("TrangThai"),
                    rs.getString("NgayDat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Cập nhật vé
    public void update(Ve ve) {
        String sql = "UPDATE Ve SET MaSuatChieu=?, MaPhong=?, SoGhe=?, MaHoaDon=?, GiaVe=?, TrangThai=?, NgayDat=? "
                   + "WHERE MaVe=?";
        try (Connection conn = DataBase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ve.getMaSuatChieu());
            pst.setInt(2, ve.getMaPhong());
            pst.setString(3, ve.getSoGhe());
            pst.setInt(4, ve.getMaHoaDon());
            pst.setDouble(5, ve.getGiaVe());
            pst.setString(6, ve.getTrangThai());
            pst.setString(7, ve.getNgayDat());
            pst.setInt(8, ve.getMaVe());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa vé
    public void delete(int id) {
        String sql = "DELETE FROM Ve WHERE MaVe=?";
        try (Connection conn = DataBase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
