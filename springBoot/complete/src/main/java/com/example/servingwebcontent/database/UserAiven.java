package com.example.servingwebcontent.database;

import com.example.servingwebcontent.models.TaiKhoan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAiven {
    private static final Logger logger = LoggerFactory.getLogger(UserAiven.class);

    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT 1 FROM TaiKhoan WHERE TenDangNhap = ? AND MatKhau = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
            
        } catch (SQLException e) {
            logger.error("Lỗi xác thực người dùng '{}': {}", username, e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("Lỗi hệ thống khi xác thực: {}", e.getMessage());
            return false;
        }
    }

    public boolean registerUser(String username, String password) {
        if (isUserExists(username)) {
            logger.warn("Tên đăng nhập '{}' đã tồn tại", username);
            return false;
        }

        String sql = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau, LoaiTaiKhoan, MaNguoiDung) VALUES (?, ?, ?, ?)";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, "user");
            pst.setInt(4, 0);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Đăng ký thành công người dùng: {}", username);
                return true;
            }
            
        } catch (SQLException e) {
            logger.error("Lỗi SQL khi đăng ký người dùng '{}': {}", username, e.getMessage());
        } catch (Exception e) {
            logger.error("Lỗi hệ thống khi đăng ký: {}", e.getMessage());
        }
        return false;
    }

    public boolean isUserExists(String username) {
        String sql = "SELECT 1 FROM TaiKhoan WHERE TenDangNhap = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
            
        } catch (SQLException e) {
            logger.error("Lỗi SQL khi kiểm tra người dùng '{}': {}", username, e.getMessage());
            return true; // Giả sử tồn tại để tránh tạo trùng
        } catch (Exception e) {
            logger.error("Lỗi hệ thống khi kiểm tra người dùng: {}", e.getMessage());
            return true;
        }
    }

    public TaiKhoan getUserByUsername(String username) {
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
            
        } catch (SQLException e) {
            logger.error("Lỗi SQL khi lấy thông tin người dùng '{}': {}", username, e.getMessage());
        } catch (Exception e) {
            logger.error("Lỗi hệ thống khi lấy thông tin người dùng: {}", e.getMessage());
        }
        return null;
    }

    public List<TaiKhoan> getAllUsers() {
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
            
        } catch (SQLException e) {
            logger.error("Lỗi SQL khi lấy danh sách người dùng: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("Lỗi hệ thống khi lấy danh sách người dùng: {}", e.getMessage());
        }
        return list;
    }

    public boolean updateUser(TaiKhoan tk) {
        String sql = "UPDATE TaiKhoan SET MatKhau = ?, LoaiTaiKhoan = ?, MaNguoiDung = ? WHERE TenDangNhap = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, tk.getMatKhau());
            pst.setString(2, tk.getLoaiTaiKhoanAsString());
            pst.setInt(3, tk.getMaNguoiDung());
            pst.setString(4, tk.getTenDangNhap());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Cập nhật thành công tài khoản: {}", tk.getTenDangNhap());
                return true;
            }
            
        } catch (SQLException e) {
            logger.error("Lỗi SQL khi cập nhật người dùng '{}': {}", tk.getTenDangNhap(), e.getMessage());
        } catch (Exception e) {
            logger.error("Lỗi hệ thống khi cập nhật người dùng: {}", e.getMessage());
        }
        return false;
    }

    public boolean deleteUser(String username) {
        String sql = "DELETE FROM TaiKhoan WHERE TenDangNhap = ?";
        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("Đã xóa tài khoản: {}", username);
                return true;
            }
            
        } catch (SQLException e) {
            logger.error("Lỗi SQL khi xóa người dùng '{}': {}", username, e.getMessage());
        } catch (Exception e) {
            logger.error("Lỗi hệ thống khi xóa người dùng: {}", e.getMessage());
        }
        return false;
    }
}