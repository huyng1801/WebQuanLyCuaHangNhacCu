package controller;

import dao.NhaSanXuatDAO;
import model.NhaSanXuat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/SearchNhaSanXuat")
public class SearchNhaSanXuat extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding("UTF-8");

        String tenNhaSanXuat = request.getParameter("q");

        int itemsPerPage = 5;

        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<NhaSanXuat> danhSachNhaSanXuat = NhaSanXuatDAO.timKiemNhaSanXuatTheoTen(tenNhaSanXuat);
        
        int totalItems = danhSachNhaSanXuat.size();

        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int startIndex = (currentPage - 1) * itemsPerPage;

        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

        List<NhaSanXuat> currentItems = danhSachNhaSanXuat.subList(startIndex, endIndex);

        request.setAttribute("danhSachNhaSanXuat", currentItems);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        request.getRequestDispatcher("views/nha_san_xuat/index.jsp").forward(request, response);
    }
}
