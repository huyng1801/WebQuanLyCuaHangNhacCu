<%@ page import="java.util.List"%>
<%@ page import="model.NhacCu"%>
<%@ page import="dao.NhacCuDAO"%>
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
					<div id="paymentSuccessMessage" class="alert alert-success mt-3"
						role="alert" style="display: none;">
						<button type="button" class="btn-close" data-bs-dismiss="alert"
							aria-label="Close"></button>
						Thanh toán thành công!
					</div>
					<form action="SearchNhacCu" method="get" class="mb-3">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Nhập tên nhạc cụ" name="q">
							<button class="btn btn-primary" type="submit">
								<i class="fa-solid fa-magnifying-glass"></i>
							</button>
						</div>
					</form>

					<!-- Bảng danh sách nhạc cụ -->
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Mã nhạc cụ</th>
								<th scope="col">Tên nhạc cụ</th>
								<th scope="col">Đơn giá</th>
								<th scope="col">Số lượng tồn</th>
								<th scope="col">Thao tác</th>
							</tr>
						</thead>
						<tbody id="danhSachNhacCu">
							<%
							List<NhacCu> danhSachNhacCu = (List<NhacCu>) request.getAttribute("danhSachNhacCu");
							for (NhacCu nhacCu : danhSachNhacCu) {
							%>
							<tr>
								<td><%=nhacCu.getMaNhacCu()%></td>
								<td><%=nhacCu.getTenNhacCu()%></td>
								<td><%= String.format("%,d ", nhacCu.getDonGia()).replace(",", ".") %><u>đ</u></td>
								<td><%=nhacCu.getSoLuongTon()%></td>
								<td>
									<button type="button" class="btn btn-success"
										data-bs-toggle="modal" data-bs-target="#themVaoHoaDonModal"
										data-ma="<%=nhacCu.getMaNhacCu()%>"
										data-ten="<%=nhacCu.getTenNhacCu()%>"
										data-dongia="<%=nhacCu.getDonGia()%>"
										data-soluongton="<%=nhacCu.getSoLuongTon()%>">
										<i class="fa-solid fa-plus"></i> Thêm vào hóa đơn
									</button>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>

					<!-- Modal thêm vào hóa đơn -->
					<div class="modal fade" id="themVaoHoaDonModal" tabindex="-1"
						aria-labelledby="themVaoHoaDonModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="themVaoHoaDonModalLabel">Thêm
										vào hóa đơn</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<form id="themVaoHoaDonForm">
										<div class="mb-3">
											<label for="soLuong">Số lượng:</label>
											<div id="error-message" class="text-danger"></div>

											<input type="number" class="form-control" id="soLuong"
												name="soLuong" min="1" required> <input
												type="hidden" id="maNhacCuHidden" name="maNhacCuHidden">
											<input type="hidden" id="tenNhacCuHidden"
												name="tenNhacCuHidden"> <input type="hidden"
												id="donGiaHidden" name="donGiaHidden"> <input
												type="hidden" id="soLuongTonHidden" name="soLuongTonHidden">
										</div>
										<button type="button" class="btn btn-success"
											onclick="themVaoHoaDonTuModal()">Thêm vào hóa đơn</button>
									</form>
								</div>
							</div>
						</div>
					</div>

					<%@ include file="../partialview/pagination.jsp"%>

					<!-- Bảng chi tiết hóa đơn -->
					<table class="table">
						<thead>
							<tr>
								<th scope="col">Mã nhạc cụ</th>
								<th scope="col">Tên nhạc cụ</th>
								<th scope="col">Đơn giá</th>
								<th scope="col">Số lượng</th>
								<th scope="col">Thành tiền</th>
								<th scope="col">Thao tác</th>
							</tr>
						</thead>
						<tbody id="chiTietHoaDon">
							<!-- Chi tiết hóa đơn sẽ được cập nhật bằng JavaScript -->
						</tbody>
					</table>
					<div id="tongTien" class="alert alert-info" role="alert">
						Tổng tiền: <span id="tongTienValue">0 <u>đ</u></span>
					</div>

				<div class="mb-3 d-flex justify-content-end">
    <button type="button" class="btn btn-primary" onclick="thanhToan()">
        <i class="fa-solid fa-money-bill"></i> Thanh toán
    </button>
				</div>	


				</div>
				<!-- Error Message Modal -->
<div class="modal fade" id="errorMessageModal" tabindex="-1" aria-labelledby="errorMessageModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="errorMessageModalLabel">Thông báo lỗi</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="errorMessageContainer"><div class="alert alert-danger" role="alert">Hóa đơn trống!<br>
                 Vui lòng thêm ít nhất một sản phẩm vào hóa đơn trước khi thanh toán.
        </div></div>
            </div>
        </div>
    </div>
</div>
				
			</main>
		</div>
	</div>
	<!-- Add this modal to your existing HTML -->
	<div class="modal fade" id="thongTinKhachHangModal" tabindex="-1"
		aria-labelledby="thongTinKhachHangModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="thongTinKhachHangModalLabel">Thông
						tin khách hàng</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="XacNhanDonHang" method="post">
						<input type="hidden" id="orderDetails" name="orderDetails">
						<div class="mb-3">
							<label for="soDienThoai">Số điện thoại:</label> <input
								type="text" class="form-control" id="soDienThoai"
								name="soDienThoai" required maxlength="10">
						</div>
						<div class="mb-3">
							<label for="tenKhachHang">Tên khách hàng:</label> <input
								type="text" class="form-control" id="tenKhachHang"
								name="tenKhachHang" required readonly>
						</div>
						<div class="mb-3">
							<label for="diaChi">Địa chỉ:</label> <input type="text"
								class="form-control" id="diaChi" name="diaChi" required readonly>
						</div>
						<button type="button" class="btn btn-primary"
							onclick="thanhToanAjax()">Xác nhận</button>

					</form>
					<!-- Add this within your HTML body -->


				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
		integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
		integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
		crossorigin="anonymous"></script>



	<script>
        var chiTietHoaDon = []; 
        function themVaoHoaDonTuModal() {
            var maNhacCu = document.getElementById('maNhacCuHidden').value;
            var tenNhacCu = document.getElementById('tenNhacCuHidden').value;
            var donGia = document.getElementById('donGiaHidden').value;
            var soLuong = document.getElementById('soLuong').value;

            var soLuongTon = parseInt(document.getElementById('soLuongTonHidden').value);
            if (parseInt(soLuong) > soLuongTon) {
                document.getElementById('error-message').innerHTML = 'Số lượng vượt quá số lượng tồn!';
                return;
            } else {
                document.getElementById('error-message').innerHTML = '';
            }

            var index = chiTietHoaDon.findIndex(item => item.maNhacCu === maNhacCu);

            if (index === -1) {
                chiTietHoaDon.push({
                    maNhacCu: maNhacCu,
                    tenNhacCu: tenNhacCu,
                    donGia: donGia,
                    soLuong: parseInt(soLuong)
                });
            } else {
                chiTietHoaDon[index].soLuong += parseInt(soLuong);
            }

            $('#themVaoHoaDonModal').modal('hide');

            capNhatBangChiTietHoaDon();
        }

        $('#themVaoHoaDonModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget);
            var maNhacCu = button.data('ma');
            var tenNhacCu = button.data('ten');
            var donGia = button.data('dongia');
            var soLuongTon = button.data('soluongton');

            var modal = $(this);
            modal.find('.modal-title').text('Thêm vào hóa đơn - ' + tenNhacCu);
            modal.find('#maNhacCuHidden').val(maNhacCu);
            modal.find('#tenNhacCuHidden').val(tenNhacCu);
            modal.find('#donGiaHidden').val(donGia);
            modal.find('#soLuongTonHidden').val(soLuongTon);
            modal.find('#soLuong').attr('max', soLuongTon);
            modal.find('#soLuong').val(1); 
        });

        function capNhatBangChiTietHoaDon() {
            var tbody = document.getElementById('chiTietHoaDon');
            tbody.innerHTML = '';

            var tongTien = 0;

            for (var i = 0; i < chiTietHoaDon.length; i++) {
                var chiTiet = chiTietHoaDon[i];

                var row = tbody.insertRow(i);
                var cellMaNhacCu = row.insertCell(0);
                var cellTenNhacCu = row.insertCell(1);
                var cellDonGia = row.insertCell(2);
                var cellSoLuong = row.insertCell(3);
                var cellThanhTien = row.insertCell(4);
                var cellXoa = row.insertCell(5);

                cellMaNhacCu.innerHTML = chiTiet.maNhacCu;
                cellTenNhacCu.innerHTML = chiTiet.tenNhacCu;
                cellDonGia.innerHTML = parseFloat(chiTiet.donGia).toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                cellSoLuong.innerHTML = chiTiet.soLuong;

                var thanhTien = chiTiet.donGia * chiTiet.soLuong;
                cellThanhTien.innerHTML = thanhTien.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                tongTien += thanhTien;

                var btnXoa = document.createElement('button');
                btnXoa.innerHTML = '<i class="fa-solid fa-trash"></i>';
                btnXoa.className = 'btn btn-danger btn-sm';
                btnXoa.onclick = function () {
                    xoaKhoiHoaDon(chiTiet.maNhacCu);
                };
                cellXoa.appendChild(btnXoa);
            }
            
            document.getElementById('orderDetails').value = JSON.stringify(chiTietHoaDon);
            var tongTienElement = document.getElementById('tongTienValue');
            tongTienElement.textContent = tongTien.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
        }


        function xoaKhoiHoaDon(maNhacCu) {
            chiTietHoaDon = chiTietHoaDon.filter(item => item.maNhacCu !== maNhacCu);
            capNhatBangChiTietHoaDon();
        }

        function thanhToan() {
            if (chiTietHoaDon.length > 0) {
                $('#thongTinKhachHangModal').modal('show');
            } else {
            	 var errorMessageContainer = $('#errorMessageContainer');
            	 $('#errorMessageModal').modal('show');
            }
        }
        function thanhToanAjax() {
            if (chiTietHoaDon.length > 0) {
                $.ajax({
                    type: 'POST',
                    url: 'XacNhanDonHang',
                    data: {
                        soDienThoai: $('#soDienThoai').val(),
                        tenKhachHang: $('#tenKhachHang').val(),
                        diaChi: $('#diaChi').val(),
                        orderDetails: JSON.stringify(chiTietHoaDon)
                    },
                    success: function (response) {
                        try {
                            var responseData = JSON.parse(response);
                            console.log(responseData);
                            if (responseData.success) {
                                $('#thongTinKhachHangModal').modal('hide');
                                var paymentSuccessMessage = document.getElementById('paymentSuccessMessage');
                                paymentSuccessMessage.style.display = 'block';
                                
                                chiTietHoaDon = []; 
                                setTimeout(function () {
                                	  paymentSuccessMessage.style.display = 'none';
                                    window.location.reload();
                                    window.location.href = 'ManagerHoaDon';
                        
                                   
                                }, 3000);
                                capNhatBangChiTietHoaDon(); 

                            } else {
                                console.log('Error: ' + responseData.message);
                            }
                        } catch (error) {
                            console.log('Error parsing JSON response: ' + error);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log('AJAX Error: ' + textStatus + ' - ' + errorThrown);
                    }
                });
            } else {
           	 var errorMessageContainer = $('#errorMessageContainer');
        	 $('#errorMessageModal').modal('show');
            }
        }
        $(document).ready(function () {
            $('#soDienThoai').on('input', function () {
                var soDienThoai = $(this).val();
                $.ajax({
                    type: 'GET',
                    url: 'CheckPhoneNumber', 
                    data: { soDienThoai: soDienThoai },
                    success: function (response) {
                        if (response.customerExists) {
                            $('#tenKhachHang').val(response.tenKhachHang);
                            $('#diaChi').val(response.diaChi);
                        } else {
                            $('#tenKhachHang').val(''); 
                            $('#diaChi').val('');    
                        }
                    },
                    error: function () {
                        console.log('Error checking phone number. Please try again.');
                    }
                });
            });
        });


        function inHoaDon() {
        }

        function tangSoLuong(index) {
            chiTietHoaDon[index].soLuong++;
            capNhatBangChiTietHoaDon();
        }

        function giamSoLuong(index) {
            if (chiTietHoaDon[index].soLuong > 1) {
                chiTietHoaDon[index].soLuong--;
                capNhatBangChiTietHoaDon();
            }
        }
    </script>

</body>
</html>
