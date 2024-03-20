<%@ page import="java.util.List" %>
<%@ page import="model.HoaDon" %>
<%@ page import="model.ChiTietHoaDon" %>
<%@ page import="dao.NhacCuDAO"%>
<%@ page import="dao.KhachHangDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<%@ include file="../partialview/header.jsp"%>
<body>
  	<div class="container-fluid">
		<div class="row full-vh">
			<%@ include file="../partialview/sidebar.jsp"%>
	<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div class="container mt-5">
				
        <h2 class="text-center text-uppercase">Chi Tiết Hóa Đơn</h2>

        <% HoaDon hoaDon = (HoaDon) request.getAttribute("hoaDon"); %>
        <h5>Mã Hóa Đơn: <%= hoaDon.getMaHoaDon() %></h5>
         <h5>Ngày Lập: <%= hoaDon.getNgayLap() %></h5>
        <h5>Tên khách hàng: <%= KhachHangDAO.timKiemKhachHangTheoMa(hoaDon.getMaKhachHang()).getTenKhachHang() %></h5>
        <h5>Địa chỉ: <%= KhachHangDAO.timKiemKhachHangTheoMa(hoaDon.getMaKhachHang()).getDiaChi() %></h5>
         <h5>Số điện thoại: <%= KhachHangDAO.timKiemKhachHangTheoMa(hoaDon.getMaKhachHang()).getSoDienThoai() %></h5>
       

        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Mã chi tiết hóa đơn</th>
                    <th scope="col">Tên nhạc cụ</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Đơn giá</th>
                    <th scope="col">Thành tiền</th>
                </tr>
            </thead>
            <tbody>
                <% List<ChiTietHoaDon> chiTietHoaDonList = (List<ChiTietHoaDon>) request.getAttribute("chiTietHoaDonList"); %>
                <% for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDonList) { %>
                    <tr>
                        <td><%= chiTietHoaDon.getId() %></td>
                        <td><%= NhacCuDAO.timKiemNhacCuTheoMa(chiTietHoaDon.getMaNhacCu()).getTenNhacCu() %></td>
                       <td><%= chiTietHoaDon.getSoLuong() %></td>
                        <td><%= NhacCuDAO.timKiemNhacCuTheoMa(chiTietHoaDon.getMaNhacCu()).getDonGia() %></td>
                        <td><%= chiTietHoaDon.getSoLuong() * NhacCuDAO.timKiemNhacCuTheoMa(chiTietHoaDon.getMaNhacCu()).getDonGia() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
            </div>
            </main>
    </div>
</div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
