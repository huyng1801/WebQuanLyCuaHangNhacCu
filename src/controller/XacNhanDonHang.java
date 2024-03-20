package controller;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ChiTietHoaDon;
import model.HoaDon;
import model.KhachHang;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/XacNhanDonHang")
public class XacNhanDonHang extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 if (request.getCharacterEncoding() == null)
             request.setCharacterEncoding("UTF-8");
        try {
            String soDienThoai = request.getParameter("soDienThoai");
            String tenKhachHang = request.getParameter("tenKhachHang");
            String diaChi = request.getParameter("diaChi");

            KhachHang khachHang = KhachHangDAO.timKiemKhachHangTheoSoDienThoai(soDienThoai);

            if (khachHang == null) {
                khachHang = new KhachHang();
                khachHang.setSoDienThoai(soDienThoai);
                khachHang.setTenKhachHang(tenKhachHang);
                khachHang.setDiaChi(diaChi);

                khachHang.setMaKhachHang(KhachHangDAO.themKhachHang(khachHang));
            }

            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaKhachHang(khachHang.getMaKhachHang());
            LocalDateTime currentDateTime = LocalDateTime.now();
            hoaDon.setNgayLap(Date.valueOf(currentDateTime.toLocalDate()));

            int maHoaDon = HoaDonDAO.themHoaDon(hoaDon);
            String orderDetailsJson = request.getParameter("orderDetails");
            List<ChiTietHoaDon> chiTietHoaDon = convertJsonToOrderDetails(orderDetailsJson);
            for (ChiTietHoaDon chiTiet : chiTietHoaDon) {
                chiTiet.setMaHoaDon(maHoaDon);
                ChiTietHoaDonDAO.themChiTietHoaDon(chiTiet);
            }

            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("success", true);
            jsonResponse.put("message", "Đơn hàng đã được xác nhận thành công!");

            response.getWriter().write(jsonResponse.toString());

       
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi đơn hàng!");
        }
    }
    private List<ChiTietHoaDon> convertJsonToOrderDetails(String jsonString) {
        List<ChiTietHoaDon> orderDetails = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(jsonString);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonOrderDetail = jsonArray.getJSONObject(i);
            int maNhacCu = jsonOrderDetail.getInt("maNhacCu");
            int soLuong = jsonOrderDetail.getInt("soLuong");
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(0 ,maNhacCu, soLuong);
            orderDetails.add(chiTietHoaDon);
        }

        return orderDetails;
    }
}
