package com.group15.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group15.DataBase.DataBase;
import com.example.servingwebcontent.models.NhanVien;
public class NhanVienDao {
    public void create(NhanVien nv){
        String sql = "INSERT INTO NhanVien(MaNguoiDung, ChucVu, Luong, VaiTro,) VALUES(?,?,?,?)";
        try ( Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, nv.getMaNguoiDung());
            ps.setString(2, nv.getChucVu());
            ps.setDouble(3, nv.getLuong());
            ps.setString(4, nv.getVaiTro().name());// Trả về chuỗi tương ứng ứng với enum vai trò 
            ps.executeUpdate();
            System.out.println("Them nhan vien " + nv.getMaNguoiDung());
        } catch (SQLException e) {
            e.printStackTrace();
            }
    }
    public void update(NhanVien nv) {
        String sql = "UPDATE NhanVien SET ChucVu=?, Luong=?, VaiTro=? WHERE MaNguoiDung=?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, nv.getChucVu());
            ps.setDouble(2, nv.getLuong());
            ps.setString(3, nv.getVaiTro().name());
            ps.setInt(4, nv.getMaNguoiDung());
            ps.executeUpdate();
            System.out.println("Cap nhat nhan vien " + nv.getMaNguoiDung());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public NhanVien getById(int id) {
        String sql = "SELECT * FROM NhanVien WHERE MaNguoiDung = ?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NhanVien nv = new NhanVien(
                    rs.getInt("MaNguoiDung"),
                    NhanVien.VaiTro.valueOf(rs.getString("VaiTro")),
                    rs.getString("ChucVu"),
                    rs.getDouble("Luong")
                );
                return nv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        try (Connection c = DataBase.getConnection();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void delete(int id) {
        String sql = "DELETE FROM NhanVien WHERE MaNguoiDung = ?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Xoa nhan vien " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}