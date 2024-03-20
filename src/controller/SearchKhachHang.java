package controller;

import dao.KhachHangDAO;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/SearchKhachHang")
public class SearchKhachHang extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding("UTF-8");

        String tenKhachHang = request.getParameter("q");

        int itemsPerPage = 5;

        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<KhachHang> danhSachKhachHang = KhachHangDAO.timKiemKhachHangTheoTen(tenKhachHang);
        int totalItems = danhSachKhachHang.size();

        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int startIndex = (currentPage - 1) * itemsPerPage;

        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

        List<KhachHang> currentItems = danhSachKhachHang.subList(startIndex, endIndex);

        request.setAttribute("danhSachKhachHang", currentItems);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        request.getRequestDispatcher("views/khach_hang/index.jsp").forward(request, response);
    }
}
