
package com.group15.dao;

import com.group15.DataBase.*;
import com.group15.models.SuatChieu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SuatChieuDao {
    
    public void create(SuatChieu s) {
        String sql = "INSERT INTO SuatChieu(MaSuatChieu, MaPhim, MaPhong, NgayGioChieu) VALUES(?,?,?,?)";
        try (Connection c = DataBase.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, s.getMaSuatChieu());
            ps.setInt(2, s.getMaPhim());
            ps.setInt(3, s.getMaPhong());
            ps.setString(4, s.getNgayGioChieu());
            ps.executeUpdate();
            System.out.println("da them xuat chieu co id=" + s.getMaSuatChieu());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public SuatChieu getById(int id) {
        String sql = "SELECT * FROM SuatChieu WHERE MaSuatChieu = ?";
        try (Connection c = DataBase.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SuatChieu s = new SuatChieu();
                s.setMaSuatChieu(rs.getInt("MaSuatChieu"));
                s.setMaPhim(rs.getInt("MaPhim"));
                s.setMaPhong(rs.getInt("MaPhong"));
                s.setNgayGioChieu(rs.getString("NgayGioChieu"));
                return s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<SuatChieu> getAll() {
        List<SuatChieu> list = new ArrayList<>();
        String sql = "SELECT * FROM SuatChieu";
        try (Connection c = DataBase.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                SuatChieu s = new SuatChieu();
                s.setMaSuatChieu(rs.getInt("MaSuatChieu"));
                s.setMaPhim(rs.getInt("MaPhim"));
                s.setMaPhong(rs.getInt("MaPhong"));
                s.setNgayGioChieu(rs.getString("NgayGioChieu"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

   
    public void update(SuatChieu s) {
        String sql = "UPDATE SuatChieu SET MaPhim=?, MaPhong=?, NgayGioChieu=? WHERE MaSuatChieu=?";
        try (Connection c = DataBase.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, s.getMaPhim());
            ps.setInt(2, s.getMaPhong());
            ps.setString(3, s.getNgayGioChieu());
            ps.setInt(4, s.getMaSuatChieu());
            ps.executeUpdate();
            System.out.println("da cap nhat xuat chieu co id=" + s.getMaSuatChieu());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void delete(int id) {
        String sql = "DELETE FROM SuatChieu WHERE MaSuatChieu=?";
        try (Connection c = DataBase.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("da xoa xuat chieu co id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
