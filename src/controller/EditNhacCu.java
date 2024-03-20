package controller;

import dao.NhacCuDAO;
import model.NhacCu;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet("/EditNhacCu")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class EditNhacCu extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCharacterEncoding() == null)
            request.setCharacterEncoding("UTF-8");

        int maNhacCu = Integer.parseInt(request.getParameter("maNhacCu"));
        String tenNhacCu = request.getParameter("tenNhacCu");
        int loaiNhacCu = Integer.parseInt(request.getParameter("loaiNhacCu"));
        long donGia = Long.parseLong(request.getParameter("donGia"));
        int soLuongTon = Integer.parseInt(request.getParameter("soLuongTon"));
        String xuatXu = request.getParameter("xuatXu");
        int nhaSanXuat = Integer.parseInt(request.getParameter("nhaSanXuat"));
        int namSanXuat = Integer.parseInt(request.getParameter("namSanXuat"));
        String moTa = request.getParameter("moTa");

        Part filePart = request.getPart("hinhAnh");
        String fileName = getSubmittedFileName(filePart);
        
        String savePath = getServletContext().getRealPath("/") + "/" + "views" + "/" + "assets" + "/" + "images" + "/" + fileName;

        Path parentDir = Path.of(savePath).getParent();
        if (!Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        filePart.write(savePath);
        String contextPath = getServletContext().getContextPath() + "/" + "views" + "/" + "assets" + "/" + "images" + "/" + fileName;

        NhacCu nhacCu = new NhacCu();
        nhacCu.setMaNhacCu(maNhacCu);
        nhacCu.setTenNhacCu(tenNhacCu);
        nhacCu.setLoaiNhacCu(loaiNhacCu);
        nhacCu.setDonGia(donGia);
        nhacCu.setSoLuongTon(soLuongTon);
        nhacCu.setXuatXu(xuatXu);
        nhacCu.setNhaSanXuat(nhaSanXuat);
        nhacCu.setNamSanXuat(namSanXuat);
        nhacCu.setMoTa(moTa);
        nhacCu.setHinhAnh(contextPath); 
        NhacCuDAO.suaNhacCu(nhacCu);

        response.sendRedirect("ManagerNhacCu");
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

