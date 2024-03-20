package controller;

import dao.NhaSanXuatDAO;
import model.NhaSanXuat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateNhaSanXuat")
public class CreateNhaSanXuat extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getCharacterEncoding() == null)
            request.setCharacterEncoding("UTF-8");

        String tenNhaSanXuat = request.getParameter("tenNhaSanXuat");
        String soDienThoai = request.getParameter("soDienThoai");
        String diaChi = request.getParameter("diaChi");

        NhaSanXuat nhaSanXuat = new NhaSanXuat();
        nhaSanXuat.setTenNhaSanXuat(tenNhaSanXuat);
        nhaSanXuat.setSoDienThoai(soDienThoai);
        nhaSanXuat.setDiaChi(diaChi);

        NhaSanXuatDAO.themNhaSanXuat(nhaSanXuat);

        response.sendRedirect("ManagerNhaSanXuat");
    }
}
