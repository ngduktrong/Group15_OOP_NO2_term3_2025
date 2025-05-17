package Javaproject.src.models;

public class NhanVien {
    public int MaNhanVien;
    public String Ten;
    public String ChucVu;
    public String CaLam;
    public String SoDienThoai;
    public String Email;

    public NhanVien(int MaNHanVien , String Ten , String ChucVu, String Calam,String SoDienThoai,String Email){
       this.MaNhanVien=MaNhanVien; 
        this.Ten=Ten;
        this.ChucVu=ChucVu;
        this.CaLam=CaLam;
        this.SoDienThoai=SoDienThoai;
        this.Email=Email;
    }
    public void hienthithongtinnhanvien(){
        System.out.println("Ma nhan vien:"+MaNhanVien);
        System.out.println("Ten:"+Ten);
        System.out.println("ChucVu:"+ChucVu);
        System.out.println("Ca lam:"+CaLam);
        System.out.println("So dien thoai:"+SoDienThoai);
        System.out.println("Email:"+Email);
    }
}
