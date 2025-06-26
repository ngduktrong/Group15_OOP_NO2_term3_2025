package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.TaiKhoan;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaiKhoanDao {

    public List<TaiKhoan> getAll() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";

        try (Connection conn = AivenConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getString("LoaiTaiKhoan"),
                        rs.getInt("MaNguoiDung")
                );
                list.add(tk);
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi lấy danh sách tài khoản:");
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(TaiKhoan tk) {
        String sql = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau, LoaiTaiKhoan, MaNguoiDung) VALUES (?, ?, ?, ?)";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tk.getTenDangNhap());
            stmt.setString(2, tk.getMatKhau());
            stmt.setString(3, tk.getLoaiTaiKhoanAsString());
            stmt.setInt(4, tk.getMaNguoiDung());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm tài khoản:");
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(TaiKhoan tk) {
        String sql = "UPDATE TaiKhoan SET MatKhau=?, LoaiTaiKhoan=?, MaNguoiDung=? WHERE TenDangNhap=?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tk.getMatKhau());
            stmt.setString(2, tk.getLoaiTaiKhoanAsString());
            stmt.setInt(3, tk.getMaNguoiDung());
            stmt.setString(4, tk.getTenDangNhap());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật tài khoản:");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String tenDangNhap) {
        String sql = "DELETE FROM TaiKhoan WHERE TenDangNhap = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tenDangNhap);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi xóa tài khoản:");
            e.printStackTrace();
        }
        return false;
    }
    public TaiKhoan getByUsername(String tenDangNhap) {
    String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";
    try (Connection conn = AivenConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, tenDangNhap);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new TaiKhoan(
                        rs.getString("TenDangNhap"),
                        rs.getString("MatKhau"),
                        rs.getString("LoaiTaiKhoan"),
                        rs.getInt("MaNguoiDung")
                );
            }
        }

    } catch (Exception e) {
        System.out.println("Lỗi khi lấy tài khoản theo tên đăng nhập:");
        e.printStackTrace();
    }
    return null; // không tìm thấy
    }
    
}
