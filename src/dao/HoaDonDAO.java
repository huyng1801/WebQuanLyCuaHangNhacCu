package dao;

import model.HoaDon;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
	public static int themHoaDon(HoaDon hoaDon) {
	    try (Connection connection = JdbcUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "INSERT INTO hoa_don (ma_khach_hang, ngay_lap) VALUES (?, ?)",
	                 Statement.RETURN_GENERATED_KEYS)) {

	        preparedStatement.setInt(1, hoaDon.getMaKhachHang());
	        preparedStatement.setDate(2, hoaDon.getNgayLap());

	        int rowsAffected = preparedStatement.executeUpdate();

	        if (rowsAffected > 0) {
	            // Retrieve the generated keys (in this case, the order ID)
	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    return generatedKeys.getInt(1); // Return the order ID
	                } else {
	                    throw new SQLException("Creating order failed, no ID obtained.");
	                }
	            }
	        } else {
	            return -1; // Return -1 to indicate failure
	        }

	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        return -1; // Return -1 to indicate failure
	    }
	}

    public static boolean suaHoaDon(HoaDon hoaDon) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE hoa_don SET ma_khach_hang = ?, ngay_lap = ? WHERE ma_hoa_don = ?")) {

            preparedStatement.setInt(1, hoaDon.getMaKhachHang());
            preparedStatement.setDate(2, hoaDon.getNgayLap());
            preparedStatement.setInt(3, hoaDon.getMaHoaDon());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaHoaDon(int maHoaDon) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM hoa_don WHERE ma_hoa_don = ?")) {

            preparedStatement.setInt(1, maHoaDon);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static HoaDon timKiemHoaDonTheoMa(int maHoaDon) {
        HoaDon hoaDon = null;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM hoa_don WHERE ma_hoa_don = ?")) {

            preparedStatement.setInt(1, maHoaDon);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(resultSet.getInt("ma_hoa_don"));
                hoaDon.setMaKhachHang(resultSet.getInt("ma_khach_hang"));
                hoaDon.setNgayLap(resultSet.getDate("ngay_lap"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return hoaDon;
    }

    public static List<HoaDon> hienThiDanhSachHoaDon() {
        List<HoaDon> danhSachHoaDon = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM hoa_don ORDER BY ma_hoa_don");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(resultSet.getInt("ma_hoa_don"));
                hd.setMaKhachHang(resultSet.getInt("ma_khach_hang"));
                hd.setNgayLap(resultSet.getDate("ngay_lap"));
                danhSachHoaDon.add(hd);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachHoaDon;
    }
}
