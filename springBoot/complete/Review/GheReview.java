package com.group15.Review;
import java.util.ArrayList;
import com.group15.models.Ghe;

public class GheReview {
    private ArrayList<Ghe> gheList = new ArrayList<>();

   
    public void addGhe(Ghe ghe) {
        gheList.add(ghe);
    }

 
    public ArrayList<Ghe> getAll() {
        return gheList;
    }

   
    public boolean deleteGhe(int index) {
        if (index >= 0 && index < gheList.size()) {
            gheList.remove(index);
            return true;
        }
        return false;
    }

    
    public void printAll() {
        if (gheList.isEmpty()) {
            System.out.println("Danh sach ghe trong.");
            return;
        }
        for (Ghe ghe : gheList) {
            System.out.println(ghe);
        }
    }
}