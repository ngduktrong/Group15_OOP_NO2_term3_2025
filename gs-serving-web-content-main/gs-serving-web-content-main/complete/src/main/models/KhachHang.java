package com.example.servingwebcontent.models;
public class KhachHang extends NguoiDung {
    private int MaNguoiDung;
    private int DiemTichLuy;

    public KhachHang() {}
    public KhachHang(int MaNguoiDung, int DiemTichLuy) {
        this.MaNguoiDung = MaNguoiDung;
        this.DiemTichLuy = DiemTichLuy;
    }
    @Override
    public int getMaNguoiDung() {
        return MaNguoiDung;
    }
    @Override
    public void setMaNguoiDung(int MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }
    public int getDiemTichLuy() {
        return DiemTichLuy;
    }
    public void setDiemTichLuy(int DiemTichLuy) {
        this.DiemTichLuy = DiemTichLuy;
    }
    @Override
    public String toString() {
        return "KhachHang{" +
                "MaNguoiDung=" + MaNguoiDung +
                ", DiemTichLuy=" + DiemTichLuy +
                '}';
    }
}
