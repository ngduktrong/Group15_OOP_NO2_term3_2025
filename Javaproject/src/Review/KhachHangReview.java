package Javaproject.src.Review;
import Javaproject.src.models.KhachHang;
import java.util.Scanner;

public class KhachHangReview {
    public void NhapKhachHang(){
        Scanner input =new Scanner(System.in);
        System.out.println("Nhap vao ma khach hang:");
        int MaKhachHang =input.nextInt();
        input.nextLine();
        System.out.println("Nhap vao ten khach hang:");
        String Ten =input.nextLine();
        System.out.println("Nhap vao so dien thoai khach hang:");
        String SoDienThoai =input.nextLine();
        System.out.println("nhap vap email khach hang:");
        String Email =input.nextLine();
        KhachHang KH = new KhachHang(MaKhachHang,Ten,SoDienThoai,Email);
        System.out.println("thong tin khach hang:");
        KH.hienthithongtinkhachhang(); 
        
        input.close();
    }
}