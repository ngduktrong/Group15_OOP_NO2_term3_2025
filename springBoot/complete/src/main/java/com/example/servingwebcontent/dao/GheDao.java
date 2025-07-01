package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.Ghe;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GheDao {

    // ✅ Lấy toàn bộ ghế
    public List<Ghe> getAll() {
        List<Ghe> list = new ArrayList<>();
        String sql = "SELECT * FROM Ghe";

        try (Connection conn = AivenConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Ghe(
                        rs.getString("soGhe"),
                        rs.getInt("maPhong") // Đổi sang getInt
                ));
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi truy vấn danh sách ghế:");
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Lấy ghế theo ID (soGhe + maPhong)
    public Ghe getById(String soGhe, int maPhong) { // Đổi tham số maPhong sang int
        String sql = "SELECT * FROM Ghe WHERE soGhe = ? AND maPhong = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, soGhe);
            ps.setInt(2, maPhong); // Đổi sang setInt
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Ghe(
                        rs.getString("soGhe"),
                        rs.getInt("maPhong") // Đổi sang getInt
                );
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm ghế theo ID:");
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Tìm ghế theo SoGhe (giúp edit/delete từ controller)
    public Ghe getBySoGhe(String soGhe) {
        String sql = "SELECT * FROM Ghe WHERE soGhe = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, soGhe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Ghe(
                        rs.getString("soGhe"),
                        rs.getInt("maPhong") // Đổi sang getInt
                );
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm ghế theo soGhe:");
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Lấy danh sách ghế theo mã phòng (cho khách hàng)
    public List<Ghe> getByMaPhong(int maPhong) {
        List<Ghe> list = new ArrayList<>();
        String sql = "SELECT * FROM Ghe WHERE maPhong = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhong); // Giữ nguyên vì đã là int
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ghe(
                        rs.getString("soGhe"),
                        rs.getInt("maPhong") // Đổi sang getInt
                ));
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm ghế theo phòng:");
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Thêm ghế mới
    public void create(Ghe ghe) {
        String sql = "INSERT INTO Ghe (soGhe, maPhong) VALUES (?, ?)";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ghe.getSoGhe());
            ps.setInt(2, ghe.getMaPhong()); // Đổi sang setInt
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm ghế:");
            e.printStackTrace();
        }
    }

    // ✅ Cập nhật thông tin ghế
    public void update(Ghe ghe) {
        String sql = "UPDATE Ghe SET maPhong = ? WHERE soGhe = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ghe.getMaPhong()); // Đổi sang setInt
            ps.setString(2, ghe.getSoGhe());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật ghế:");
            e.printStackTrace();
        }
    }

    // ✅ Xoá ghế theo soGhe
    public void delete(String soGhe) {
        String sql = "DELETE FROM Ghe WHERE soGhe = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, soGhe);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Lỗi khi xoá ghế:");
            e.printStackTrace();
        }
    }
}