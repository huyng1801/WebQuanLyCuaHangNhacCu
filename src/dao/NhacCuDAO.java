package dao;

import model.NhacCu;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NhacCuDAO {
    public static boolean themNhacCu(NhacCu nhacCu) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO nhac_cu (ten_nhac_cu, loai_nhac_cu, don_gia, so_luong_ton, xuat_xu, nha_san_xuat, nam_san_xuat, mo_ta, hinh_anh) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, nhacCu.getTenNhacCu());
            preparedStatement.setInt(2, nhacCu.getLoaiNhacCu());
            preparedStatement.setLong(3, nhacCu.getDonGia());
            preparedStatement.setInt(4, nhacCu.getSoLuongTon());
            preparedStatement.setString(5, nhacCu.getXuatXu());
            preparedStatement.setInt(6, nhacCu.getNhaSanXuat());
            preparedStatement.setInt(7, nhacCu.getNamSanXuat());
            preparedStatement.setString(8, nhacCu.getMoTa());
            preparedStatement.setString(9, nhacCu.getHinhAnh());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean suaNhacCu(NhacCu nhacCu) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE nhac_cu SET ten_nhac_cu = ?, loai_nhac_cu = ?, don_gia = ?, so_luong_ton = ?, xuat_xu = ?, " +
                             "nha_san_xuat = ?, nam_san_xuat = ?, mo_ta = ?, hinh_anh = ? WHERE ma_nhac_cu = ?")) {

            preparedStatement.setString(1, nhacCu.getTenNhacCu());
            preparedStatement.setInt(2, nhacCu.getLoaiNhacCu());
            preparedStatement.setLong(3, nhacCu.getDonGia());
            preparedStatement.setInt(4, nhacCu.getSoLuongTon());
            preparedStatement.setString(5, nhacCu.getXuatXu());
            preparedStatement.setInt(6, nhacCu.getNhaSanXuat());
            preparedStatement.setInt(7, nhacCu.getNamSanXuat());
            preparedStatement.setString(8, nhacCu.getMoTa());
            preparedStatement.setString(9, nhacCu.getHinhAnh());
            preparedStatement.setInt(10, nhacCu.getMaNhacCu());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaNhacCu(int maNhacCu) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM nhac_cu WHERE ma_nhac_cu = ?")) {

            preparedStatement.setInt(1, maNhacCu);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static NhacCu timKiemNhacCuTheoMa(int maNhacCu) {
        NhacCu nhacCu = null;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM nhac_cu WHERE ma_nhac_cu = ?")) {

            preparedStatement.setInt(1, maNhacCu);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nhacCu = new NhacCu();
                nhacCu.setMaNhacCu(resultSet.getInt("ma_nhac_cu"));
                nhacCu.setTenNhacCu(resultSet.getString("ten_nhac_cu"));
                nhacCu.setLoaiNhacCu(resultSet.getInt("loai_nhac_cu"));
                nhacCu.setDonGia(resultSet.getLong("don_gia"));
                nhacCu.setSoLuongTon(resultSet.getInt("so_luong_ton"));
                nhacCu.setXuatXu(resultSet.getString("xuat_xu"));
                nhacCu.setNhaSanXuat(resultSet.getInt("nha_san_xuat"));
                nhacCu.setNamSanXuat(resultSet.getInt("nam_san_xuat"));
                nhacCu.setMoTa(resultSet.getString("mo_ta"));
                nhacCu.setHinhAnh(resultSet.getString("hinh_anh"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nhacCu;
    }

    // Phương thức tìm kiếm nhạc cụ theo tên
    public static List<NhacCu> timKiemNhacCuTheoTen(String tenNhacCu) {
        List<NhacCu> danhSachNhacCu = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM nhac_cu WHERE ten_nhac_cu LIKE ?")) {

            preparedStatement.setString(1, "%" + tenNhacCu + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NhacCu nc = new NhacCu();
                nc.setMaNhacCu(resultSet.getInt("ma_nhac_cu"));
                nc.setTenNhacCu(resultSet.getString("ten_nhac_cu"));
                nc.setLoaiNhacCu(resultSet.getInt("loai_nhac_cu"));
                nc.setDonGia(resultSet.getLong("don_gia"));
                nc.setSoLuongTon(resultSet.getInt("so_luong_ton"));
                nc.setXuatXu(resultSet.getString("xuat_xu"));
                nc.setNhaSanXuat(resultSet.getInt("nha_san_xuat"));
                nc.setNamSanXuat(resultSet.getInt("nam_san_xuat"));
                nc.setMoTa(resultSet.getString("mo_ta"));
                nc.setHinhAnh(resultSet.getString("hinh_anh"));
                danhSachNhacCu.add(nc);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachNhacCu;
    }

    // Phương thức hiển thị danh sách nhạc cụ
    public static List<NhacCu> hienThiDanhSachNhacCu() {
        List<NhacCu> danhSachNhacCu = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM nhac_cu ORDER BY ma_nhac_cu");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                NhacCu nc = new NhacCu();
                nc.setMaNhacCu(resultSet.getInt("ma_nhac_cu"));
                nc.setTenNhacCu(resultSet.getString("ten_nhac_cu"));
                nc.setLoaiNhacCu(resultSet.getInt("loai_nhac_cu"));
                nc.setDonGia(resultSet.getLong("don_gia"));
                nc.setSoLuongTon(resultSet.getInt("so_luong_ton"));
                nc.setXuatXu(resultSet.getString("xuat_xu"));
                nc.setNhaSanXuat(resultSet.getInt("nha_san_xuat"));
                nc.setNamSanXuat(resultSet.getInt("nam_san_xuat"));
                nc.setMoTa(resultSet.getString("mo_ta"));
                nc.setHinhAnh(resultSet.getString("hinh_anh"));
                danhSachNhacCu.add(nc);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachNhacCu;
    }
    public static List<NhacCu> thongKeNhacCuBanChay() {
        List<NhacCu> danhSachNhacCu = new ArrayList<>();
        String sql = "SELECT " +
                "    nhac_cu.ma_nhac_cu, " +
                "    ten_nhac_cu, " +
                "    loai_nhac_cu, " +
                "    nha_san_xuat " +
                "    so_luong_ton, " +
                "    don_gia, " +
                "    xuat_xu, " +
                "    nha_san_xuat, " +
                "    nam_san_xuat, " +
                "    mo_ta, " +
                "    hinh_anh, " +
                "    (SELECT SUM(so_luong) FROM chi_tiet_hoa_don WHERE chi_tiet_hoa_don.ma_nhac_cu = nhac_cu.ma_nhac_cu) AS so_luong_ban " +
                "FROM " +
                "    nhac_cu " +
                "WHERE " +
                "    nhac_cu.ma_nhac_cu IN (SELECT ma_nhac_cu FROM chi_tiet_hoa_don) " +
                "ORDER BY " +
                "    so_luong_ban DESC";

        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                NhacCu nc = new NhacCu();
                nc.setMaNhacCu(resultSet.getInt("ma_nhac_cu"));
                nc.setTenNhacCu(resultSet.getString("ten_nhac_cu"));
                nc.setLoaiNhacCu(resultSet.getInt("loai_nhac_cu"));
                nc.setNhaSanXuat(resultSet.getInt("nha_san_xuat"));
                nc.setSoLuongTon(resultSet.getInt("so_luong_ton"));
                nc.setDonGia(resultSet.getLong("don_gia"));
                nc.setXuatXu(resultSet.getString("xuat_xu"));
                nc.setNhaSanXuat(resultSet.getInt("nha_san_xuat"));
                nc.setNamSanXuat(resultSet.getInt("nam_san_xuat"));
                nc.setMoTa(resultSet.getString("mo_ta"));
                nc.setHinhAnh(resultSet.getString("hinh_anh"));
                nc.setSoLuongBan(resultSet.getInt("so_luong_ban"));
                danhSachNhacCu.add(nc);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachNhacCu;
    }


}
