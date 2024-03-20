CREATE TABLE loai_nhac_cu (
	ma_loai_nhac_cu INT AUTO_INCREMENT PRIMARY KEY,
    ten_loai_nhac_cu VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE nha_san_xuat (
    ma_nha_san_xuat INT AUTO_INCREMENT PRIMARY KEY,
    ten_nha_san_xuat VARCHAR(100) NOT NULL UNIQUE,
    so_dien_thoai VARCHAR(20) NOT NULL,
    dia_chi VARCHAR(255) NOT NULL
);

-- Tạo bảng nhạc cụ
CREATE TABLE nhac_cu (
    ma_nhac_cu INT AUTO_INCREMENT PRIMARY KEY,
    ten_nhac_cu VARCHAR(100) NOT NULL UNIQUE,
    loai_nhac_cu INT,
    don_gia BIGINT NOT NULL,
    so_luong_ton INT NOT NULL,
    xuat_xu VARCHAR(50),
    nha_san_xuat INT,
    nam_san_xuat INT,
    mo_ta TEXT,
    hinh_anh TEXT,
    FOREIGN KEY (loai_nhac_cu) REFERENCES loai_nhac_cu(ma_loai_nhac_cu) ON DELETE CASCADE,
    FOREIGN KEY (nha_san_xuat) REFERENCES nha_san_xuat(ma_nha_san_xuat) ON DELETE CASCADE
);


-- Tạo bảng danh sách khách hàng
CREATE TABLE khach_hang (
    ma_khach_hang INT AUTO_INCREMENT PRIMARY KEY,
    ten_khach_hang VARCHAR(100) NOT NULL,
    so_dien_thoai VARCHAR(20) NOT NULL,
    dia_chi VARCHAR(100)
);

-- Tạo bảng hóa đơn
CREATE TABLE hoa_don (
    ma_hoa_don INT AUTO_INCREMENT PRIMARY KEY,
    ma_khach_hang INT,
    ngay_lap DATE DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ma_khach_hang) REFERENCES khach_hang(ma_khach_hang) ON DELETE CASCADE
);

-- Tạo bảng chi tiết đơn hàng
CREATE TABLE chi_tiet_hoa_don (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ma_hoa_don INT,
    ma_nhac_cu INT,
    so_luong INT,
    FOREIGN KEY (ma_hoa_don) REFERENCES hoa_don(ma_hoa_don) ON DELETE CASCADE,
    FOREIGN KEY (ma_nhac_cu) REFERENCES nhac_cu(ma_nhac_cu) ON DELETE CASCADE
);

CREATE TABLE nguoi_dung (
    ma_nguoi_dung VARCHAR(100) PRIMARY KEY,
    ten_nguoi_dung VARCHAR(100) NOT NULL,
    mat_khau TEXT,
    hinh_anh TEXT
);
DELIMITER //
CREATE TRIGGER after_insert_chi_tiet_hoa_don
AFTER INSERT ON chi_tiet_hoa_don
FOR EACH ROW
BEGIN
    DECLARE so_luong_moi INT;

    -- Lấy số lượng mới từ chi tiết hóa đơn vừa được thêm
    SELECT so_luong INTO so_luong_moi
    FROM chi_tiet_hoa_don
    WHERE id = NEW.id;

    -- Cập nhật so_luong_ton trong bảng nhac_cu
    UPDATE nhac_cu
    SET so_luong_ton = so_luong_ton - so_luong_moi
    WHERE ma_nhac_cu = NEW.ma_nhac_cu;
END;
//
DELIMITER ;
INSERT INTO loai_nhac_cu (ten_loai_nhac_cu) VALUES
('Đàn guitar'),
('Đàn piano'),
('Đàn violin'),
('Trống'),
('Sáo trúc');
