package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.Ve;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VeDao {

    public List<Ve> getAll() {
        List<Ve> list = new ArrayList<>();
        String sql = "SELECT * FROM Ve";
        try (Connection conn = AivenConnection.getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Ve getById(int id) {
        String sql = "SELECT * FROM Ve WHERE MaVe = ?";
        try (Connection conn = AivenConnection.getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(Ve ve) {
        String sql = "INSERT INTO Ve (MaVe, MaSuatChieu, MaPhong, SoGhe, MaHoaDon, GiaVe, TrangThai, NgayDat) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = AivenConnection.getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Ve ve) {
        String sql = "UPDATE Ve SET MaSuatChieu=?, MaPhong=?, SoGhe=?, MaHoaDon=?, GiaVe=?, TrangThai=?, NgayDat=? WHERE MaVe=?";
        try (Connection conn = AivenConnection.getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Ve WHERE MaVe = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Ve> getVeByMaKhachHang(int maKhachHang) {
    List<Ve> list = new ArrayList<>();
    String sql = """
        SELECT v.*, sc.NgayGioChieu
        FROM Ve v
        JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon
        JOIN SuatChieu sc ON v.MaSuatChieu = sc.MaSuatChieu
        WHERE hd.MaKhachHang = ?
    """;

    try (Connection conn = AivenConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, maKhachHang);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Ve ve = new Ve(
                rs.getInt("MaVe"),
                rs.getInt("MaSuatChieu"),
                rs.getInt("MaPhong"),
                rs.getString("SoGhe"),
                rs.getInt("MaHoaDon"),
                rs.getDouble("GiaVe"),
                rs.getString("TrangThai"),
                rs.getString("NgayDat")
            );
            Timestamp tg = rs.getTimestamp("NgayGioChieu");
            if (tg != null) {
                ve.setNgayGioChieu(tg.toLocalDateTime());
            }
            list.add(ve);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

    
}
