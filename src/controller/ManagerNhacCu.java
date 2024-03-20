package controller;

import dao.LoaiNhacCuDAO;
import dao.NhaSanXuatDAO;
import dao.NhacCuDAO;
import model.LoaiNhacCu;
import model.NhaSanXuat;
import model.NhacCu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@WebServlet("/ManagerNhacCu")
public class ManagerNhacCu extends HttpServlet {
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

        List<LoaiNhacCu> danhSachLoaiNhacCu = LoaiNhacCuDAO.hienThiDanhSachLoaiNhacCu();

        List<NhaSanXuat> danhSachNhaSanXuat = NhaSanXuatDAO.hienThiDanhSachNhaSanXuat();


        request.setAttribute("danhSachNhacCu", currentItems);
        request.setAttribute("danhSachLoaiNhacCu", danhSachLoaiNhacCu);
        request.setAttribute("danhSachNhaSanXuat", danhSachNhaSanXuat);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("views/nhac_cu/index.jsp").forward(request, response);
    }
}
