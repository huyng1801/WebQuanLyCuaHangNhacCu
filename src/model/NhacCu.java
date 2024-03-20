package model;

public class NhacCu {
    private int maNhacCu;
    private String tenNhacCu;
    private int loaiNhacCu;
    private long donGia;
    private int soLuongTon;
    private String xuatXu;
    private int nhaSanXuat;
    private int namSanXuat;
    private String moTa;
    private String hinhAnh;
    private int soLuongBan;
    public NhacCu() {
    }


    public int getMaNhacCu() {
        return maNhacCu;
    }

    public void setMaNhacCu(int maNhacCu) {
        this.maNhacCu = maNhacCu;
    }

    public String getTenNhacCu() {
        return tenNhacCu;
    }

    public void setTenNhacCu(String tenNhacCu) {
        this.tenNhacCu = tenNhacCu;
    }

    public int getLoaiNhacCu() {
        return loaiNhacCu;
    }

    public void setLoaiNhacCu(int loaiNhacCu) {
        this.loaiNhacCu = loaiNhacCu;
    }

    public long getDonGia() {
        return donGia;
    }

    public void setDonGia(long donGia) {
        this.donGia = donGia;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getNhaSanXuat() {
        return nhaSanXuat;
    }

    public void setNhaSanXuat(int nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getSoLuongBan() {
		return soLuongBan;
	}


	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}


	@Override
    public String toString() {
        return "NhacCu{" +
                "maNhacCu=" + maNhacCu +
                ", tenNhacCu='" + tenNhacCu + '\'' +
                ", loaiNhacCu=" + loaiNhacCu +
                ", donGia=" + donGia +
                ", soLuongTon=" + soLuongTon +
                ", xuatXu='" + xuatXu + '\'' +
                ", nhaSanXuat=" + nhaSanXuat +
                ", namSanXuat=" + namSanXuat +
                ", moTa='" + moTa + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                '}';
    }
}
