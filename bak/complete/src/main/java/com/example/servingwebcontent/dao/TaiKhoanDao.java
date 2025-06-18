package com.group15.dao;

import com.example.servingwebcontent.models.TaiKhoan;
import com.group15.DataBase.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDao {

    public List<TaiKhoan> getAll() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            connection = DataBase.getConnection();
            stmt = connection.prepareStatement(sql);
            rs = stmt.executeQuery();
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
            System.out.println(" Lỗi khi lấy danh sách tài khoản:");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public boolean insert(TaiKhoan tk) {
        String sql = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau, LoaiTaiKhoan, MaNguoiDung) VALUES (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DataBase.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, tk.getTenDangNhap());
            stmt.setString(2, tk.getMatKhau());
            stmt.setString(3, tk.getLoaiTaiKhoan());
            stmt.setInt(4, tk.getMaNguoiDung());
            int rows = stmt.executeUpdate();
            System.out.println(" Đã thêm tài khoản: " + tk.getTenDangNhap());
            return rows > 0;
        } catch (Exception e) {
            System.out.println(" Lỗi khi thêm tài khoản:");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(TaiKhoan tk) {
        String sql = "UPDATE TaiKhoan SET MatKhau=?, LoaiTaiKhoan=?, MaNguoiDung=? WHERE TenDangNhap=?";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DataBase.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, tk.getMatKhau());
            stmt.setString(2, tk.getLoaiTaiKhoan());
            stmt.setInt(3, tk.getMaNguoiDung());
            stmt.setString(4, tk.getTenDangNhap());
            int rows = stmt.executeUpdate();
            System.out.println(" Đã cập nhật tài khoản: " + tk.getTenDangNhap());
            return rows > 0;
        } catch (Exception e) {
            System.out.println(" Lỗi khi cập nhật tài khoản:");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    public boolean delete(String tenDangNhap) {
        String sql = "DELETE FROM TaiKhoan WHERE TenDangNhap=?";
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DataBase.getConnection();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, tenDangNhap);
            int rows = stmt.executeUpdate();
            System.out.println(" Đã xóa tài khoản: " + tenDangNhap);
            return rows > 0;
        } catch (Exception e) {
            System.out.println(" Lỗi khi xóa tài khoản:");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
