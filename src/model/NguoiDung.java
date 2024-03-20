package model;

public class NguoiDung {
    private String maNguoiDung;
    private String tenNguoiDung;
    private String matKhau;
    private String hinhAnh;

    // Constructors, getters, and setters

    public NguoiDung() {
    }

    public NguoiDung(String maNguoiDung, String tenNguoiDung, String matKhau, String hinhAnh) {
        this.maNguoiDung = maNguoiDung;
        this.tenNguoiDung = tenNguoiDung;
        this.matKhau = matKhau;
        this.hinhAnh = hinhAnh;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
