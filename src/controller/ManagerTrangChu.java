package controller;

import dao.NhacCuDAO;
import model.NhaSanXuat;
import model.NhacCu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManagerTrangChu")
public class ManagerTrangChu extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        int itemsPerPage = 5;

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<NhacCu> danhSachNhacCuBanChay = NhacCuDAO.thongKeNhacCuBanChay();

        int totalItems = danhSachNhacCuBanChay.size();

        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

        List<NhacCu> currentItems = danhSachNhacCuBanChay.subList(startIndex, endIndex);
        request.setAttribute("danhSachNhacCuBanChay", currentItems);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("views/trang_chu/index.jsp").forward(request, response);
    }
}
