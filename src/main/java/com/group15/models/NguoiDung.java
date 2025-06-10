package com.group15.models ;
public class NguoiDung {

    public enum LoaiNguoiDung {
        KhachHang , NhanVien
    }
    private int MaNguoiDung;
    private String HoTen;
    private String SoDienThoai;
    private String Email;
    private String LoaiNguoiDung;
    public NguoiDung() {
    }
    /**
     * Constructor for NguoiDung class.
     *
     * @param MaNguoiDung   Unique identifier for the user.
     * @param HoTen         Full name of the user.
     * @param SoDienThoai   Phone number of the user.
     * @param Email         Email address of the user.
     * @param LoaiNguoiDung Type of user (e.g., KhachHang, NhanVien).
     */

    public NguoiDung( int MaNguoiDung , String HoTen, String SoDienThoai, String Email, String LoaiNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
        this.HoTen = HoTen;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
        this.LoaiNguoiDung = LoaiNguoiDung ;
    }

    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }
    public String getLoaiNguoiDung() {
        return LoaiNguoiDung;
    }
    public void setLoaiNguoiDung(String LoaiNguoiDung) {
        this.LoaiNguoiDung = LoaiNguoiDung;
    }

    public void setMaNguoiDung(int MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }
    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }
    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    @Override
    public String toString() {
        return "NguoiDung{" +
                "MaNguoiDung=" + MaNguoiDung +
                ", Ten='" + HoTen + '\'' +
                ", SoDienThoai='" + SoDienThoai + '\'' +
                ", Email='" + Email + '\'' +
                ", MatKhau='" + LoaiNguoiDung + '\'' +
                '}';
    }
}

