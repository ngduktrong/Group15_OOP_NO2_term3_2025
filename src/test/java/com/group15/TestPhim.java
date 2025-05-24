import com.group15.models.Phim;
import com.group15.Review.PhimReview;

import java.util.ArrayList;
import java.util.Scanner;

public class TestPhim {
    ArrayList<Phim> phimList = new ArrayList<>();
    public ArrayList<Phim> addSamplePhim() {
        Phim p1 = new Phim("Avengers: Endgame", 1001, 181, "2019-04-26", "USA", "IMAX", "Superhero movie", "Anthony Russo", "endgame.jpg");
        Phim p2 = new Phim("Parasite", 1002, 132, "2019-05-30", "Korea", "2D", "Drama thriller", "Bong Joon-ho", "parasite.jpg");
        Phim p3 = new Phim("Inception", 1003, 148, "2010-07-16", "USA", "IMAX", "Sci-fi thriller", "Christopher Nolan", "inception.jpg");

        phimList.add(p1);
        phimList.add(p2);
        phimList.add(p3);

        return phimList;
    }
    public void testEditDelete() {
        Scanner scanner = new Scanner(System.in);
        PhimReview pr = new PhimReview();
        for (Phim p : addSamplePhim()) {
            pr.addPhim(p);
        }
        System.out.println("=== DANH SÁCH PHIM BAN ĐẦU ===");
        pr.printPhimList();
        System.out.println("\n--- SỬA PHIM ---");
        System.out.print("Nhập mã phim muốn sửa: ");
        int maPhim = scanner.nextInt();
        scanner.nextLine();
        Phim phimOld = pr.getPhimByMa(maPhim); // Sử dụng hàm mới
        if (phimOld == null) {
            System.out.println(" Không tìm thấy phim.");
        } else {
            System.out.print("Nhập tên phim mới: ");
            String tenMoi = scanner.nextLine();
            System.out.print("Thời lượng mới: ");
            int thoiLuong = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ngày khởi chiếu mới: ");
            String ngayKC = scanner.nextLine();
            System.out.print("Nước sản xuất mới: ");
            String nuocSX = scanner.nextLine();
            System.out.print("Định dạng mới: ");
            String dinhDang = scanner.nextLine();
            System.out.print("Mô tả mới: ");
            String moTa = scanner.nextLine();
            System.out.print("Đạo diễn mới: ");
            String daoDien = scanner.nextLine();
            System.out.print("Poster mới: ");
            String poster = scanner.nextLine();
            Phim phimMoi = new Phim(tenMoi, maPhim, thoiLuong, ngayKC, nuocSX, dinhDang, moTa, daoDien, poster);
            pr.deletePhimById(maPhim); 
            pr.addPhim(phimMoi);        
        }
        System.out.println("\nDANH SÁCH SAU KHI SỬA ");
        pr.printPhimList();
        System.out.println("\n XOÁ PHIM ");
        System.out.print("Nhập mã phim cần xoá: ");
        int maPhimXoa = scanner.nextInt();

        pr.deletePhimById(maPhimXoa);

        System.out.println("\nDANH SÁCH SAU KHI XOÁ ");
        pr.printPhimList();
    }
    public static void main(String[] args) {
        TestPhim test = new TestPhim();
        test.testEditDelete();
    }
}