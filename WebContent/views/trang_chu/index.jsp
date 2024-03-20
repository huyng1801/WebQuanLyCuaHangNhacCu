<%@ page import="java.util.List"%>
<%@ page import="model.NhacCu"%>
<%@ page import="model.LoaiNhacCu"%>
<%@ page import="model.NhaSanXuat"%>
<%@ page import="dao.LoaiNhacCuDAO"%>
<%@ page import="dao.NhaSanXuatDAO"%>
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
					<!-- Form tìm kiếm -->
					<form action="SearchNhacCu" method="get" class="mb-3">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Nhập tên nhạc cụ" name="q">
							<button class="btn btn-primary" type="submit">
								<i class="fa-solid fa-magnifying-glass"></i>
							</button>
						</div>
					</form>

					<h2 class="text-center text-uppercase">Danh sách nhạc cụ bán chạy</h2>

			

					<!-- Table to display musical instruments -->
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Hình ảnh</th>
								<th scope="col">Mã nhạc cụ</th>
								<th scope="col">Tên nhạc cụ</th>
								<th scope="col">Loại nhạc cụ</th>
								<th scope="col">Nhà sản xuất</th>
								<th scope="col">Đơn giá</th>
								<th scope="col">Số lượng tồn</th>
								<th scope="col">Xuất xứ</th>
								<th scope="col">Năm sản xuất</th>

								<th scope="col">Số lượng bán</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<NhacCu> danhSachNhacCu = (List<NhacCu>) request.getAttribute("danhSachNhacCuBanChay");
							%>
							<%
					if (danhSachNhacCu == null || danhSachNhacCu.isEmpty()) {
					%>
						<div class="alert alert-warning" role="alert">
							Danh sách nhạc cụ bán chạy đang trống.
						</div>
					<%
					} else {
						for (NhacCu nhacCu : danhSachNhacCu) {
					%>
							<tr>
							<td class="text-center align-middle"><img
									src="<%=nhacCu.getHinhAnh()%>" alt="<%=nhacCu.getTenNhacCu()%>"
									height="60"></td>
								<td class="align-middle"><%=nhacCu.getMaNhacCu()%></td>
								<td class="align-middle"><%=nhacCu.getTenNhacCu()%></td>
								<td class="align-middle"><%=LoaiNhacCuDAO.timKiemLoaiNhacCuTheoMa(nhacCu.getLoaiNhacCu()).getTenLoaiNhacCu()%></td>
								<td class="align-middle"><%=NhaSanXuatDAO.timKiemNhaSanXuatTheoMa(nhacCu.getNhaSanXuat()).getTenNhaSanXuat()%></td>
								<td class="align-middle"><%=nhacCu.getDonGia()%></td>
								<td class="align-middle"><%=nhacCu.getSoLuongTon()%></td>
								<td class="align-middle"><%=nhacCu.getXuatXu()%></td>
								<td class="align-middle"><%=nhacCu.getNamSanXuat()%></td>
								<td class="align-middle"><%=nhacCu.getSoLuongBan()%></td>
							</tr>
					<%
						}
					}
					%>
							
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
