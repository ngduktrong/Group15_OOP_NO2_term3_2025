package Javaproject.src.Review;
import Javaproject.src.models.RapPhim;
import java.util.Scanner;

public class RapPhimReview {
   public RapPhim nhapRapPhim() {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhap ma rap: ");
        int maRap = input.nextInt();
        input.nextLine(); 
        System.out.print("Nhap ten rap: ");
        String tenRap = input.nextLine();
        System.out.print("Nhap dia chi: ");
        String diaChi = input.nextLine();
        System.out.print("Nhap so dien thoai: ");
        String soDienThoai = input.nextLine();
        RapPhim rapPhim = new RapPhim(maRap,tenRap,diaChi,soDienThoai);
        rapPhim.HienThiRapPhim();
        
        return rapPhim;
    }
    

}
