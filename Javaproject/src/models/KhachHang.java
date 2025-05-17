package Javaproject.src.models;

public class KhachHang {
    public int MaKhachHang;
    public String Ten;
    public String SoDienThoai;
    public String Email;


    public KhachHang( int MaKhachHang,String Ten,String SoDienThoai, String Email ){
        this.MaKhachHang =MaKhachHang;
        this.Ten=Ten;
        this.SoDienThoai=SoDienThoai;
        this.Email=Email;
    }
    public void hienthithongtinkhachhang(){
        System.out.println("Ma Khach Hang:" +MaKhachHang);
        System.out.println("Ten:"+Ten);
        System.out.println("So Dien Thoai:"+SoDienThoai);
        System.out.println("Email:"+Email);
    }
}
