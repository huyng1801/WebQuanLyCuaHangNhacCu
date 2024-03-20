package controller;

import dao.KhachHangDAO;
import model.KhachHang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditKhachHang")
public class EditKhachHang extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding("UTF-8");
        int maKhachHang = Integer.parseInt(request.getParameter("maKhachHang"));
        String tenKhachHang = request.getParameter("tenKhachHang");
        String soDienThoai = request.getParameter("soDienThoai");
        String diaChi = request.getParameter("diaChi");
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKhachHang(maKhachHang);
        khachHang.setTenKhachHang(tenKhachHang);
        khachHang.setSoDienThoai(soDienThoai);
        khachHang.setDiaChi(diaChi);
        KhachHangDAO.suaKhachHang(khachHang);
        response.sendRedirect("ManagerKhachHang");
    }
}
