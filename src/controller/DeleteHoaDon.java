package controller;

import dao.HoaDonDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteHoaDon")
public class DeleteHoaDon extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int maHoaDon = Integer.parseInt(request.getParameter("maHoaDon"));
            HoaDonDAO.xoaHoaDon(maHoaDon);
            response.sendRedirect("XemHoaDon");
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid HoaDon ID.");
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
