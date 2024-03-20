package controller;

import dao.NguoiDungDAO;
import model.NguoiDung;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManagerNguoiDung")
public class ManagerNguoiDung extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currentPage = 1;
        int itemsPerPage = 5;

        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<NguoiDung> danhSachNguoiDung = NguoiDungDAO.hienThiDanhSachNguoiDung();

        int totalItems = danhSachNguoiDung.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

        List<NguoiDung> currentItems = danhSachNguoiDung.subList(startIndex, endIndex);
        request.setAttribute("danhSachNguoiDung", currentItems);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("views/nguoi_dung/index.jsp").forward(request, response);
    }

}
