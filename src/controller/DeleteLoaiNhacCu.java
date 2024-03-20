package controller;

import dao.LoaiNhacCuDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteLoaiNhacCu")
public class DeleteLoaiNhacCu extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maLoaiNhacCu = Integer.parseInt(request.getParameter("maLoaiNhacCu"));
        LoaiNhacCuDAO.xoaLoaiNhacCu(maLoaiNhacCu);

        response.sendRedirect("ManagerLoaiNhacCu");
    }
}