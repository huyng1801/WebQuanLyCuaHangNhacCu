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

@WebServlet("/CreateNguoiDung")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CreateNguoiDung extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set character encoding
        request.setCharacterEncoding("UTF-8");

        // Retrieve form data
        String maNguoiDung = request.getParameter("maNguoiDung");
        String tenNguoiDung = request.getParameter("tenNguoiDung");
        String matKhau = request.getParameter("matKhau");

        // Validate inputs
        if (maNguoiDung == null || maNguoiDung.trim().isEmpty() || tenNguoiDung == null || tenNguoiDung.trim().isEmpty() ||
            matKhau == null || matKhau.trim().isEmpty()) {
            return;
        }

        Part filePart = request.getPart("hinhAnh");
        String fileName = getSubmittedFileName(filePart);

        if (fileName == null || fileName.trim().isEmpty()) {
            response.sendRedirect("ErrorPage");
            return;
        }

        String uploadDirectory = getServletContext().getRealPath("/") + File.separator + "uploads";
        String savePath = uploadDirectory + File.separator + fileName;

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
            NguoiDungDAO.themNguoiDung(nguoiDung);
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
