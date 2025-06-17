package com.group15.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group15.DataBase.DataBase;
import com.example.servingwebcontent.models.Ve;

public class VeDao {
    
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
        }// Neeus truy van sql loi , ctrinh se khong bi dung dot ngot ma se in thong tin loi ra 
        return list;
    }

    
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
