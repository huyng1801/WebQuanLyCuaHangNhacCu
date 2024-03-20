package controller;

import dao.NhacCuDAO;
import model.NhacCu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/SearchNhacCu")
public class SearchNhacCu extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding("UTF-8");

        String tenNhacCu = request.getParameter("q");

        int itemsPerPage = 5;

        int currentPage = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }

        List<NhacCu> danhSachNhacCu = new ArrayList<>();

        if (tenNhacCu != null && !tenNhacCu.isEmpty()) {
            danhSachNhacCu = NhacCuDAO.timKiemNhacCuTheoTen(tenNhacCu);
        }

        int totalItems = danhSachNhacCu.size();

        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int startIndex = (currentPage - 1) * itemsPerPage;

        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);

        List<NhacCu> currentItems = danhSachNhacCu.subList(startIndex, endIndex);

        request.setAttribute("danhSachNhacCu", currentItems);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        request.getRequestDispatcher("views/nhac_cu/index.jsp").forward(request, response);
    }
}
