package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.HoaDon;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HoaDonDao {

    // ✅ Tạo hóa đơn mới
    public void create(HoaDon hd) {
        String sql = "INSERT INTO HoaDon (MaNhanVien, MaKhachHang, TongTien) VALUES (?, ?, ?)";

        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setObject(1, hd.getMaNhanVien(), Types.INTEGER);
            pst.setObject(2, hd.getMaKhachHang(), Types.INTEGER);
            pst.setDouble(3, hd.getTongTien());

            pst.executeUpdate();
            try (ResultSet keys = pst.getGeneratedKeys()) {
                if (keys.next()) {
                    hd.setMaHoaDon(keys.getInt(1));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ✅ Lấy hóa đơn theo ID
    public HoaDon getById(int id) {
        String sql = "SELECT * FROM HoaDon WHERE MaHoaDon = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return mapResultSetToHoaDon(rs);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Lấy tất cả hóa đơn
    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon ORDER BY NgayLap DESC";
        try (Connection conn = AivenConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSetToHoaDon(rs));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Cập nhật hóa đơn
    public void update(HoaDon hd) {
        String sql = "UPDATE HoaDon SET MaNhanVien = ?, MaKhachHang = ?, TongTien = ? WHERE MaHoaDon = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setObject(1, hd.getMaNhanVien(), Types.INTEGER);
            pst.setObject(2, hd.getMaKhachHang(), Types.INTEGER);
            pst.setDouble(3, hd.getTongTien());
            pst.setInt(4, hd.getMaHoaDon());

            pst.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ✅ Xóa hóa đơn
    public void delete(int id) {
        String sql = "DELETE FROM HoaDon WHERE MaHoaDon = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // ✅ Lấy theo mã khách hàng
    public List<HoaDon> getByMaKhachHang(int maKH) {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE MaKhachHang = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, maKH);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToHoaDon(rs));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Lấy theo ngày cụ thể
    public List<HoaDon> getByNgayLap(String ngayLap) {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE DATE(NgayLap) = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, ngayLap);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToHoaDon(rs));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Tổng doanh thu theo ngày
    public double getTongDoanhThuTheoNgay(String ngayLap) {
        String sql = "SELECT SUM(TongTien) AS DoanhThu FROM HoaDon WHERE DATE(NgayLap) = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, ngayLap);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return rs.getDouble("DoanhThu");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // ✅ Lấy hóa đơn trong khoảng ngày
    public List<HoaDon> getByKhoangNgay(String tuNgay, String denNgay) {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE DATE(NgayLap) BETWEEN ? AND ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, tuNgay);
            pst.setString(2, denNgay);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToHoaDon(rs));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Tổng doanh thu trong khoảng ngày
    public double getTongDoanhThuTheoKhoangNgay(String tuNgay, String denNgay) {
        String sql = "SELECT SUM(TongTien) AS DoanhThu FROM HoaDon WHERE DATE(NgayLap) BETWEEN ? AND ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, tuNgay);
            pst.setString(2, denNgay);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return rs.getDouble("DoanhThu");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // ✅ Hàm chuyển đổi từ ResultSet thành HoaDon
    private HoaDon mapResultSetToHoaDon(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(rs.getInt("MaHoaDon"));

        int maNV = rs.getInt("MaNhanVien");
        hd.setMaNhanVien(rs.wasNull() ? null : maNV);

        int maKH = rs.getInt("MaKhachHang");
        hd.setMaKhachHang(rs.wasNull() ? null : maKH);

        hd.setNgayLap(rs.getString("NgayLap"));
        hd.setTongTien(rs.getDouble("TongTien"));
        return hd;
    }
}
