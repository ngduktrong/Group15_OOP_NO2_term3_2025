package com.example.servingwebcontent.dao;

import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.NguoiDung;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NguoiDungDao {

    public void creat(NguoiDung nd) {
        String sql = "INSERT INTO NguoiDung (MaNguoiDung, HoTen, SoDienThoai, Email, LoaiNguoiDung) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, nd.getMaNguoiDung());
            ps.setString(2, nd.getHoTen());
            ps.setString(3, nd.getSoDienThoai());
            ps.setString(4, nd.getEmail());
            ps.setString(5, nd.getLoaiNguoiDung());
            ps.executeUpdate();
            System.out.println("Thêm người dùng: " + nd.getMaNguoiDung());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public NguoiDung getByID(int id) {
        String sql = "SELECT * FROM NguoiDung WHERE MaNguoiDung = ?";
        try (Connection c = AivenConnection.getConnection();
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(NguoiDung nd) {
        String sql = "UPDATE NguoiDung SET HoTen = ?, SoDienThoai = ?, Email = ?, LoaiNguoiDung = ? WHERE MaNguoiDung = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, nd.getHoTen());
            ps.setString(2, nd.getSoDienThoai());
            ps.setString(3, nd.getEmail());
            ps.setString(4, nd.getLoaiNguoiDung());
            ps.setInt(5, nd.getMaNguoiDung());
            ps.executeUpdate();
            System.out.println("Cập nhật người dùng: " + nd.getMaNguoiDung());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<NguoiDung> getAll() {
        List<NguoiDung> list = new ArrayList<>();
        String sql = "SELECT * FROM NguoiDung";
        try (Connection c = AivenConnection.getConnection();
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delete(int id) {
        String sql = "DELETE FROM NguoiDung WHERE MaNguoiDung = ?";
        try (Connection c = AivenConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Xóa người dùng: " + id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
