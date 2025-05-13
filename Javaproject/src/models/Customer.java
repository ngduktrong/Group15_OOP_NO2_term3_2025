package Javaproject.src.models;
public class Customer{
    int makhachhang;

    String ten;

    String  sodienthoai;

    String email;
    Customer(int makhachhang ,String ten, String sodienthoai ,String email){
        this.makhachhang=makhachhang;
        this.ten=ten;
        this.sodienthoai=sodienthoai;
        this.email=email;
    }
    public static void main(String[] args){
        Customer KH= new Customer(123,"nguyen van hoang","0123456789","hoang@gmai.com");
        System.out.println(KH.makhachhang);
        System.out.println(KH.ten);
        System.out.println(KH.sodienthoai);
        System.out.println(KH.email);

    }
}