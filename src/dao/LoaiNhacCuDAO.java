package dao;
import model.LoaiNhacCu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.JdbcUtils;

public class LoaiNhacCuDAO {
	  public static boolean themLoaiNhacCu(LoaiNhacCu loaiNhacCu) {
	        try (Connection connection = JdbcUtils.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "INSERT INTO loai_nhac_cu (ten_loai_nhac_cu) VALUES (?)")) {

	            preparedStatement.setString(1, loaiNhacCu.getTenLoaiNhacCu());
	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public static boolean suaLoaiNhacCu(LoaiNhacCu loaiNhacCu) {
	        try (Connection connection = JdbcUtils.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "UPDATE loai_nhac_cu SET ten_loai_nhac_cu = ? WHERE ma_loai_nhac_cu = ?")) {

	            preparedStatement.setString(1, loaiNhacCu.getTenLoaiNhacCu());
	            preparedStatement.setInt(2, loaiNhacCu.getMaLoaiNhacCu());
	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	    public static boolean xoaLoaiNhacCu(int maLoaiNhacCu) {
	        try (Connection connection = JdbcUtils.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "DELETE FROM loai_nhac_cu WHERE ma_loai_nhac_cu = ?")) {

	            preparedStatement.setInt(1, maLoaiNhacCu);
	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;

	        } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

    public static LoaiNhacCu timKiemLoaiNhacCuTheoMa(int maLoaiNhacCu) {
        LoaiNhacCu loaiNhacCu = null;
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM loai_nhac_cu WHERE ma_loai_nhac_cu = ?")) {

            preparedStatement.setInt(1, maLoaiNhacCu);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                loaiNhacCu = new LoaiNhacCu();
                loaiNhacCu.setMaLoaiNhacCu(resultSet.getInt("ma_loai_nhac_cu"));
                loaiNhacCu.setTenLoaiNhacCu(resultSet.getString("ten_loai_nhac_cu"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return loaiNhacCu;
    }
    public static List<LoaiNhacCu> timKiemLoaiNhacCuTheoTen(String tenLoaiNhacCu) {
        List<LoaiNhacCu> danhSachLoaiNhacCu = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM loai_nhac_cu WHERE ten_loai_nhac_cu LIKE ?")) {

            preparedStatement.setString(1, "%" + tenLoaiNhacCu + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LoaiNhacCu loaiNhacCu = new LoaiNhacCu();
                loaiNhacCu.setMaLoaiNhacCu(resultSet.getInt("ma_loai_nhac_cu"));
                loaiNhacCu.setTenLoaiNhacCu(resultSet.getString("ten_loai_nhac_cu"));
                danhSachLoaiNhacCu.add(loaiNhacCu);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachLoaiNhacCu;
    }
    public static List<LoaiNhacCu> hienThiDanhSachLoaiNhacCu() {
        List<LoaiNhacCu> danhSachLoaiNhacCu = new ArrayList<>();
        try (Connection connection = JdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM loai_nhac_cu ORDER BY ma_loai_nhac_cu");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                LoaiNhacCu loaiNhacCu = new LoaiNhacCu();
                loaiNhacCu.setMaLoaiNhacCu(resultSet.getInt("ma_loai_nhac_cu"));
                loaiNhacCu.setTenLoaiNhacCu(resultSet.getString("ten_loai_nhac_cu"));
                danhSachLoaiNhacCu.add(loaiNhacCu);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhSachLoaiNhacCu;
    }
  


}
