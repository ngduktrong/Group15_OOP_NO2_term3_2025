package com.group15;
import com.group15.Review.GenericCRUD;
import com.group15.models.Phim;

public class TestPhimCRUD {
    public static void main(String[] args) {
        GenericCRUD<Phim> phimCRUD = new GenericCRUD<>();

        // Thêm phim
        phimCRUD.add(new Phim("Avengers: Endgame", 1001, 181, "2019-04-26", "USA", "IMAX", "Superhero movie", "Anthony Russo", "endgame.jpg"));
        phimCRUD.add(new Phim("Parasite", 1002, 132, "2019-05-30", "Korea", "2D", "Drama thriller", "Bong Joon-ho", "parasite.jpg"));

        System.out.println("=== Danh sách sau khi thêm   ===");
        phimCRUD.printAll();

        // Sửa phim đầu tiên
        phimCRUD.update(0, new Phim("Avengers: Infinity War", 1001, 149, "2018-04-27", "USA", "IMAX", "Superhero movie", "Anthony Russo", "infinitywar.jpg"));
        System.out.println("=== Danh sach sau khi sau  ===");
        phimCRUD.printAll();

        // Xóa phim thứ hai
        phimCRUD.delete(1);
        System.out.println("=== Danh sach sau khi xoa ===");
        phimCRUD.printAll();
    }
}