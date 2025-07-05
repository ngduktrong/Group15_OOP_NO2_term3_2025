package com.example.servingwebcontent.service;

import com.example.servingwebcontent.dao.VeDao;
import com.example.servingwebcontent.database.AivenConnection;
import com.example.servingwebcontent.models.Ve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class Ve30Service {

    @Autowired
    private VeDao veDAO;

    private void printHeader(String title) {
        System.out.println("\n================== 📌 " + title + " ==================\n");
    }

    private void printFooter(String message) {
        System.out.println("\n================== ✅ " + message + " ==================\n");
    }

    /**
     * ✅ Lấy tất cả vé của một khách hàng
     */
    public List<Ve> getVeListByKhachHang(int maKhachHang) {
        printHeader("LẤY VÉ CỦA KHÁCH HÀNG MÃ " + maKhachHang);
        List<Ve> list = veDAO.getVeByMaKhachHang(maKhachHang);
        System.out.println("📦 Số vé tìm được: " + list.size());
        printFooter("HOÀN TẤT TRUY VẤN VÉ KHÁCH HÀNG");
        return list;
    }

    /**
     * ✅ Lấy tất cả vé trong hệ thống
     */
    public List<Ve> getAllVe() {
        printHeader("LẤY TOÀN BỘ VÉ TRONG HỆ THỐNG");
        List<Ve> list = veDAO.getAll();
        System.out.println("📦 Tổng số vé trong hệ thống: " + list.size());
        printFooter("HOÀN TẤT TRUY VẤN TOÀN BỘ VÉ");
        return list;
    }

    /**
     * ✅ Lọc vé sắp chiếu trong 30 phút tới
     */
    public List<Ve> locVeSapChieuTrong30Phut(List<Ve> danhSachVe) {
        printHeader("BẮT ĐẦU LỌC VÉ SẮP CHIẾU TRONG 30 PHÚT");

        List<Ve> ketQua = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in30Min = now.plusMinutes(30);

        System.out.println("⏱️ Thời gian hiện tại: " + now);
        System.out.println("⏰ Thời gian giới hạn: " + in30Min);
        System.out.println("🔎 Đang kiểm tra từng vé...");

        String sql = "SELECT v.*, sc.NgayGioChieu FROM Ve v " +
                     "JOIN SuatChieu sc ON v.MaSuatChieu = sc.MaSuatChieu " +
                     "WHERE v.TrangThai = 'paid'";

        try (Connection conn = AivenConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Timestamp gioChieuDB = rs.getTimestamp("NgayGioChieu");
                int maVe = rs.getInt("MaVe");

                if (gioChieuDB != null) {
                    LocalDateTime gioChieu = gioChieuDB.toLocalDateTime();
                    System.out.printf("➡️  Vé %-4d | Giờ chiếu: %-20s | ", maVe, gioChieu);

                    if (gioChieu.isAfter(now) && gioChieu.isBefore(in30Min)) {
                        System.out.println("✅ HỢP LỆ");
                        Ve ve = new Ve(
                            maVe,
                            rs.getInt("MaSuatChieu"),
                            rs.getInt("MaPhong"),
                            rs.getString("SoGhe"),
                            rs.getInt("MaHoaDon"),
                            rs.getDouble("GiaVe"),
                            rs.getString("TrangThai"),
                            rs.getString("NgayDat")
                        );
                        ketQua.add(ve);
                    } else {
                        System.out.println("⛔ KHÔNG HỢP LỆ");
                    }
                } else {
                    System.out.println("⚠️ Không có giờ chiếu.");
                }
            }

        } catch (Exception e) {
            System.err.println("❌ Lỗi trong quá trình lọc vé:");
            e.printStackTrace();
        }

        System.out.println("\n📋 Số vé hợp lệ (sắp chiếu): " + ketQua.size());
        printFooter("HOÀN TẤT LỌC VÉ TRONG 30 PHÚT");
        return ketQua;
    }

    /**
     * ✅ Lấy danh sách mã khách hàng có vé sắp chiếu
     */
    public List<Integer> getDanhSachMaKhachHangSapChieu() {
        printHeader("LẤY DANH SÁCH MÃ KHÁCH HÀNG SẮP CHIẾU");
        List<Integer> list = veDAO.getMaKhachHangCuaVeSapChieuTrong30Phut();
        System.out.println("👥 Số khách hàng có vé sắp chiếu: " + list.size());
        printFooter("HOÀN TẤT LẤY MÃ KHÁCH HÀNG");
        return list;
    }

    /**
     * ✅ Lấy danh sách vé sắp chiếu toàn hệ thống
     */
    public List<Ve> getDanhSachVeSapChieuToanBo() {
        printHeader("LỌC TOÀN BỘ VÉ SẮP CHIẾU TOÀN HỆ THỐNG");
        return locVeSapChieuTrong30Phut(getAllVe());
    }
}
