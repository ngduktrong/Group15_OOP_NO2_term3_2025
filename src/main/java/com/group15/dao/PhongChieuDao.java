
package com.group15.dao;

import com.group15.DataBase.*;
import com.group15.models.PhongChieu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhongChieuDao {
    
    public void create(PhongChieu p) {
        String sql = "INSERT INTO PhongChieu(MaPhong, TenPhong, SoLuongGhe, LoaiPhong) VALUES(?,?,?,?)";
        try (Connection c = DataBase.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, p.getMaPhong());
            ps.setString(2, p.getTenPhong());
            ps.setInt(3, p.getSoLuongGhe());
            ps.setString(4, p.getLoaiPhong());
            ps.executeUpdate();
            System.out.println("da them phong chieu: " + p.getTenPhong());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public PhongChieu getById(int id) {
        String sql = "SELECT * FROM PhongChieu WHERE MaPhong = ?";
        try (Connection c = DataBase.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new PhongChieu(
                    rs.getInt("MaPhong"),
                    rs.getString("TenPhong"),
                    rs.getInt("SoLuongGhe"),
                    rs.getString("LoaiPhong")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   
    public List<PhongChieu> getAll() {
        List<PhongChieu> list = new ArrayList<>();
        String sql = "SELECT * FROM PhongChieu";
        try (Connection c = DataBase.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new PhongChieu(
                    rs.getInt("MaPhong"),
                    rs.getString("TenPhong"),
                    rs.getInt("SoLuongGhe"),
                    rs.getString("LoaiPhong")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public void update(PhongChieu p) {
        String sql = "UPDATE PhongChieu SET TenPhong=?, SoLuongGhe=?, LoaiPhong=? WHERE MaPhong=?";
        try (Connection c = DataBase.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getTenPhong());
            ps.setInt(2, p.getSoLuongGhe());
            ps.setString(3, p.getLoaiPhong());
            ps.setInt(4, p.getMaPhong());
            ps.executeUpdate();
            System.out.println("da cap nhat phong chieu co id=" + p.getMaPhong());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  
    public void delete(int id) {
        String sql = "DELETE FROM PhongChieu WHERE MaPhong=?";
        try (Connection c = DataBase.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("da xoa phong chieu co id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
