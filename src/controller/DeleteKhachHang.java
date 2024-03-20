package controller;

import dao.KhachHangDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteKhachHang")
public class DeleteKhachHang extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maKhachHang = Integer.parseInt(request.getParameter("maKhachHang"));
        KhachHangDAO.xoaKhachHang(maKhachHang);

        response.sendRedirect("ManagerKhachHang");
    }
}
