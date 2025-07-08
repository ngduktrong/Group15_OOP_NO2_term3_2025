package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.NhanVien;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NhanVienDao {

    public boolean create(NhanVien nv) {
    String sql = "INSERT INTO NhanVien (MaNguoiDung, ChucVu, Luong, VaiTro) VALUES (?, ?, ?, ?)";
    try (Connection c = AivenConnection.getConnection();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, nv.getMaNguoiDung());
        ps.setString(2, nv.getChucVu());
        ps.setDouble(3, nv.getLuong());
        ps.setString(4, nv.getVaiTro().name());

        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println(" Thêm nhân viên: " + nv.getMaNguoiDung());
            return true;
        }
    } catch (SQLException | ClassNotFoundException e) {
        System.out.println(" Lỗi khi thêm nhân viên:");
        e.printStackTrace();
    }
    return false;
}


    public void update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET ChucVu = ?, Luong = ?, VaiTro = ? WHERE MaNguoiDung = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nv.getChucVu());
            ps.setDouble(2, nv.getLuong());
            ps.setString(3, nv.getVaiTro().name());
            ps.setInt(4, nv.getMaNguoiDung());
            ps.executeUpdate();

            System.out.println("Cập nhật nhân viên: " + nv.getMaNguoiDung());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public NhanVien getById(int id) {
        String sql = "SELECT * FROM NhanVien WHERE MaNguoiDung = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new NhanVien(
                        rs.getInt("MaNguoiDung"),
                        NhanVien.VaiTro.valueOf(rs.getString("VaiTro")),
                        rs.getString("ChucVu"),
                        rs.getDouble("Luong")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (Connection c = AivenConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt("MaNguoiDung"),
                        NhanVien.VaiTro.valueOf(rs.getString("VaiTro")),
                        rs.getString("ChucVu"),
                        rs.getDouble("Luong")
                );
                list.add(nv);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(int id) {
        String sql = "DELETE FROM NhanVien WHERE MaNguoiDung = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("🗑 Xóa nhân viên: " + id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
