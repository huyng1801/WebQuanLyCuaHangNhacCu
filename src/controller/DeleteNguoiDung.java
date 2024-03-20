package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.NguoiDungDAO;
@WebServlet("/DeleteNguoiDung")
public class DeleteNguoiDung extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String maNguoiDung = request.getParameter("maNguoiDung");

        if (maNguoiDung == null || maNguoiDung.trim().isEmpty()) {
            return;
        }

        try {
            NguoiDungDAO.xoaNguoiDung(maNguoiDung);

            response.sendRedirect("ManagerNguoiDung");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
