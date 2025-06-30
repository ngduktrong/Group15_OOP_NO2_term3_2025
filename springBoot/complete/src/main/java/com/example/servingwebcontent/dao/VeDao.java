// VeDao.java (updated with getVeByMaHoaDon)
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
                list.add(mapRow(rs));
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
                return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(Ve ve) {
    String sql = "INSERT INTO Ve (MaSuatChieu, MaPhong, SoGhe, MaHoaDon, GiaVe, TrangThai, NgayDat) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = AivenConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        conn.setAutoCommit(true); // <<< THÊM DÒNG NÀY

        pst.setInt(1, ve.getMaSuatChieu());
        pst.setInt(2, ve.getMaPhong());
        pst.setString(3, ve.getSoGhe());
        pst.setInt(4, ve.getMaHoaDon());
        pst.setDouble(5, ve.getGiaVe());
        pst.setString(6, ve.getTrangThai());
        pst.setString(7, ve.getNgayDat());

        int affected = pst.executeUpdate();
        if (affected == 0) {
            System.out.println("⚠️ Không insert được vé vào DB.");
        }

        try (ResultSet keys = pst.getGeneratedKeys()) {
            if (keys.next()) {
                ve.setMaVe(keys.getInt(1));
                System.out.println("✅ Đã tạo vé mới: " + ve.getMaVe());
            }
        }
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

    public List<Ve> getVesByIds(List<Integer> veIds) {
        List<Ve> list = new ArrayList<>();
        if (veIds == null || veIds.isEmpty()) {
            return list;
        }
        StringBuilder sql = new StringBuilder("SELECT * FROM Ve WHERE MaVe IN (");
        for (int i = 0; i < veIds.size(); i++) {
            sql.append("?");
            if (i < veIds.size() - 1) sql.append(",");
        }
        sql.append(")");

        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < veIds.size(); i++) pst.setInt(i + 1, veIds.get(i));
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Lấy vé theo mã hóa đơn
     */
    public List<Ve> getVeByMaHoaDon(int maHoaDon) {
        List<Ve> list = new ArrayList<>();
        String sql = "SELECT * FROM Ve WHERE MaHoaDon = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, maHoaDon);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private Ve mapRow(ResultSet rs) throws SQLException {
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
}