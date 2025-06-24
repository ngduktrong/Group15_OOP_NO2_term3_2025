package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.SuatChieu;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SuatChieuDao {
  
    public void create(SuatChieu s) {
        String sql = "INSERT INTO SuatChieu (MaPhim, MaPhong, NgayGioChieu) VALUES (?, ?, ?)";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
           
            ps.setInt(1, s.getMaPhim());
            ps.setInt(2, s.getMaPhong());
            ps.setString(3, s.getNgayGioChieu());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    s.setMaSuatChieu(keys.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public SuatChieu getById(int id) {
        String sql = "SELECT * FROM SuatChieu WHERE MaSuatChieu = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSuatChieu(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

   
    public List<SuatChieu> getAll() {
        List<SuatChieu> list = new ArrayList<>();
        String sql = "SELECT * FROM SuatChieu";
        try (Connection c = AivenConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToSuatChieu(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

   
    public void update(SuatChieu s) {
        String sql = "UPDATE SuatChieu SET MaPhim = ?, MaPhong = ?, NgayGioChieu = ? WHERE MaSuatChieu = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, s.getMaPhim());
            ps.setInt(2, s.getMaPhong());
            ps.setString(3, s.getNgayGioChieu());
            ps.setInt(4, s.getMaSuatChieu());
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   
    public void delete(int id) {
        String sql = "DELETE FROM SuatChieu WHERE MaSuatChieu = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    private SuatChieu mapResultSetToSuatChieu(ResultSet rs) throws SQLException {
        SuatChieu s = new SuatChieu();
        s.setMaSuatChieu(rs.getInt("MaSuatChieu"));
        s.setMaPhim(rs.getInt("MaPhim"));
        s.setMaPhong(rs.getInt("MaPhong"));
        s.setNgayGioChieu(rs.getString("NgayGioChieu"));
        return s;
    }
}
