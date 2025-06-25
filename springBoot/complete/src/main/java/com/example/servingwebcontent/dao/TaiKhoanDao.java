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
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

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

    public TaiKhoan getByUsername(String username) {
        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap = ?";

        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
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

        return null;
    }

    public boolean existsByUsername(String username) {
        String sql = "SELECT 1 FROM TaiKhoan WHERE TenDangNhap = ?";

        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean create(TaiKhoan tk) {
        if (existsByUsername(tk.getTenDangNhap())) {
            return false;
        }

        String sql = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau, LoaiTaiKhoan, MaNguoiDung) VALUES (?, ?, ?, ?)";

        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, tk.getTenDangNhap());
            pst.setString(2, tk.getMatKhau());
            pst.setString(3, tk.getLoaiTaiKhoan().toString());
            pst.setInt(4, tk.getMaNguoiDung());

            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm tài khoản:");
            e.printStackTrace();
        }

        return false;
    }

    public void update(TaiKhoan tk) {
        String sql = "UPDATE TaiKhoan SET MatKhau = ?, LoaiTaiKhoan = ?, MaNguoiDung = ? WHERE TenDangNhap = ?";

        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, tk.getMatKhau());
            pst.setString(2, tk.getLoaiTaiKhoan().toString());
            pst.setInt(3, tk.getMaNguoiDung());
            pst.setString(4, tk.getTenDangNhap());

            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật tài khoản:");
            e.printStackTrace();
        }
    }

    public void delete(String username) {
        String sql = "DELETE FROM TaiKhoan WHERE TenDangNhap = ?";

        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("Lỗi khi xóa tài khoản:");
            e.printStackTrace();
        }
    }
}
