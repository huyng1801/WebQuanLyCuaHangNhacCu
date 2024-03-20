package model;

import java.sql.Date;

public class HoaDon {
    private int maHoaDon;
    private int maKhachHang;
    private Date ngayLap;

    public HoaDon() {
        // Default constructor
    }

    public HoaDon(int maHoaDon, int maKhachHang, Date ngayLap) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.ngayLap = ngayLap;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon=" + maHoaDon +
                ", maKhachHang=" + maKhachHang +
                ", ngayLap=" + ngayLap +
                '}';
    }
}
