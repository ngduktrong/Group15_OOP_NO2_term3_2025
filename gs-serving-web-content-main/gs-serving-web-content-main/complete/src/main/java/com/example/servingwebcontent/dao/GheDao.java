// src/com/group15/dao/GheDAO.java
package com.example.servingwebcontent.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.servingwebcontent.database.DataBase;
import com.example.servingwebcontent.models.Ghe;

public class GheDao {
    public void create(Ghe g) {
        String sql = "INSERT INTO Ghe(soGhe, maPhong) VALUES(?,?)";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, g.getSoGhe());
            ps.setString(2, g.getMaPhong());
            ps.executeUpdate();
            System.out.println("Thêm ghế số " + g.getSoGhe());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ghe getById(int soGhe, String maPhong) {
        String sql = "SELECT * FROM Ghe WHERE soGhe=? AND maPhong=?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, soGhe);
            ps.setString(2, maPhong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ghe g = new Ghe();
                g.setSoGhe(rs.getInt("soGhe"));
                g.setMaPhong(rs.getString("maPhong"));
                return g;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Ghe> getAll() {
        List<Ghe> list = new ArrayList<>();
        String sql = "SELECT * FROM Ghe";
        try (Connection c = DataBase.getConnection();
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Ghe g = new Ghe();
                g.setSoGhe(rs.getInt("soGhe"));
                g.setMaPhong(rs.getString("maPhong"));
                list.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Ghe g) {
        String sql = "UPDATE Ghe SET maPhong=? WHERE soGhe=?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, g.getMaPhong());
            ps.setInt(2, g.getSoGhe());
            ps.executeUpdate();
            System.out.println("Cập nhật ghế " + g.getSoGhe());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int soGhe) {
        String sql = "DELETE FROM Ghe WHERE soGhe=?";
        try (Connection c = DataBase.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, soGhe);
            ps.executeUpdate();
            System.out.println("Xoá ghế " + soGhe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
