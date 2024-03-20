package controller;

import dao.LoaiNhacCuDAO;
import model.LoaiNhacCu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CreateLoaiNhacCu")
public class CreateLoaiNhacCu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getCharacterEncoding() == null)
    		request.setCharacterEncoding("UTF-8");
        String tenLoaiNhacCu = request.getParameter("tenLoaiNhacCu");

        LoaiNhacCu loaiNhacCu = new LoaiNhacCu();
        loaiNhacCu.setTenLoaiNhacCu(tenLoaiNhacCu);
        LoaiNhacCuDAO.themLoaiNhacCu(loaiNhacCu);

        response.sendRedirect("ManagerLoaiNhacCu");
    }

}
