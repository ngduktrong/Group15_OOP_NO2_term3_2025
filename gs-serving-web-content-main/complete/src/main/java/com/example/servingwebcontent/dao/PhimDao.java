// src/com/group15/dao/PhimDAO.java
package com.group15.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group15.DataBase.DataBase;
import com.example.servingwebcontent.models.Phim;

public class PhimDao {
    // CREATE
    public void create(Phim p) {
        String sql = "INSERT INTO Phim(MaPhim, TenPhim, ThoiLuong, NgayKhoiChieu, NuocSanXuat, DinhDang, MoTa, DaoDien, DuongDanPoster) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, p.getMaPhim());
            ps.setString(2, p.getTenPhim());
            ps.setInt(3, p.getThoiLuong());
            ps.setString(4, p.getNgayKhoiChieu());
            ps.setString(5, p.getNuocSanXuat());
            ps.setString(6, p.getDinhDang());
            ps.setString(7, p.getMoTa());
            ps.setString(8, p.getDaoDien());
            ps.setString(9, p.getDuongDanPoster());
            ps.executeUpdate();
            System.out.println("da them phim: " + p.getTenPhim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ by ID
    public Phim getById(int id) {
        String sql = "SELECT * FROM Phim WHERE MaPhim = ?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // READ all
    public List<Phim> getAll() {
        List<Phim> list = new ArrayList<>();
        String sql = "SELECT * FROM Phim";
        try (Connection c = DataBase.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    public void update(Phim p) {
        String sql = "UPDATE Phim SET TenPhim=?, ThoiLuong=?, NgayKhoiChieu=?, NuocSanXuat=?, DinhDang=?, MoTa=?, DaoDien=?, DuongDanPoster=? WHERE MaPhim=?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTenPhim());
            ps.setInt(2, p.getThoiLuong());
            ps.setString(3, p.getNgayKhoiChieu());
            ps.setString(4, p.getNuocSanXuat());
            ps.setString(5, p.getDinhDang());
            ps.setString(6, p.getMoTa());
            ps.setString(7, p.getDaoDien());
            ps.setString(8, p.getDuongDanPoster());
            ps.setInt(9, p.getMaPhim());
            ps.executeUpdate();
            System.out.println("da cap nhat phim co ma id=" + p.getMaPhim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int id) {
        String sql = "DELETE FROM Phim WHERE MaPhim=?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("da xoa phim co id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
