package com.example.servingwebcontent.database;

import java.util.Locale;
import java.sql.*;

public class AivenConnection {

    
        // ...existing code...
    private static final String URL = "jdbc:mysql://ngduktrong-oop-duktrong-oop.b.aivencloud.com:26340/quanlyrcp?ssl-mode=REQUIRED";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_sZ26Imppg25oMKu9vG1";
    // ...existing code...
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    public void testConnection() {
        Connection conn = null;

        try {
            // Nạp JDBC driver cho MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Kết nối tới CSDL
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Kết nối thành công đến cơ sở dữ liệu Aiven MySQL!");

            // Truy vấn dữ liệu từ bảng Phim
            Statement statement = conn.createStatement();
            String sql = "SELECT MaPhim, TenPhim, ThoiLuong, NgayKhoiChieu, NuocSanXuat, DinhDang, MoTa, DaoDien, DuongDanPoster FROM Phim LIMIT 5";
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("🎬 Danh sách phim:");
            while (rs.next()) {
                int maPhim = rs.getInt("MaPhim");
                String tenPhim = rs.getString("TenPhim");
                int thoiLuong = rs.getInt("ThoiLuong");
                Date ngayKhoiChieu = rs.getDate("NgayKhoiChieu");
                String nuocSanXuat = rs.getString("NuocSanXuat");
                String dinhDang = rs.getString("DinhDang");
                String moTa = rs.getString("MoTa");
                String daoDien = rs.getString("DaoDien");
                String duongDanPoster = rs.getString("DuongDanPoster");

                System.out.println("→ " + maPhim + ": " + tenPhim);
                System.out.println("   Thời lượng: " + thoiLuong + " phút | Khởi chiếu: " + ngayKhoiChieu);
                System.out.println("   Quốc gia: " + nuocSanXuat + " | Định dạng: " + dinhDang);
                System.out.println("   Đạo diễn: " + daoDien);
                System.out.println("   Mô tả: " + moTa);
                System.out.println("   Poster: " + duongDanPoster);
                System.out.println("--------------------------------------------------");
        }

            // Đóng kết nối
            rs.close();
            statement.close();

            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Lỗi: Không tìm thấy MySQL JDBC Driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Lỗi khi kết nối hoặc truy vấn cơ sở dữ liệu:");
            e.printStackTrace();
        }
    }
}