package dao;

import model.KhachHang;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
	public static int themKhachHang(KhachHang khachHang) {
	    try (Connection connection = JdbcUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "INSERT INTO khach_hang (ten_khach_hang, so_dien_thoai, dia_chi) VALUES (?, ?, ?)",
	                 Statement.RETURN_GENERATED_KEYS)) {

	        preparedStatement.setString(1, khachHang.getTenKhachHang());
	        preparedStatement.setString(2, khachHang.getSoDienThoai());
	        preparedStatement.setString(3, khachHang.getDiaChi());

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            // Retrieve the generated keys
	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    // Return the generated ID
	                    return generatedKeys.getInt(1);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    // Return -1 to indicate failure
	    return -1;
	}

    public static boolean suaKhachHang(KhachHang khachHang) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE khach_hang SET ten_khach_hang = ?, so_dien_thoai = ?, dia_chi = ? WHERE ma_khach_hang = ?")) {

            preparedStatement.setString(1, khachHang.getTenKhachHang());
            preparedStatement.setString(2, khachHang.getSoDienThoai());
            preparedStatement.setString(3, khachHang.getDiaChi());
            preparedStatement.setInt(4, khachHang.getMaKhachHang());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaKhachHang(int maKhachHang) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM khach_hang WHERE ma_khach_hang = ?")) {

            preparedStatement.setInt(1, maKhachHang);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static KhachHang timKiemKhachHangTheoMa(int maKhachHang) {
        KhachHang khachHang = null;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM khach_hang WHERE ma_khach_hang = ?")) {

            preparedStatement.setInt(1, maKhachHang);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                khachHang = new KhachHang();
                khachHang.setMaKhachHang(resultSet.getInt("ma_khach_hang"));
                khachHang.setTenKhachHang(resultSet.getString("ten_khach_hang"));
                khachHang.setSoDienThoai(resultSet.getString("so_dien_thoai"));
                khachHang.setDiaChi(resultSet.getString("dia_chi"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return khachHang;
    }

    public static List<KhachHang> timKiemKhachHangTheoTen(String tenKhachHang) {
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM khach_hang WHERE ten_khach_hang LIKE ?")) {

            preparedStatement.setString(1, "%" + tenKhachHang + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(resultSet.getInt("ma_khach_hang"));
                kh.setTenKhachHang(resultSet.getString("ten_khach_hang"));
                kh.setSoDienThoai(resultSet.getString("so_dien_thoai"));
                kh.setDiaChi(resultSet.getString("dia_chi"));
                danhSachKhachHang.add(kh);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachKhachHang;
    }

    public static List<KhachHang> hienThiDanhSachKhachHang() {
        List<KhachHang> danhSachKhachHang = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM khach_hang ORDER BY ma_khach_hang");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(resultSet.getInt("ma_khach_hang"));
                kh.setTenKhachHang(resultSet.getString("ten_khach_hang"));
                kh.setSoDienThoai(resultSet.getString("so_dien_thoai"));
                kh.setDiaChi(resultSet.getString("dia_chi"));
                danhSachKhachHang.add(kh);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachKhachHang;
    }
    public static KhachHang timKiemKhachHangTheoSoDienThoai(String soDienThoai) {
        KhachHang khachHang = null;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM khach_hang WHERE so_dien_thoai = ?")) {

            preparedStatement.setString(1, soDienThoai);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                khachHang = new KhachHang();
                khachHang.setMaKhachHang(resultSet.getInt("ma_khach_hang"));
                khachHang.setTenKhachHang(resultSet.getString("ten_khach_hang"));
                khachHang.setSoDienThoai(resultSet.getString("so_dien_thoai"));
                khachHang.setDiaChi(resultSet.getString("dia_chi"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return khachHang;
    }

}
