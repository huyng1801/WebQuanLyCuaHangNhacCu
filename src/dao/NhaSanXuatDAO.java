package dao;

import model.LoaiNhacCu;
import model.NhaSanXuat;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhaSanXuatDAO {
    public static boolean themNhaSanXuat(NhaSanXuat nhaSanXuat) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO nha_san_xuat (ten_nha_san_xuat, so_dien_thoai, dia_chi) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, nhaSanXuat.getTenNhaSanXuat());
            preparedStatement.setString(2, nhaSanXuat.getSoDienThoai());
            preparedStatement.setString(3, nhaSanXuat.getDiaChi());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean suaNhaSanXuat(NhaSanXuat nhaSanXuat) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE nha_san_xuat SET ten_nha_san_xuat = ?, so_dien_thoai = ?, dia_chi = ? WHERE ma_nha_san_xuat = ?")) {

            preparedStatement.setString(1, nhaSanXuat.getTenNhaSanXuat());
            preparedStatement.setString(2, nhaSanXuat.getSoDienThoai());
            preparedStatement.setString(3, nhaSanXuat.getDiaChi());
            preparedStatement.setInt(4, nhaSanXuat.getMaNhaSanXuat());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean xoaNhaSanXuat(int maNhaSanXuat) {
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM nha_san_xuat WHERE ma_nha_san_xuat = ?")) {

            preparedStatement.setInt(1, maNhaSanXuat);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static NhaSanXuat timKiemNhaSanXuatTheoMa(int maNhaSanXuat) {
        NhaSanXuat nhaSanXuat = null;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM nha_san_xuat WHERE ma_nha_san_xuat = ?")) {

            preparedStatement.setInt(1, maNhaSanXuat);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nhaSanXuat = new NhaSanXuat();
                nhaSanXuat.setMaNhaSanXuat(resultSet.getInt("ma_nha_san_xuat"));
                nhaSanXuat.setTenNhaSanXuat(resultSet.getString("ten_nha_san_xuat"));
                nhaSanXuat.setSoDienThoai(resultSet.getString("so_dien_thoai"));
                nhaSanXuat.setDiaChi(resultSet.getString("dia_chi"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return nhaSanXuat;
    }
    public static List<NhaSanXuat> timKiemNhaSanXuatTheoTen(String tenNhaSanXuat) {
    	List<NhaSanXuat> danhSachNhaSanXuat = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
            		 "SELECT * FROM nha_san_xuat WHERE ten_nha_san_xuat = ?")) {

            preparedStatement.setString(1, "%" + tenNhaSanXuat + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	  NhaSanXuat nhaSanXuat = new NhaSanXuat();
                  nhaSanXuat.setMaNhaSanXuat(resultSet.getInt("ma_nha_san_xuat"));
                  nhaSanXuat.setTenNhaSanXuat(resultSet.getString("ten_nha_san_xuat"));
                  nhaSanXuat.setSoDienThoai(resultSet.getString("so_dien_thoai"));
                  nhaSanXuat.setDiaChi(resultSet.getString("dia_chi"));
                  danhSachNhaSanXuat.add(nhaSanXuat);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachNhaSanXuat;
    }
    public static List<NhaSanXuat> hienThiDanhSachNhaSanXuat() {
        List<NhaSanXuat> danhSachNhaSanXuat = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM nha_san_xuat ORDER BY ma_nha_san_xuat");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                NhaSanXuat nhaSanXuat = new NhaSanXuat();
                nhaSanXuat.setMaNhaSanXuat(resultSet.getInt("ma_nha_san_xuat"));
                nhaSanXuat.setTenNhaSanXuat(resultSet.getString("ten_nha_san_xuat"));
                nhaSanXuat.setSoDienThoai(resultSet.getString("so_dien_thoai"));
                nhaSanXuat.setDiaChi(resultSet.getString("dia_chi"));
                danhSachNhaSanXuat.add(nhaSanXuat);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachNhaSanXuat;
    }
}
