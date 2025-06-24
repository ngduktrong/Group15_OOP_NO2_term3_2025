package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.ChiTietHoaDon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChiTietHoaDonDao {
    
    public void create(ChiTietHoaDon ct) {
        String sql = "INSERT INTO ChiTietHoaDon (MaHoaDon, MaVe) VALUES (?, ?)";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, ct.getMaHoaDon());
            pst.setInt(2, ct.getMaVe());
            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    public ChiTietHoaDon getById(int maHoaDon, int maVe) {
        String sql = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaVe = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, maHoaDon);
            pst.setInt(2, maVe);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToChiTietHoaDon(rs);
                }
            }
        } catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ChiTietHoaDon> getAll() {
        List<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDon";
        try (Connection conn = AivenConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToChiTietHoaDon(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

   
    public void update(int oldMaHoaDon, int oldMaVe, ChiTietHoaDon ctNew) {
        String sql = "UPDATE ChiTietHoaDon SET MaHoaDon = ?, MaVe = ? WHERE MaHoaDon = ? AND MaVe = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, ctNew.getMaHoaDon());
            pst.setInt(2, ctNew.getMaVe());
         
            pst.setInt(3, oldMaHoaDon);
            pst.setInt(4, oldMaVe);
            pst.executeUpdate();
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

  
    public void delete(int maHoaDon, int maVe) {
        String sql = "DELETE FROM ChiTietHoaDon WHERE MaHoaDon = ? AND MaVe = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, maHoaDon);
            pst.setInt(2, maVe);
            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ChiTietHoaDon mapResultSetToChiTietHoaDon(ResultSet rs) throws SQLException {
        ChiTietHoaDon ct = new ChiTietHoaDon();
        ct.setMaHoaDon(rs.getInt("MaHoaDon"));
        ct.setMaVe(rs.getInt("MaVe"));
        return ct;
    }
}