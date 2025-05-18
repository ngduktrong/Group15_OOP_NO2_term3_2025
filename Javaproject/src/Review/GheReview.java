package Javaproject.src.Review;
import Javaproject.src.models.Ghe;
import Javaproject.src.models.PhongChieu;
import java.util.Scanner;

public class GheReview {
    public Ghe NhapGhe(PhongChieu PhongChieu){
        Scanner input = new Scanner(System.in);
        System.out.println("Nhap vao ma ghe: ");
        int maGhe = input.nextInt();
        input.nextLine(); 
        System.out.println("Nhap vao so ghe: ");
        String soGhe = input.nextLine();
        System.out.println("Nhap vao loai ghe: ");
        String loaiGhe = input.nextLine();
        
        Ghe ghe = new Ghe(maGhe, PhongChieu, soGhe, loaiGhe);
        
        System.out.println("Thong tin ghe:");
        ghe.HienThiGhe();
        
        return ghe;
    }
}
