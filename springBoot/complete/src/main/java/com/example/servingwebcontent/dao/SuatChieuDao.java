package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.SuatChieu;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SuatChieuDao {

    public void create(SuatChieu s, int durationMinutes) {
        if (!isPhongTrong(s.getMaPhong(), Timestamp.valueOf(s.getNgayGioChieu()), durationMinutes)) {
            throw new RuntimeException("Phòng đã có suất chiếu trùng thời gian!");
        }

        String sql = "INSERT INTO SuatChieu (MaPhim, MaPhong, NgayGioChieu) VALUES (?, ?, ?)";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, s.getMaPhim());
            ps.setInt(2, s.getMaPhong());
            ps.setTimestamp(3, Timestamp.valueOf(s.getNgayGioChieu()));
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

    public boolean isPhongTrong(int maPhong, Timestamp startTime, int durationMinutes) {
        String sql = "SELECT * FROM SuatChieu WHERE MaPhong = ? AND " +
                     "((NgayGioChieu BETWEEN ? AND ?) " +
                     "OR (? BETWEEN NgayGioChieu AND DATE_ADD(NgayGioChieu, INTERVAL ? MINUTE)))";

        Timestamp endTime = Timestamp.valueOf(startTime.toLocalDateTime().plusMinutes(durationMinutes));

        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, maPhong);
            ps.setTimestamp(2, startTime);
            ps.setTimestamp(3, endTime);
            ps.setTimestamp(4, startTime);
            ps.setInt(5, durationMinutes);

            try (ResultSet rs = ps.executeQuery()) {
                return !rs.next(); // True nếu không có bản ghi trùng lịch
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public SuatChieu getById(int id) {
        String sql = "SELECT * FROM SuatChieu WHERE MaSuatChieu = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapResultSetToSuatChieu(rs);
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
            while (rs.next()) list.add(mapResultSetToSuatChieu(rs));
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
            ps.setTimestamp(3, Timestamp.valueOf(s.getNgayGioChieu()));
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

    public List<SuatChieu> getByMaPhong(int maPhong) {
        List<SuatChieu> list = new ArrayList<>();
        String sql = "SELECT * FROM SuatChieu WHERE MaPhong = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maPhong);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToSuatChieu(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SuatChieu> getByMaPhim(int maPhim) {
        List<SuatChieu> list = new ArrayList<>();
        String sql = "SELECT * FROM SuatChieu WHERE MaPhim = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maPhim);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToSuatChieu(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SuatChieu> getByMaPhongAndPhim(int maPhong, int maPhim) {
        List<SuatChieu> list = new ArrayList<>();
        String sql = "SELECT * FROM SuatChieu WHERE MaPhong = ? AND MaPhim = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, maPhong);
            ps.setInt(2, maPhim);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToSuatChieu(rs));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    private SuatChieu mapResultSetToSuatChieu(ResultSet rs) throws SQLException {
        SuatChieu s = new SuatChieu();
        s.setMaSuatChieu(rs.getInt("MaSuatChieu"));
        s.setMaPhim(rs.getInt("MaPhim"));
        s.setMaPhong(rs.getInt("MaPhong"));
        Timestamp ts = rs.getTimestamp("NgayGioChieu");
        if (ts != null) s.setNgayGioChieu(ts.toLocalDateTime());
        return s;
    }
}
