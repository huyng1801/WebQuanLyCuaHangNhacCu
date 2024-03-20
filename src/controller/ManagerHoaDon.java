package controller;
import dao.HoaDonDAO;
import dao.LoaiNhacCuDAO;
import dao.NhaSanXuatDAO;
import dao.NhacCuDAO;
import dao.ChiTietHoaDonDAO;
import model.HoaDon;
import model.LoaiNhacCu;
import model.NhaSanXuat;
import model.NhacCu;
import model.ChiTietHoaDon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.sql.Date;
@WebServlet("/ManagerHoaDon")
public class ManagerHoaDon extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int currentPage = 1;
        int itemsPerPage = 5; 
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }
        List<NhacCu> danhSachNhacCu = NhacCuDAO.hienThiDanhSachNhacCu();

        int totalItems = danhSachNhacCu.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

        List<NhacCu> currentItems = danhSachNhacCu.subList(startIndex, endIndex);



        request.setAttribute("danhSachNhacCu", currentItems);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("views/hoa_don/index.jsp").forward(request, response);
    }
}
