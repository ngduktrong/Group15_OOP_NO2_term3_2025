package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.KhachHang;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KhachHangDao {

    public void create(KhachHang kh) {
    String sql = "INSERT INTO KhachHang (MaNguoiDung, DiemTichLuy) VALUES (?, ?)";

    try (Connection c = AivenConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, kh.getMaNguoiDung());
        ps.setInt(2, kh.getDiemTichLuy());

        ps.executeUpdate();
        System.out.println("✅ Thêm khách hàng thành công: " + kh.getMaNguoiDung());

    } catch (SQLIntegrityConstraintViolationException ex) {
        System.err.println("❌ Lỗi ràng buộc (trùng khóa chính hoặc khóa ngoại): " + ex.getMessage());
        throw new RuntimeException("Ràng buộc bị vi phạm khi thêm khách hàng.", ex);

    } catch (SQLException | ClassNotFoundException e) {
        System.err.println("❌ Lỗi SQL khác khi thêm khách hàng: " + e.getMessage());
        throw new RuntimeException("Lỗi không xác định khi thêm khách hàng.", e);
    }
}


    public KhachHang getByID(int id) {
        String sql = "SELECT * FROM KhachHang WHERE MaNguoiDung = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaNguoiDung(rs.getInt("MaNguoiDung"));
                kh.setDiemTichLuy(rs.getInt("DiemTichLuy"));
                return kh;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(KhachHang kh) {
        String sql = "UPDATE KhachHang SET DiemTichLuy = ? WHERE MaNguoiDung = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, kh.getDiemTichLuy());
            ps.setInt(2, kh.getMaNguoiDung());
            ps.executeUpdate();
            System.out.println("Cập nhật khách hàng: " + kh.getMaNguoiDung());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try (Connection c = AivenConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaNguoiDung(rs.getInt("MaNguoiDung"));
                kh.setDiemTichLuy(rs.getInt("DiemTichLuy"));
                list.add(kh);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(int id) {
        String sql = "DELETE FROM KhachHang WHERE MaNguoiDung = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Xóa khách hàng: " + id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
