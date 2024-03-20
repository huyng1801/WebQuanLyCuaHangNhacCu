package controller;

import dao.NguoiDungDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/DangNhap")
public class DangNhap extends HttpServlet {
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        request.getRequestDispatcher("views/index.jsp").forward(request, response);
	    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenDangNhap = request.getParameter("tenDangNhap");
        String matKhau = request.getParameter("matKhau");

        if (NguoiDungDAO.dangNhap(tenDangNhap, matKhau)) {
            HttpSession session = request.getSession();
            session.setAttribute("tenDangNhap", tenDangNhap);
            response.sendRedirect("ManagerLoaiNhacCu"); // You can change the destination URL

        } else {
            request.setAttribute("errorMessage", "Invalid login credentials");
            request.getRequestDispatcher("DangNhap").forward(request, response);
        }
    }
}
