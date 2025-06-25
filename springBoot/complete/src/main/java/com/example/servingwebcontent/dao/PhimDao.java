package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.Phim;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PhimDao {

    // Lấy tất cả phim
    public List<Phim> getAll() {
        List<Phim> list = new ArrayList<>();
        String sql = "SELECT MaPhim, TenPhim, ThoiLuong, NgayKhoiChieu, NuocSanXuat, DinhDang, MoTa, DaoDien, DuongDanPoster FROM Phim";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Phim p = new Phim();
                p.setMaPhim(rs.getInt("MaPhim"));
                p.setTenPhim(rs.getString("TenPhim"));
                p.setThoiLuong(rs.getInt("ThoiLuong"));

                Date sqlDate = rs.getDate("NgayKhoiChieu");
                if (sqlDate != null) {
                    p.setNgayKhoiChieu(sqlDate.toLocalDate());
                } else {
                    p.setNgayKhoiChieu(null);
                }

                p.setNuocSanXuat(rs.getString("NuocSanXuat"));
                p.setDinhDang(rs.getString("DinhDang"));
                p.setMoTa(rs.getString("MoTa"));
                p.setDaoDien(rs.getString("DaoDien"));
                p.setDuongDanPoster(rs.getString("DuongDanPoster"));

                list.add(p);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // hoặc dùng logger.error(...)
        }
        return list;
    }

    // Lấy phim theo id
    public Phim getById(int id) {
        String sql = "SELECT MaPhim, TenPhim, ThoiLuong, NgayKhoiChieu, NuocSanXuat, DinhDang, MoTa, DaoDien, DuongDanPoster FROM Phim WHERE MaPhim = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Phim p = new Phim();
                    p.setMaPhim(rs.getInt("MaPhim"));
                    p.setTenPhim(rs.getString("TenPhim"));
                    p.setThoiLuong(rs.getInt("ThoiLuong"));
                    Date sqlDate = rs.getDate("NgayKhoiChieu");
                    if (sqlDate != null) {
                        p.setNgayKhoiChieu(sqlDate.toLocalDate());
                    }
                    p.setNuocSanXuat(rs.getString("NuocSanXuat"));
                    p.setDinhDang(rs.getString("DinhDang"));
                    p.setMoTa(rs.getString("MoTa"));
                    p.setDaoDien(rs.getString("DaoDien"));
                    p.setDuongDanPoster(rs.getString("DuongDanPoster"));
                    return p;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tạo phim mới
    public void create(Phim phim) {
        String sql = "INSERT INTO Phim (TenPhim, ThoiLuong, NgayKhoiChieu, NuocSanXuat, DinhDang, MoTa, DaoDien, DuongDanPoster) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, phim.getTenPhim());
            pst.setInt(2, phim.getThoiLuong());

            LocalDate ld = phim.getNgayKhoiChieu();
            if (ld != null) {
                pst.setDate(3, Date.valueOf(ld));
            } else {
                pst.setNull(3, Types.DATE);
            }

            pst.setString(4, phim.getNuocSanXuat());
            pst.setString(5, phim.getDinhDang());
            pst.setString(6, phim.getMoTa());
            pst.setString(7, phim.getDaoDien());
            pst.setString(8, phim.getDuongDanPoster());

            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật phim
    public void update(Phim phim) {
        String sql = "UPDATE Phim SET TenPhim=?, ThoiLuong=?, NgayKhoiChieu=?, NuocSanXuat=?, DinhDang=?, MoTa=?, DaoDien=?, DuongDanPoster=? " +
                     "WHERE MaPhim=?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, phim.getTenPhim());
            pst.setInt(2, phim.getThoiLuong());

            LocalDate ld = phim.getNgayKhoiChieu();
            if (ld != null) {
                pst.setDate(3, Date.valueOf(ld));
            } else {
                pst.setNull(3, Types.DATE);
            }

            pst.setString(4, phim.getNuocSanXuat());
            pst.setString(5, phim.getDinhDang());
            pst.setString(6, phim.getMoTa());
            pst.setString(7, phim.getDaoDien());
            pst.setString(8, phim.getDuongDanPoster());

            pst.setInt(9, phim.getMaPhim());
            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Xóa phim
    public void delete(int id) {
        String sql = "DELETE FROM Phim WHERE MaPhim = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
