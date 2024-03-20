package dao;

import model.ChiTietHoaDon;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {
    public static boolean themChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO chi_tiet_hoa_don (ma_hoa_don, ma_nhac_cu, so_luong) VALUES (?, ?, ?)")) {

            preparedStatement.setInt(1, chiTietHoaDon.getMaHoaDon());
            preparedStatement.setInt(2, chiTietHoaDon.getMaNhacCu());
            preparedStatement.setInt(3, chiTietHoaDon.getSoLuong());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean suaChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE chi_tiet_hoa_don SET ma_hoa_don = ?, ma_nhac_cu = ?, so_luong = ? WHERE id = ?")) {

            preparedStatement.setInt(1, chiTietHoaDon.getMaHoaDon());
            preparedStatement.setInt(2, chiTietHoaDon.getMaNhacCu());
            preparedStatement.setInt(3, chiTietHoaDon.getSoLuong());
            preparedStatement.setInt(4, chiTietHoaDon.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaChiTietHoaDon(int id) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM chi_tiet_hoa_don WHERE id = ?")) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<ChiTietHoaDon> layDanhSachChiTietHoaDonTheoMaHoaDon(int maHoaDon) {
        List<ChiTietHoaDon> danhSachChiTietHoaDon = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM chi_tiet_hoa_don WHERE ma_hoa_don = ?")) {

            preparedStatement.setInt(1, maHoaDon);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setId(resultSet.getInt("id"));
                chiTietHoaDon.setMaHoaDon(resultSet.getInt("ma_hoa_don"));
                chiTietHoaDon.setMaNhacCu(resultSet.getInt("ma_nhac_cu"));
                chiTietHoaDon.setSoLuong(resultSet.getInt("so_luong"));
                danhSachChiTietHoaDon.add(chiTietHoaDon);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachChiTietHoaDon;
    }
}
