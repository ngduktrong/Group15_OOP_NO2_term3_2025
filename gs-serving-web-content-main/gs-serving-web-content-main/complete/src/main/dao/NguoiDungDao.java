// src/com/group15/dao/NguoiDungDaoNguoiDungDao.java
package com.group15.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group15.DataBase.DataBase;
import com.example.servingwebcontent.models.NguoiDung;
public class NguoiDungDao{
    public void creat(NguoiDung nd){
        String sql = "INSERT INTO NguoiDung(MaNguoiDung, HoTen, SoDienThoai,Email,LoaiNguoiDung) VALUES(?,?,?,?,?)";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, nd.getMaNguoiDung());
            ps.setString(2, nd.getHoTen());
            ps.setString(3, nd.getSoDienThoai());
            ps.setString(4, nd.getEmail());
            ps.setString(5, nd.getLoaiNguoiDung());
            ps.executeUpdate();
            System.out.println("Them nguoi dung  " + nd.getMaNguoiDung());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public NguoiDung getByID ( int id ) {
        String sql = "SELECT * FROM NguoiDung WHERE MaNguoiDung = ?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                NguoiDung p = new NguoiDung();
                p.setMaNguoiDung(rs.getInt("MaNguoiDung"));
                p.setHoTen(rs.getString("HoTen"));
                p.setSoDienThoai(rs.getString("SoDienThoai"));
                p.setEmail(rs.getString("Email"));
                p.setLoaiNguoiDung(rs.getString("LoaiNguoiDung"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(NguoiDung nd){
        String sql = "UPDATE NguoiDung SET MaNguoiDung=?, HoTen=?, SoDienThoai=?, Email=?, LoaiNguoiDung=?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, nd.getMaNguoiDung());
            ps.setString(2, nd.getHoTen());
            ps.setString(3, nd.getSoDienThoai());
            ps.setString(4, nd.getEmail());
            ps.setString(5, nd.getLoaiNguoiDung());
            ps.executeUpdate();
            System.out.println("Cap nhat nguoi dung " + nd.getMaNguoiDung());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<NguoiDung> getAll() {
        List<NguoiDung> list = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDung";
        try (Connection c = DataBase.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                NguoiDung p = new NguoiDung();
                p.setMaNguoiDung(rs.getInt("MaNguoiDung"));
                p.setHoTen(rs.getString("HoTen"));
                p.setSoDienThoai(rs.getString("SoDienThoai"));
                p.setEmail(rs.getString("Email"));
                p.setLoaiNguoiDung(rs.getString("LoaiNguoiDung"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void delete(int id) {
        String sql = "DELETE FROM NguoiDung WHERE MaNguoiDung=?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Xoa nguoi dung " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}