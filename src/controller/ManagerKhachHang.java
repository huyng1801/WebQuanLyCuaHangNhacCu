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

@WebServlet("/ManagerKhachHang")
public class ManagerKhachHang extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        int itemsPerPage = 5;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }
        List<KhachHang> danhSachKhachHang = KhachHangDAO.hienThiDanhSachKhachHang();
        int totalItems = danhSachKhachHang.size();

        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
        
        List<KhachHang> currentItems = danhSachKhachHang.subList(startIndex, endIndex);

        request.setAttribute("danhSachKhachHang", currentItems);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("views/khach_hang/index.jsp").forward(request, response);
    }
}
