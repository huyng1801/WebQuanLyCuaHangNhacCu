package controller;

import dao.LoaiNhacCuDAO;
import model.LoaiNhacCu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditLoaiNhacCu")
public class EditLoaiNhacCu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getCharacterEncoding() == null)
    		request.setCharacterEncoding("UTF-8");
        int maLoaiNhacCu = Integer.parseInt(request.getParameter("maLoaiNhacCu"));
        String tenLoaiNhacCu = request.getParameter("tenLoaiNhacCu");
        LoaiNhacCu loaiNhacCu = new LoaiNhacCu();
        loaiNhacCu.setMaLoaiNhacCu(maLoaiNhacCu);
        loaiNhacCu.setTenLoaiNhacCu(tenLoaiNhacCu);
        LoaiNhacCuDAO.suaLoaiNhacCu(loaiNhacCu);
        response.sendRedirect("ManagerLoaiNhacCu");
    }

}