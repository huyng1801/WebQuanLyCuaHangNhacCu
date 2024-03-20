package model;

public class NhaSanXuat {
    private int maNhaSanXuat;
    private String tenNhaSanXuat;
    private String soDienThoai;
    private String diaChi;

    public NhaSanXuat() {
    }

    public NhaSanXuat(String tenNhaSanXuat, String soDienThoai, String diaChi) {
        this.tenNhaSanXuat = tenNhaSanXuat;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public int getMaNhaSanXuat() {
        return maNhaSanXuat;
    }

    public void setMaNhaSanXuat(int maNhaSanXuat) {
        this.maNhaSanXuat = maNhaSanXuat;
    }

    public String getTenNhaSanXuat() {
        return tenNhaSanXuat;
    }

    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhaSanXuat{" +
                "maNhaSanXuat=" + maNhaSanXuat +
                ", tenNhaSanXuat='" + tenNhaSanXuat + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }
}
