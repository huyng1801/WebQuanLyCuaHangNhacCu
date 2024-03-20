package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KhachHangDAO;
import model.KhachHang;

@WebServlet("/CheckPhoneNumber")
public class CheckPhoneNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getCharacterEncoding() == null)
			request.setCharacterEncoding("UTF-8");
		
		String soDienThoai = request.getParameter("soDienThoai");
		KhachHang khachHang = KhachHangDAO.timKiemKhachHangTheoSoDienThoai(soDienThoai);
		String jsonResponse;
		if (khachHang != null) {
			jsonResponse = "{\"customerExists\":true, \"tenKhachHang\":\"" + khachHang.getTenKhachHang()
					+ "\", \"diaChi\":\"" + khachHang.getDiaChi() + "\"}";
		} else {
			jsonResponse = "{\"customerExists\":false, \"tenKhachHang\":\"\", \"diaChi\":\"\"}";
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonResponse);
		out.flush();
	}
}
