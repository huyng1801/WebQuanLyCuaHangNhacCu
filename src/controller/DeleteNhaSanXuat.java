package controller;

import dao.NhaSanXuatDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteNhaSanXuat")
public class DeleteNhaSanXuat extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maNhaSanXuat = Integer.parseInt(request.getParameter("maNhaSanXuat"));
        NhaSanXuatDAO.xoaNhaSanXuat(maNhaSanXuat);
        response.sendRedirect("ManagerNhaSanXuat");
    }
}
