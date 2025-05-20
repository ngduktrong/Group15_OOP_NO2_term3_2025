<h1>Hệ thống quản lý rạp chiếu phim </h1> 
<h2>Giới Thiệu Dự Án </h2>
Dự án này nhằm xây dựng một ứng dụng quản lý rạp chiếu phim, giúp tối ưu hóa quy trình vận hành và đặt vé thông qua việc sử dụng ngôn ngữ Java và thư viện JavaFX. Ứng dụng cung cấp các chức năng thiết yếu như quản lý phim, lịch chiếu, phòng chiếu, đặt vé và khách hàng, hóa đơn , ghế ngồi  từ đó tạo ra một hệ thống trực quan, hiệu quả và dễ sử dụng cho cả nhân viên và người dùng.
<h2>Mục Tiêu </h2>
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
       Tạo hóa đơn khi đặt vé gồm ngyaf giờ thanh toán , tổng tiền , ...<br>
       Truy vấn danh sách Ve theo SuatChieu<br>
       Hiển thị trạng thái: available, booked, paid, cancelled, pending<br>
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

![Sơ đồ class ](https://github.com/ngduktrong/Group15_OOP_NO2_term3_2025/blob/main/%E1%BA%A2nh%20ch%E1%BB%A5p%20m%C3%A0n%20h%C3%ACnh%202025-05-20%20114859.png?raw=true)
<h3>UML Squence Diagram </h3>
![Sơ đồ squence](https://raw.githubusercontent.com/ngduktrong/Group15_OOP_NO2_term3_2025/3331520bc93b3cfbefb48066d6aa6b5243f8cc87/Screenshot%202025-05-20%20120942.png)
<h3> UML Chức năng Đăng nhập </h3>
![Sơ đồ login](https://raw.githubusercontent.com/ngduktrong/Group15_OOP_NO2_term3_2025/c08089f4b67ff7fbe1f24208ab4cd3bcc3753b6f/img/ChucNanglogin.png)




