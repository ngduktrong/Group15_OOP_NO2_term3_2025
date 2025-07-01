package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.Ghe;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GheDao {

    // ✅ Lấy toàn bộ ghế (ĐÃ SỬA)
    public List<Ghe> getAll() {
        List<Ghe> list = new ArrayList<>();
        String sql = "SELECT soGhe, maPhong FROM Ghe"; // Bỏ TrangThai

        try (Connection conn = AivenConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Ghe(
                        rs.getString("soGhe"),
                        rs.getInt("maPhong")
                       
                ));
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi truy vấn danh sách ghế:");
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Lấy ghế theo ID (soGhe + maPhong) (ĐÃ SỬA)
    public Ghe getById(String soGhe, int maPhong) {
        String sql = "SELECT soGhe, maPhong FROM Ghe WHERE soGhe = ? AND maPhong = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, soGhe);
            ps.setInt(2, maPhong);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Ghe(
                        rs.getString("soGhe"),
                        rs.getInt("maPhong")
                        
                );
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm ghế theo ID:");
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Tìm ghế theo SoGhe (ĐÃ SỬA)
    public Ghe getBySoGhe(String soGhe) {
        String sql = "SELECT soGhe, maPhong FROM Ghe WHERE soGhe = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, soGhe);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Ghe(
                        rs.getString("soGhe"),
                        rs.getInt("maPhong")
                       
                );
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm ghế theo soGhe:");
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Lấy danh sách ghế theo mã phòng (ĐÃ SỬA)
    public List<Ghe> getByMaPhong(int maPhong) {
        List<Ghe> list = new ArrayList<>();
        String sql = "SELECT soGhe, maPhong FROM Ghe WHERE maPhong = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPhong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ghe(
                        rs.getString("soGhe"),
                        rs.getInt("maPhong")
                       
                ));
            }
        } catch (Exception e) {
            System.err.println("Lỗi khi tìm ghế theo phòng:");
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Thêm ghế mới (ĐÃ SỬA)
    public void create(Ghe ghe) {
        String sql = "INSERT INTO Ghe (soGhe, maPhong) VALUES (?, ?)"; // Bỏ TrangThai
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ghe.getSoGhe());
            ps.setInt(2, ghe.getMaPhong());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm ghế:");
            e.printStackTrace();
        }
    }

    // ✅ Cập nhật thông tin ghế (ĐÃ SỬA)
    public void update(Ghe ghe) {
        String sql = "UPDATE Ghe SET maPhong = ? WHERE soGhe = ?"; // Bỏ TrangThai
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ghe.getMaPhong());
            ps.setString(2, ghe.getSoGhe());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật ghế:");
            e.printStackTrace();
        }
    }

    // ✅ Xoá ghế theo soGhe (ĐÃ SỬA)
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
    
    // ✅ Xoá ghế theo soGhe và maPhong (THÊM MỚI - nên dùng)
    public void delete(String soGhe, int maPhong) {
        String sql = "DELETE FROM Ghe WHERE soGhe = ? AND maPhong = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, soGhe);
            ps.setInt(2, maPhong);
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Lỗi khi xoá ghế:");
            e.printStackTrace();
        }
    }
}