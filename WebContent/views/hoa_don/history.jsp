<%@ page import="java.util.List" %>
<%@ page import="model.HoaDon" %>
<%@ page import="dao.ChiTietHoaDonDAO"%>
<%@ page import="dao.KhachHangDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<html>
<%@ include file="../partialview/header.jsp"%>
<body>
	<div class="container-fluid">
		<div class="row full-vh">
			<%@ include file="../partialview/sidebar.jsp"%>

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div class="container mt-5">
				

					<h2 class="text-center text-uppercase">Lịch sử hóa đơn</h2>

			

					<!-- Table to display musical instruments -->
					 <table class="table">
            <thead>
                <tr>
                    <th scope="col">Mã Hóa Đơn</th>
                    <th scope="col">Mã Khách Hàng</th>
                    <th scope="col">Ngày Lập</th>
                     <th scope="col">Thao tác</th>
                    <!-- Add more columns if needed -->
                </tr>
            </thead>
            <tbody>
                <% List<HoaDon> danhSachHoaDon = (List<HoaDon>) request.getAttribute("danhSachHoaDon"); %>
                <% for (HoaDon hoaDon : danhSachHoaDon) { %>
                    <tr>
                        <td><%= hoaDon.getMaHoaDon() %></td>
                        <td><%= KhachHangDAO.timKiemKhachHangTheoMa(hoaDon.getMaKhachHang()).getTenKhachHang() %></td>
                        <td><%= hoaDon.getNgayLap() %></td>
                        <td>
                    <a href="XemChiTietHoaDon?maHoaDon=<%= hoaDon.getMaHoaDon() %>" class="btn btn-info btn-sm">
                        <i class="fa-solid fa-eye"></i> Xem chi tiết
                    </a>
                    <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#xoaModal<%= hoaDon.getMaHoaDon() %>">
                        <i class="fa-solid fa-trash"></i> Xóa
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" id="xoaModal<%= hoaDon.getMaHoaDon() %>" tabindex="-1" aria-labelledby="xoaModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="xoaModalLabel">Xác nhận xóa</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Bạn có chắc muốn xóa hóa đơn này không?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                    <a href="XoaHoaDon?maHoaDon=<%= hoaDon.getMaHoaDon() %>" class="btn btn-danger">Xóa</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
                        <!-- Add more cells based on your HoaDon model -->
                    </tr>
                <% } %>
            </tbody>
        </table>
					<%@ include file="../partialview/pagination.jsp"%>

				
				</div>
			</main>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
