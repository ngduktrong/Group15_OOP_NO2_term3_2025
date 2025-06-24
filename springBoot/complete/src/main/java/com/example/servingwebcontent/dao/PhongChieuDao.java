package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.PhongChieu;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PhongChieuDao {
    
    public void create(PhongChieu p) {
        String sql = "INSERT INTO PhongChieu (TenPhong, SoLuongGhe, LoaiPhong) VALUES (?, ?, ?)";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
           
            ps.setString(1, p.getTenPhong());
            ps.setInt(2, p.getSoLuongGhe());
            ps.setString(3, p.getLoaiPhong());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    p.setMaPhong(keys.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   
    public PhongChieu getById(int id) {
        String sql = "SELECT * FROM PhongChieu WHERE MaPhong = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPhongChieu(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

   
    public List<PhongChieu> getAll() {
        List<PhongChieu> list = new ArrayList<>();
        String sql = "SELECT * FROM PhongChieu";
        try (Connection c = AivenConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToPhongChieu(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public void update(PhongChieu p) {
        String sql = "UPDATE PhongChieu SET TenPhong = ?, SoLuongGhe = ?, LoaiPhong = ? WHERE MaPhong = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getTenPhong());
            ps.setInt(2, p.getSoLuongGhe());
            ps.setString(3, p.getLoaiPhong());
            ps.setInt(4, p.getMaPhong());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM PhongChieu WHERE MaPhong = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   
    private PhongChieu mapResultSetToPhongChieu(ResultSet rs) throws SQLException {
        PhongChieu p = new PhongChieu();
        p.setMaPhong(rs.getInt("MaPhong"));
        p.setTenPhong(rs.getString("TenPhong"));
        p.setSoLuongGhe(rs.getInt("SoLuongGhe"));
        p.setLoaiPhong(rs.getString("LoaiPhong"));
        return p;
    }
}