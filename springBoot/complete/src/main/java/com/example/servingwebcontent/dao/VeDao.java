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

            conn.setAutoCommit(true);

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
     * ✅ Lấy vé theo mã hóa đơn
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

    /**
     * ✅ Tính tổng số vé đã thanh toán (TrangThai = 'paid')
     */
    public int getSoVeDaThanhToan() {
        String sql = "SELECT COUNT(*) AS SoLuong FROM Ve WHERE TrangThai = 'paid'";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
    public boolean updateTrangThaiVeToPaid(int maVe) {
    try (Connection conn = AivenConnection.getConnection()) {
        conn.setAutoCommit(false); // Đảm bảo tất cả cùng commit hoặc rollback

        // 1. Lấy timestamp tại đây trước
        Timestamp ngayDat = new Timestamp(System.currentTimeMillis());

        // 2. Cập nhật trạng thái và thời gian đặt vé
        String updateVeSql = "UPDATE Ve SET TrangThai = 'paid', NgayDat = ? WHERE MaVe = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateVeSql)) {
            stmt.setTimestamp(1, ngayDat);
            stmt.setInt(2, maVe);
            int updated = stmt.executeUpdate();
            if (updated == 0) {
                conn.rollback();
                return false;
            }
        }

        // 3. Lấy MaHoaDon từ vé vừa thanh toán
        int maHoaDon = -1;
        String getHoaDonSql = "SELECT MaHoaDon FROM Ve WHERE MaVe = ?";
        try (PreparedStatement stmt = conn.prepareStatement(getHoaDonSql)) {
            stmt.setInt(1, maVe);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                maHoaDon = rs.getInt("MaHoaDon");
            }
        }

        // 4. Cập nhật NgayLap cho hóa đơn
        if (maHoaDon > 0) {
            String updateHoaDonSql = "UPDATE HoaDon SET NgayLap = ? WHERE MaHoaDon = ?";
            try (PreparedStatement stmt = conn.prepareStatement(updateHoaDonSql)) {
                stmt.setTimestamp(1, ngayDat);
                stmt.setInt(2, maHoaDon);
                stmt.executeUpdate();
            }
        }

        conn.commit();
        return true;

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        return false;
    }
    }
    public List<Ve> getVeByMaKhachHang(int maKhachHang) {
    List<Ve> list = new ArrayList<>();
    String sql = "SELECT v.* FROM Ve v JOIN HoaDon h ON v.MaHoaDon = h.MaHoaDon WHERE h.MaKhachHang = ?";
    try (Connection conn = AivenConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, maKhachHang);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(mapRow(rs));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
    public List<Integer> getMaKhachHangCuaVeSapChieuTrong30Phut() {
    List<Integer> result = new ArrayList<>();
    String sql = "SELECT DISTINCT hd.MaKhachHang " +
                 "FROM Ve v " +
                 "JOIN SuatChieu sc ON v.MaSuatChieu = sc.MaSuatChieu " +
                 "JOIN HoaDon hd ON v.MaHoaDon = hd.MaHoaDon " +
                 "WHERE v.TrangThai = 'paid' " +
                 "AND sc.NgayGioChieu BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 30 MINUTE)";

    try (Connection conn = AivenConnection.getConnection();
         PreparedStatement pst = conn.prepareStatement(sql);
         ResultSet rs = pst.executeQuery()) {

        while (rs.next()) {
            result.add(rs.getInt("MaKhachHang"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
    }
    public List<String> getSoGheDaDatBySuatChieu(int maSuatChieu) {
    List<String> list = new ArrayList<>();
    String sql = "SELECT SoGhe FROM Ve WHERE MaSuatChieu = ? AND TrangThai = 'paid'";
    try (Connection conn = AivenConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, maSuatChieu);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(rs.getString("SoGhe"));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
    }

}
