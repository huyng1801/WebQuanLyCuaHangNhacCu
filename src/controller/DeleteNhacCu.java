package controller;

import dao.NhacCuDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteNhacCu")
public class DeleteNhacCu extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maNhacCu = Integer.parseInt(request.getParameter("maNhacCu"));
        NhacCuDAO.xoaNhacCu(maNhacCu);
        response.sendRedirect("ManagerNhacCu");
    }
}
