package model;

public class ChiTietHoaDon {
    private int id;
    private int maHoaDon;
    private int maNhacCu;
    private int soLuong;

    public ChiTietHoaDon() {
        // Default constructor
    }

    public ChiTietHoaDon(int maHoaDon, int maNhacCu, int soLuong) {
		super();
		this.maHoaDon = maHoaDon;
		this.maNhacCu = maNhacCu;
		this.soLuong = soLuong;
	}

	public ChiTietHoaDon(int id, int maHoaDon, int maNhacCu, int soLuong) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.maNhacCu = maNhacCu;
        this.soLuong = soLuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaNhacCu() {
        return maNhacCu;
    }

    public void setMaNhacCu(int maNhacCu) {
        this.maNhacCu = maNhacCu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "id=" + id +
                ", maHoaDon=" + maHoaDon +
                ", maNhacCu=" + maNhacCu +
                ", soLuong=" + soLuong +
                '}';
    }
}
