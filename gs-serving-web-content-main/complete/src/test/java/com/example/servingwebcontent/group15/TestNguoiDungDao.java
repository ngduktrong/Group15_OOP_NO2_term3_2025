package com.example.servingwebcontent.group15;
import com.group15.dao.NguoiDungDao;
import com.group15.models.NguoiDung;

public class TestNguoiDungDao {
    public static void main(String[] args) {
        NguoiDungDao nguoiDungDao = new NguoiDungDao();

        for (NguoiDung nd : nguoiDungDao.getAll()) {
            System.out.println(nd);
        }
    }
}