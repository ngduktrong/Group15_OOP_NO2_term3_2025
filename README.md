<h1>Hệ thống quản lý rạp chiếu phim </h1> 
<h2>Giới Thiệu Dự Án </h2>
Dự án này nhằm xây dựng một ứng dụng quản lý rạp chiếu phim, giúp tối ưu hóa quy trình vận hành và đặt vé thông qua việc sử dụng ngôn ngữ Java và thư viện Java SpringBoot. Ứng dụng cung cấp các chức năng thiết yếu như quản lý phim, lịch chiếu, phòng chiếu, đặt vé và khách hàng, hóa đơn , ghế ngồi  từ đó tạo ra một hệ thống trực quan, hiệu quả và dễ sử dụng cho cả nhân viên và người dùng.
<h2>Mục Tiêu </h2>
springBoot-main/                 
├── controller/      # Các Controller xử lý request (KhachHang, Ve, Suatchieu...)
├── service/         # Business logic (xử lý nghiệp vụ)
├── dao/             # Tầng DAO giao tiếp với CSDL
├── models/          # Các Entity (Ve, Suatchieu, KhachHang...)
├── templates/       # Giao diện Thymeleaf
├── static/          # Tài nguyên tĩnh: CSS, JS, hình ảnh
├── ServingWebContentApplication.java  # Class khởi chạy ứng dụng
├── application.properties           # File cấu hình CSDL
└── README.md        # Mô tả dự án

1. Giao diện

    - Giao diện đồ họa bằng Java cho dự án
      
2. Chức năng chính
   + Quản lý Phim : <br>
     Thêm phim mới ( tên , thể loại , ngày công chiếu , mô tả , ... ) <br>
     Cập nhập thông tin phim <br>
     Xóa Phim<br>
     Liệt kê phim<br>
   + Quản lý Phòng Chiếu <br>
       Thêm mới , sửa , xóa phòng chiếu và danh sách ghế .<br>
   + Quản lý Suất  Chiếu <br>
       Quản lý lịch chiếu cho từng phim theo thời gian và phòng<br>
       Thêm suất: Chọn phim, phòng, thời gian<br>
       Cập nhật: sửa thời gian, phòng<br>
   + Quản lý Vé :  Xem trạng thái ghế đã đặt theo suất chiếu, cập nhật/trả vé. <br>
       Tạo hóa đơn khi đặt vé gồm ngày giờ thanh toán , tổng tiền , ...<br>
       Truy vấn danh sách Ve theo SuatChieu<br>
       Hiển thị trạng thái:  paid, pending<br>
       Cập nhật trạng thái vé (nếu có yêu cầu hủy/trả)<br>
   + Quản lý Hóa Đơn  :Xem thông tin hóa đơn đã thanh toán<br>
       Mối hóa đơn có dánh sách vé <br>
         
   + Quản lý Người Dùng : Đối tượng sử dụng
       Khách hàng : Đăng ký , đặt vé , xem lịch chiếu .<br>
       Nhân viên : Tạo Phim , Suất  Chiếu , Phòng chiếu <br>
   - Dữ liệu sẽ được lưu trữ và truy xuất trong Database 
   
<h2> Thành Viên Nhóm </h2>

   - Nguyễn Đức Trọng -	23010594
     
   - Dương Hùng Mạnh  - 23010597

<h2> Readme Giới thiệu Project </h2>
[Link](https://ngduktrong.github.io/Group15_OOP_NO2_term3_2025/)

<h2> Sơ đồ khối </h2>

<h3>UML Class Diagram</h3>


![Sơ đồ class ](https://github.com/ngduktrong/Group15_OOP_NO2_term3_2025/blob/main/img/class%20diagram.png?raw=true)
<h3>UML Squence Diagram </h3>
![Sơ đồ squence](https://github.com/ngduktrong/Group15_OOP_NO2_term3_2025/blob/main/img/Screenshot%202025-05-20%20120942.png?raw=true)
<h3> UML Chức năng Đăng nhập </h3>
![Sơ đồ login](https://github.com/ngduktrong/Group15_OOP_NO2_term3_2025/blob/main/img/ChucNanglogin.png?raw=true)
<h3> UML Chúc năng Quản lý phim : Xem và Thêm </h3>
![Sơ đồ view and create](https://github.com/ngduktrong/Group15_OOP_NO2_term3_2025/blob/main/img/Th%C3%AAm%20and%20Xem.png?raw=true)
<h3>UML Chức năng quản lý phim : Xóa và Sửa </h3>
![Sơ  đồ set and delete ](https://github.com/ngduktrong/Group15_OOP_NO2_term3_2025/blob/main/img/X%C3%B3a%20and%20s%E1%BB%ADa.png?raw=true)

<h3>Chuc năng chính : Thông báo vé đến giờ chiều của khách hàng </h3>
![SƠ dồ ](https://github.com/ngduktrong/Group15_OOP_NO2_term3_2025/blob/main/img/DiagramChucNang.png?raw=true) 
- Mô tả : Hệ thống kiểm tra các vé mà khách đã đặt , mếu có suất chiếu sắp diễn ra thì sẽ hiển thị thông báo 
## 🔍 Phân rã chức năng:

1. `Velist(String maKhachHang)` – Lấy các vé đã đặt từ cơ sở dữ liệu.</br> 
2. `kiemTraVeSapChieu(List<Ve> danhSachVe)` – Kiểm tra các vé có suất chiếu trong vòng 30 phút.
3. `thongBaoVeSapChieu(String maKhachHang)` – Tổng hợp và hiển thị danh sách vé gần đến giờ chiếu.

---

## 👨‍💻 Phân công thành viên:

| Thành viên | Công việc |
|------------|----------|
| **Nguyễn Đức Trọng ** |  
- Viết phương thức `Velist(String maKhachHang)`   

| **Dương Hùng Mạnh ** |  
- Viết hàm `kiemTraVeSapChieu(List<Ve>)` để lọc vé sắp chiếu  
- Viết phương thức `thongBaoVeSapChieu(String maKhachHang)`

  <h3>Thông báo vé có suất chiếu trong vòng 30 phút</h3>
  ![Sơ đồ vé sắp chiếu ](https://github.com/ngduktrong/Group15_OOP_NO2_term3_2025/blob/main/img/Screenshot%202025-07-07%20235334.png?raw=true)





