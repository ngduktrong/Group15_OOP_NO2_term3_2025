package com.group15.Review;
import java.util.ArrayList;
import com.group15.models.Ghe;

public class GheReview {
    private ArrayList<Ghe> gheList = new ArrayList<>();

    // Thêm ghế
    public void addGhe(Ghe ghe) {
        gheList.add(ghe);
    }

    // Lấy danh sách ghế
    public ArrayList<Ghe> getAll() {
        return gheList;
    }

    // Xóa ghế theo index
    public boolean deleteGhe(int index) {
        if (index >= 0 && index < gheList.size()) {
            gheList.remove(index);
            return true;
        }
        return false;
    }

    // In danh sách ghế
    public void printAll() {
        if (gheList.isEmpty()) {
            System.out.println("Danh sách ghế trống.");
            return;
        }
        for (Ghe ghe : gheList) {
            System.out.println(ghe);
        }
    }
}