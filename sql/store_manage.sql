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
CREATE TABLE IF NOT EXISTS ResetToKen (
	Token VARCHAR(255) PRIMARY KEY, 
    Email NVARCHAR(100) NOT NULL, 
    expiration_time TIMESTAMP NOT NULL, 
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
    TrangThai ENUM ('available ' , 'booked' , 'paid' , 'cancelled ' , 'pending' ) DEFAULT 'available' NOT NULL , 
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