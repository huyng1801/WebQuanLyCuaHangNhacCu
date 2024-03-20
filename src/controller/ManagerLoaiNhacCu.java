package controller;

import dao.LoaiNhacCuDAO;
import model.LoaiNhacCu;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManagerLoaiNhacCu")
public class ManagerLoaiNhacCu extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int currentPage = 1;
        int itemsPerPage = 5;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }
        List<LoaiNhacCu> danhSachLoaiNhacCu = LoaiNhacCuDAO.hienThiDanhSachLoaiNhacCu();
        int totalItems = danhSachLoaiNhacCu.size();

        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int startIndex = (currentPage - 1) * itemsPerPage;

        int endIndex = Math.min(startIndex + itemsPerPage, totalItems);
        List<LoaiNhacCu> currentItems = danhSachLoaiNhacCu.subList(startIndex, endIndex);
	    request.setAttribute("danhSachLoaiNhacCu", currentItems);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("totalPages", totalPages);

	    request.getRequestDispatcher("views/loai_nhac_cu/index.jsp").forward(request, response);
	}

}