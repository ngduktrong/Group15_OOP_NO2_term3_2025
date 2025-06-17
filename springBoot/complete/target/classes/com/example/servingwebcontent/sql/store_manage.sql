-- Tắt ràng buộc để xóa bảng

SET FOREIGN_KEY_CHECKS = 0;
SET @tables = NULL;
SELECT GROUP_CONCAT(table_schema, '.', table_name)
	INTO @tables
	FROM information_schema.tables
	WHERE table_schema = 'quanlyrcp';
SET @tables = CONCAT('DROP TABLE ', @tables);
PREPARE stmt FROM @tables;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
SET FOREIGN_KEY_CHECKS = 1;

USE quanlyrcp;

-- Bảng NguoiDung
CREATE TABLE IF NOT EXISTS NguoiDung (
	MaNguoiDung INT AUTO_INCREMENT PRIMARY KEY,
    HoTen NVARCHAR(100) NOT NULL,
    SoDienThoai VARCHAR(15) UNIQUE NOT NULL,
    Email NVARCHAR(100) UNIQUE NOT NULL,
    LoaiNguoiDung ENUM('KhachHang', 'NhanVien') NOT NULL
);

-- Bảng KhachHang
CREATE TABLE IF NOT EXISTS KhachHang (
	MaNguoiDung INT PRIMARY KEY,
    DiemTichLuy INT DEFAULT 0 CHECK (DiemTichLuy >= 0),
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung) ON DELETE CASCADE
);

-- Bảng ResetToken
CREATE TABLE IF NOT EXISTS ResetToken (
	Token VARCHAR(255) PRIMARY KEY, 
    Email NVARCHAR(100) NOT NULL, 
    expirarion_time TIMESTAMP NOT NULL, 
    FOREIGN KEY (Email) REFERENCES NguoiDung(Email) ON DELETE CASCADE
);

-- Bảng NhanVien
CREATE TABLE IF NOT EXISTS NhanVien (
	MaNguoiDung INT PRIMARY KEY,
    ChucVu NVARCHAR(50) NOT NULL,
    Luong DECIMAL(10, 2) CHECK (Luong >= 0) NOT NULL,
    VaiTro ENUM('Admin', 'QuanLy', 'ThuNgan', 'BanVe') NOT NULL,
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung) ON DELETE CASCADE
);

-- Bảng TaiKhoan
CREATE TABLE IF NOT EXISTS TaiKhoan (
	TenDangNhap NVARCHAR(50) PRIMARY KEY,
    MatKhau NVARCHAR(255) NOT NULL,
    LoaiTaiKhoan ENUM('admin', 'user') NOT NULL,
    MaNguoiDung INT UNIQUE,
    FOREIGN KEY (MaNguoiDung) REFERENCES NguoiDung(MaNguoiDung) ON DELETE CASCADE
);

-- Bảng Phim 
CREATE TABLE IF NOT EXISTS Phim (
	MaPhim INT AUTO_INCREMENT PRIMARY KEY ,
    TenPhim VARCHAR (100) NOT NULL ,
    ThoiLuong INT CHECK ( ThoiLuong > 0 ) NOT NULL ,
    NgayKhoiChieu DATE NOT NULL ,
    NuocSanXuat NVARCHAR(50) NOT NULL , 
    DinhDang NVARCHAR(20) NOT NULL ,
    MoTa TEXT ,
    DaoDien NVARCHAR(100) NOT NULL ,
    DuongDanPoster TEXT 
);

-- Bang PhongChieu 
CREATE TABLE IF NOT EXISTS PhongChieu (
	MaPhong INT AUTO_INCREMENT PRIMARY KEY ,
    TenPhong NVARCHAR(255) UNIQUE NOT NULL ,
    SoLuongGhe INT CHECK ( SoLuongGhe > 0 ) NOT NULL ,
    LoaiPhong NVARCHAR (50) NOT NULL 
);

-- Tao bang Ghe ( bang moi de quan ly ghe trong phong chieu ) 
CREATE TABLE IF NOT EXISTS Ghe (
	MaPhong INT NOT NULL ,
    SoGhe NVARCHAR (5) NOT NULL ,
    PRIMARY KEY (MaPhong , Soghe ) ,
    FOREIGN KEY (MaPhong ) REFERENCES  PhongChieu(MaPhong) ON DELETE CASCADE 
    
);
-- Tao bang SuatChieu them rang buoc thoi gian 

CREATE TABLE IF NOT EXISTS SuatChieu (
	MaSuatChieu INT AUTO_INCREMENT PRIMARY KEY ,
    MaPhim INT NOT NULL ,
    MaPhong INT NOT NULL ,
    NgayGioChieu DATETIME NOT NULL ,	
    FOREIGN KEY (MaPhim ) REFERENCES Phim(MaPhim) ON DELETE CASCADE ,
    FOREIGN KEY (MaPhong) REFERENCES PhongChieu(MaPhong) ON DELETE CASCADE 
);

-- Tao bang HoaDon 
CREATE TABLE IF NOT EXISTS HoaDon (
	MaHoaDon INT AUTO_INCREMENT PRIMARY KEY , 
    MaNhanVien INT ,
    MaKhachHang INT , 
    NgayLap DATETIME DEFAULT NOW() ,
    TongTien DECIMAL(10,2) CHECK (TongTien >= 0 ) NOT NULL , 
    FOREIGN KEY (MaNhanVien) REFERENCES NhanVien( MaNguoiDung) ON DELETE SET NULL,
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaNguoiDung) ON DELETE SET NULL 
);

-- Tao bang Ve 
CREATE TABLE  IF NOT EXISTS Ve (
	MaVe INT AUTO_INCREMENT PRIMARY KEY ,
    MaSuatChieu INT NOT  NULL ,
    MaPhong INT NOT NULL , 
    SoGhe NVARCHAR(5) NOT NULL ,
    MaHoaDon INT NULL ,
    GiaVe DECIMAL(10,2) CHECK ( GiaVe >= 0 ) NOT NULL ,
    TrangThai ENUM ('available ', 'booked', 'paid', 'cancelled ', 'pending'), 
    NgayDat DATETIME NULL ,
    FOREIGN KEY (MaSuatChieu) REFERENCES SuatChieu(MaSuatChieu) ON DELETE CASCADE ,
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon) ON DELETE SET NULL , 
    FOREIGN KEY (MaPhong , SoGhe ) REFERENCES Ghe(MaPhong, SoGhe) ON DELETE NO ACTION ,
    CONSTRAINT UQ_SuatChieu_SoGhe UNIQUE (MaSuatChieu ,SoGhe) 
);
-- Tao bang ChiTietHoaDon 
CREATE TABLE IF NOT EXISTS ChiTietHoaDon (
	MaHoaDon INT NOT NULL ,
    MaVe INT NOT NULL ,
    FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon) ON DELETE CASCADE ,
    FOREIGN KEY (MaVe) REFERENCES Ve(MaVe) ON DELETE CASCADE 
);


-- ========================================
-- DU LIEU MAU CHO HE THONG QUAN LY RCP
-- ========================================

USE quanlyrcp;

-- 1. THEM PHIM
INSERT INTO Phim (TenPhim, ThoiLuong, NgayKhoiChieu, NuocSanXuat, DinhDang, MoTa, DaoDien, DuongDanPoster) VALUES
('Minions', 90, '2024-01-15', 'USA', '3D', 'Phiêu lưu của Minions', 'Pierre Coffin', 'poster_minions.jpg'),
('Doremon Movie',' 50 ' , '2025-05-23', 'JPN' ,'5D','Cuộc phiêu lưu đến hành tinh khỉ ' ,' Puskas' ,'doremon.jpg ') ;

-- 2. THEM PHONG CHIEU
INSERT INTO PhongChieu (TenPhong, SoLuongGhe, LoaiPhong) VALUES
('Phòng 1', 50, '2D'),
('Phòng 2', 30, '3D');

-- 3. THEM GHE
-- Phòng 1 (50 ghế: A01 - E10)
SET @i := -1;
INSERT INTO Ghe (MaPhong, SoGhe)
SELECT 1, CONCAT(CHAR(65 + FLOOR(i/10)), LPAD((i%10)+1, 2, '0'))
FROM (SELECT @i := @i + 1 AS i FROM mysql.help_topic LIMIT 50) AS tmp;

-- Phòng 2 (30 ghế: A01 - C10)
SET @i := -1;
INSERT INTO Ghe (MaPhong, SoGhe)
SELECT 2, CONCAT(CHAR(65 + FLOOR(i/10)), LPAD((i%10)+1, 2, '0'))
FROM (SELECT @i := @i + 1 AS i FROM mysql.help_topic LIMIT 30) AS tmp;

-- 4. THEM SUAT CHIEU
INSERT INTO SuatChieu (MaPhim, MaPhong, NgayGioChieu) VALUES
(1, 1, '2025-05-20 18:30:00'),
(2, 2, '2025-05-21 15:00:00');

-- 5. THEM NGUOI DUNG (5 khach, 5 nhan vien)
INSERT INTO NguoiDung (HoTen, SoDienThoai, Email, LoaiNguoiDung) VALUES
('Nguyen Duc Trong ', '0983241301', '23010594@st.phenikaa-uni.edu.vn', 'NhanVien'),
('Tran Thi B', '0911000002', 'b@example.com', 'KhachHang'),
('Le Van C', '0911000003', 'c@example.com', 'KhachHang'),
('Pham Thi D', '0911000004', 'd@example.com', 'KhachHang'),
('Hoang Van E', '0911000005', 'e@example.com', 'KhachHang'),
('Nguyen Van F', '0911000006', 'f@example.com', 'NhanVien'),
('Tran Thi G', '0911000007', 'g@example.com', 'NhanVien'),
('Le Van H', '0911000008', 'h@example.com', 'NhanVien'),
('Pham Thi I', '0911000009', 'i@example.com', 'NhanVien'),
('Hoang Van K', '0911000010', 'k@example.com', 'NhanVien');

-- 6. THEM KHACH HANG
INSERT INTO KhachHang (MaNguoiDung, DiemTichLuy) VALUES
(1, 100), (2, 250), (3, 0), (4, 50), (5, 150);

-- 7. THEM NHAN VIEN
INSERT INTO NhanVien (MaNguoiDung, ChucVu, Luong, VaiTro) VALUES
(6, 'Quản lý rạp', 15000000, 'QuanLy'),
(7, 'Bán vé', 8000000, 'BanVe'),
(8, 'Thu ngân', 9000000, 'ThuNgan'),
(9, 'Admin hệ thống', 20000000, 'Admin'),
(10, 'Nhân viên hỗ trợ', 7500000, 'BanVe');

-- 8. THEM TAI KHOAN
INSERT INTO TaiKhoan (TenDangNhap, MatKhau, LoaiTaiKhoan, MaNguoiDung) VALUES
('userA', 'matkhauA', 'user', 1),
('userB', 'matkhauB', 'user', 2),
('userC', 'matkhauC', 'user', 3),
('userD', 'matkhauD', 'user', 4),
('userE', 'matkhauE', 'user', 5),
('adminF', 'matkhauF', 'admin', 6),
('adminG', 'matkhauG', 'admin', 7),
('adminH', 'matkhauH', 'admin', 8),
('adminI', 'matkhauI', 'admin', 9),
('adminK', 'matkhauK', 'admin', 10);

-- 9. THEM HOA DON
INSERT INTO HoaDon (MaNhanVien, MaKhachHang, TongTien) VALUES
(6, 1, 100000),
(7, 2, 150000);

-- 10. THEM VE
INSERT INTO Ve (MaSuatChieu, MaPhong, SoGhe, MaHoaDon, GiaVe, TrangThai, NgayDat) VALUES
(1, 1, 'A01', 1, 100000, 'paid', NOW()),
(2, 2, 'A02', 2, 150000, 'paid', NOW());

-- 11. THEM CHI TIET HOA DON
INSERT INTO ChiTietHoaDon (MaHoaDon, MaVe) VALUES
(1, 1),
(2, 2);
USE quanlyrcp;