package com.group15.Review;
import com.group15.models.Ghe;
import com.group15.models.PhongChieu;
import java.util.Scanner;
public class GheReview {
    public Ghe NhapGhe(PhongChieu PhongChieu){
        Scanner input =new Scanner(System.in);
        System.out.println("Nhap so ghe: ");
        int SoGhe = input.nextInt();
        input.nextLine();
        Ghe GH = new Ghe(SoGhe , PhongChieu);
        
        GH.HienThiGhe();
        return GH;
    }
    
}