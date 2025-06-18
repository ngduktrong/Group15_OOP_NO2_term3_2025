package com.group15.dao;

import com.group15.DataBase.DataBase;
import com.example.servingwebcontent.models.ChiTietHoaDon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDao {
    public void insert(ChiTietHoaDon ct) {
        String sql = "INSERT INTO ChiTietHoaDon (MaHoaDon, MaVe) VALUES (?, ?)";
        try (Connection conn = DataBase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ct.getMaHoaDon());
            pst.setInt(2, ct.getMaVe());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ChiTietHoaDon getById(int maHoaDon, int maVe) {
        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon=? AND MaVe=?";
        try (Connection conn = DataBase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, maHoaDon);
            pst.setInt(2, maVe);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new ChiTietHoaDon(
                    rs.getInt("MaHoaDon"),
                    rs.getInt("MaVe")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ChiTietHoaDon> getAll() {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon";
        try (Connection conn = DataBase.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new ChiTietHoaDon(
                    rs.getInt("MaHoaDon"),
                    rs.getInt("MaVe")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(ChiTietHoaDon ctOld, ChiTietHoaDon ctNew) {
        String sql = "UPDATE ChiTietHoaDon SET MaHoaDon=?, MaVe=? WHERE MaHoaDon=? AND MaVe=?";
        try (Connection conn = DataBase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            // giá trị mới
            pst.setInt(1, ctNew.getMaHoaDon());
            pst.setInt(2, ctNew.getMaVe());
            // khóa cũ
            pst.setInt(3, ctOld.getMaHoaDon());
            pst.setInt(4, ctOld.getMaVe());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int maHoaDon, int maVe) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon=? AND MaVe=?";
        try (Connection conn = DataBase.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, maHoaDon);
            pst.setInt(2, maVe);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
