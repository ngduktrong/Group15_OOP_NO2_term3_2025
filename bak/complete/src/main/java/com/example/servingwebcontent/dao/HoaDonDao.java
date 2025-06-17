package com.group15.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group15.DataBase.DataBase;
import com.example.servingwebcontent.models.HoaDon;

public class HoaDonDao {
    public void insert(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (MaHoaDon, MaKhachHang, NgayLap, TongTien) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBase.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, hd.getMaHoaDon());
            pst.setInt(2, hd.getMaKhachHang());
            pst.setString(3, hd.getNgayLap());
            pst.setDouble(4, hd.getTongTien());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HoaDon getById(int id) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon=?";
        try (Connection conn = DataBase.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new HoaDon(
                    rs.getInt("MaHoaDon"),
                    rs.getInt("MaKhachHang"),
                    rs.getString("NgayLap"),
                    rs.getDouble("TongTien")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";
        try (Connection conn = DataBase.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new HoaDon(
                    rs.getInt("MaHoaDon"),
                    rs.getInt("MaKhachHang"),
                    rs.getString("NgayLap"),
                    rs.getDouble("TongTien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(HoaDon hd) {
        String sql = "UPDATE HoaDon SET MaKhachHang=?, NgayLap=?, TongTien=? WHERE MaHoaDon=?";
        try (Connection conn = DataBase.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, hd.getMaKhachHang());
            pst.setString(2, hd.getNgayLap());
            pst.setDouble(3, hd.getTongTien());
            pst.setInt(4, hd.getMaHoaDon());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon=?";
        try (Connection conn = DataBase.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
