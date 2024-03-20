package dao;

import model.NguoiDung;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungDAO {
    public static boolean themNguoiDung(NguoiDung nguoiDung) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO nguoi_dung (ma_nguoi_dung, ten_nguoi_dung, mat_khau, hinh_anh) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, nguoiDung.getMaNguoiDung());
            preparedStatement.setString(2, nguoiDung.getTenNguoiDung());
            preparedStatement.setString(3, nguoiDung.getMatKhau());
            preparedStatement.setString(4, nguoiDung.getHinhAnh());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static NguoiDung timNguoiDungTheoMa(String maNguoiDung) {
        NguoiDung nguoiDung = null;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM nguoi_dung WHERE ma_nguoi_dung = ?")) {

            preparedStatement.setString(1, maNguoiDung);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nguoiDung = new NguoiDung();
                nguoiDung.setMaNguoiDung(resultSet.getString("ma_nguoi_dung"));
                nguoiDung.setTenNguoiDung(resultSet.getString("ten_nguoi_dung"));
                nguoiDung.setMatKhau(resultSet.getString("mat_khau"));
                nguoiDung.setHinhAnh(resultSet.getString("hinh_anh"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nguoiDung;
    }

    public static List<NguoiDung> hienThiDanhSachNguoiDung() {
        List<NguoiDung> danhSachNguoiDung = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM nguoi_dung ORDER BY ma_nguoi_dung");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                NguoiDung nguoiDung = new NguoiDung();
                nguoiDung.setMaNguoiDung(resultSet.getString("ma_nguoi_dung"));
                nguoiDung.setTenNguoiDung(resultSet.getString("ten_nguoi_dung"));
                nguoiDung.setMatKhau(resultSet.getString("mat_khau"));
                nguoiDung.setHinhAnh(resultSet.getString("hinh_anh"));
                danhSachNguoiDung.add(nguoiDung);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachNguoiDung;
    }
    public static boolean capNhatNguoiDungKhongMatKhau(NguoiDung nguoiDung) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE nguoi_dung SET ten_nguoi_dung = ?, hinh_anh = ? WHERE ma_nguoi_dung = ?")) {

            preparedStatement.setString(1, nguoiDung.getTenNguoiDung());
            preparedStatement.setString(2, nguoiDung.getHinhAnh());
            preparedStatement.setString(3, nguoiDung.getMaNguoiDung());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean capNhatNguoiDung(NguoiDung nguoiDung) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE nguoi_dung SET ten_nguoi_dung = ?, mat_khau = ?, hinh_anh = ? WHERE ma_nguoi_dung = ?")) {

            preparedStatement.setString(1, nguoiDung.getTenNguoiDung());
            preparedStatement.setString(2, nguoiDung.getMatKhau());
            preparedStatement.setString(3, nguoiDung.getHinhAnh());
            preparedStatement.setString(4, nguoiDung.getMaNguoiDung());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaNguoiDung(String maNguoiDung) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM nguoi_dung WHERE ma_nguoi_dung = ?")) {

            preparedStatement.setString(1, maNguoiDung);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean dangNhap(String tenDangNhap, String matKhau) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM nguoi_dung WHERE ma_nguoi_dung = ? AND mat_khau = ?")) {

            preparedStatement.setString(1, tenDangNhap);
            preparedStatement.setString(2, matKhau);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // If there is at least one row, login is successful

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
