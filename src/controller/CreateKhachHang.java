package controller;

import dao.KhachHangDAO;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateKhachHang")
public class CreateKhachHang extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding("UTF-8");

        String tenKhachHang = request.getParameter("tenKhachHang");
        String soDienThoai = request.getParameter("soDienThoai");
        String diaChi = request.getParameter("diaChi");

        KhachHang khachHang = new KhachHang();
        khachHang.setTenKhachHang(tenKhachHang);
        khachHang.setSoDienThoai(soDienThoai);
        khachHang.setDiaChi(diaChi);

        KhachHangDAO.themKhachHang(khachHang);

        response.sendRedirect("ManagerKhachHang");
    }
}
