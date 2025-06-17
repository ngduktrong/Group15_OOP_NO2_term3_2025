package com.group15.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.models.TaiKhoan;

public class TaiKhoanDao {
    private Connection connection;

    public TaiKhoanDao(Connection connection) {
        this.connection = connection;
    }
    

    public List<TaiKhoan> getAll() throws SQLException {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(
                    rs.getString("TenDangNhap"),
                    rs.getString("MatKhau"),
                    rs.getString("LoaiTaiKhoan"),
                    rs.getInt("MaNguoiDung")
                );
                list.add(tk);
            }
        }
        return list;
    }

    public boolean insert(TaiKhoan tk) throws SQLException {
        String sql = "INSERT INTO TaiKhoan (TenDangNhap, MatKhau, LoaiTaiKhoan, MaNguoiDung) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tk.getTenDangNhap());
            stmt.setString(2, tk.getMatKhau());
            stmt.setString(3, tk.getLoaiTaiKhoan());
            stmt.setInt(4, tk.getMaNguoiDung());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean update(TaiKhoan tk) throws SQLException {
        String sql = "UPDATE TaiKhoan SET MatKhau=?, LoaiTaiKhoan=?, MaNguoiDung=? WHERE TenDangNhap=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tk.getMatKhau());
            stmt.setString(2, tk.getLoaiTaiKhoan());
            stmt.setInt(3, tk.getMaNguoiDung());
            stmt.setString(4, tk.getTenDangNhap());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(String tenDangNhap) throws SQLException {
        String sql = "DELETE FROM TaiKhoan WHERE TenDangNhap=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, tenDangNhap);
            return stmt.executeUpdate() > 0;
        }
    }
}