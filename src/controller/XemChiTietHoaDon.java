package controller;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import model.ChiTietHoaDon;
import model.HoaDon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/XemChiTietHoaDon")
public class XemChiTietHoaDon extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int maHoaDon = Integer.parseInt(request.getParameter("maHoaDon"));
            HoaDon hoaDon = HoaDonDAO.timKiemHoaDonTheoMa(maHoaDon);
            List<ChiTietHoaDon> chiTietHoaDonList = ChiTietHoaDonDAO.layDanhSachChiTietHoaDonTheoMaHoaDon(maHoaDon);
            request.setAttribute("hoaDon", hoaDon);
            request.setAttribute("chiTietHoaDonList", chiTietHoaDonList);
            request.getRequestDispatcher("views/hoa_don/xem_chi_tiet_hoa_don.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid HoaDon ID.");
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
