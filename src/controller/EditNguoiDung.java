package controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.NguoiDungDAO;
import model.NguoiDung;
@WebServlet("/EditNguoiDung")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class EditNguoiDung extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String maNguoiDung = request.getParameter("maNguoiDung");
        String tenNguoiDung = request.getParameter("tenNguoiDung");
        String matKhau = request.getParameter("matKhau");
        Part filePart = request.getPart("hinhAnh");
        String fileName = getSubmittedFileName(filePart);

        if (fileName == null || fileName.trim().isEmpty()) {
            return;
        }

        String savePath = getServletContext().getRealPath("/") + "/" + "views" + "/" + "assets" + "/" + "images" + "/" + fileName;
        try {
            Path parentDir = Path.of(savePath).getParent();
            if (!Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
            filePart.write(savePath);
        } catch (IOException e) {
            return;
        }

        String contextPath = getServletContext().getContextPath() + "/" + "views" + "/" + "assets" + "/" + "images" + "/" + fileName;
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setMaNguoiDung(maNguoiDung);
        nguoiDung.setTenNguoiDung(tenNguoiDung);
        nguoiDung.setMatKhau(matKhau);
        nguoiDung.setHinhAnh(contextPath);
        try {
            if(matKhau.equals(""))
            	NguoiDungDAO.capNhatNguoiDungKhongMatKhau(nguoiDung);
            else
            	NguoiDungDAO.capNhatNguoiDung(nguoiDung);
        } catch (Exception e) {
            return;
        }
        response.sendRedirect("ManagerNguoiDung");
    }

    private String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
