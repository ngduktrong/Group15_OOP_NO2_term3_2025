package Javaproject.src.Review;
import Javaproject.src.models.PhongChieu;
import Javaproject.src.models.RapPhim;
import java.util.Scanner;

public class PhongChieuReview {
    public void NhapPhongChieu(RapPhim rapPhim){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap vao ma phong: ");
        int maPhong = input.nextInt();
        input.nextLine(); 
        System.out.println("Nhap vao ten phong: ");
        String tenPhong = input.nextLine();
        System.out.println("Nhap vao so luong ghe: ");
        int soLuongGhe = input.nextInt();
        input.nextLine(); 
        System.out.println("Nhap vao loai phong: ");
        String loaiPhong = input.nextLine();
        
        PhongChieu phongChieu = new PhongChieu(maPhong, tenPhong, rapPhim, soLuongGhe, loaiPhong);
        
        System.out.println("Thong tin phong chieu:");
        phongChieu.HienThiPhongChieu();
        
        
    }
    
}
