package com.group15.Review;
import java.lang.reflect.Array;
import java.util.ArrayList;
import com.group15.models.NguoiDung;
public class NguoiDungReview {
    ArrayList<NguoiDung> ND = new ArrayList<NguoiDung>();
    public ArrayList<NguoiDung> addNguoiDung(NguoiDung NguoiDung){
        ND.add(NguoiDung);
        return ND;
    }
    public ArrayList<NguoiDung> getNguoiDungList(int MaNguoiDung, String HoTen, String SoDienThoai, String Email, String LoaiNguoiDung) {
        for (int i = 0; i < ND.size(); i++) {
            if (ND.get(i).getMaNguoiDung() == MaNguoiDung) {
                ND.get(i).setHoTen(HoTen);
                ND.get(i).setSoDienThoai(SoDienThoai);
                ND.get(i).setEmail(Email);
                ND.get(i).setLoaiNguoiDung(LoaiNguoiDung);
                return ND;
            }
        }
        return ND;
    }

}
