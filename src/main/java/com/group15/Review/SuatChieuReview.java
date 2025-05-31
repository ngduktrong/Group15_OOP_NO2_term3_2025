package com.group15.Review;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.group15.models.SuatChieu;

public class SuatChieuReview {

    private Connection conn;

    public SuatChieuReview(Connection conn) {
        this.conn = conn;
    }

    public List<SuatChieu> laySuatChieuTheoNgay(String ngay) throws SQLException {
        List<SuatChieu> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM SuatChieu WHERE DATE(NgayGioChieu) = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, ngay);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            SuatChieu sc = new SuatChieu();
            sc.setMaSuatChieu(rs.getInt("MaSuatChieu"));
            sc.setMaPhim(rs.getInt("MaPhim"));
            sc.setMaPhong(rs.getInt("MaPhong"));
            sc.setNgayGioChieu(rs.getString("NgayGioChieu"));
            danhSach.add(sc);
        }

        return danhSach;
    }
}
