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

					<h2 class="text-center text-uppercase">Danh sách nhạc cụ</h2>

					<!-- Nút mở form thêm/sửa -->
					<button type="button" class="btn btn-success"
						onclick="openForm('popupForm', null, null, null, null, null, null, null, null, null, null)">
						<i class="fa-solid fa-plus"></i> Thêm
					</button>

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

								<th scope="col">Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<NhacCu> danhSachNhacCu = (List<NhacCu>) request.getAttribute("danhSachNhacCu");
							%>
							<%
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
								<td class="align-middle">
									<button type="button" class="btn btn-primary btn-sm"
										onclick="openForm('popupForm', <%=nhacCu.getMaNhacCu()%>, '<%=nhacCu.getTenNhacCu()%>',
							    <%=nhacCu.getLoaiNhacCu()%>, <%=nhacCu.getNhaSanXuat()%>, <%=nhacCu.getDonGia()%>,
							    <%=nhacCu.getSoLuongTon()%>, '<%=nhacCu.getXuatXu()%>', <%=nhacCu.getNamSanXuat()%>,
							    '<%=nhacCu.getMoTa()%>', '<%=nhacCu.getHinhAnh()%>' )">
										<i class="fa-solid fa-pen-to-square"></i>

									</button>


									<button type="button" class="btn btn-danger btn-sm"
										data-bs-toggle="modal"
										data-bs-target="#xoaModal<%=nhacCu.getMaNhacCu()%>">
										<i class="fa-solid fa-trash"></i>
									</button> <!-- Modal -->
									<div class="modal fade" id="xoaModal<%=nhacCu.getMaNhacCu()%>"
										tabindex="-1" aria-labelledby="xoaModalLabel"
										aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="xoaModalLabel">Xác nhận
														xóa</h5>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<div class="modal-body">Bạn có chắc muốn xóa nhà sản
													xuất này không?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Đóng</button>
													<a
														href="DeleteNhaSanXuat?maNhaSanXuat=<%=nhacCu.getMaNhacCu()%>"
														class="btn btn-danger">Xóa</a>
												</div>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>

					</table>

					<%@ include file="../partialview/pagination.jsp"%>

					<!-- Modal for create/edit form -->
					<div id="popupForm" class="modal" tabindex="-1">
						<div class="modal-dialog">
							<div class="modal-content">
								<!-- Modal content for creating/editing -->
								<div class="modal-header">
									<h2 id="formTitle" class="text-center text-uppercase">Thêm
										nhạc cụ</h2>
									<button type="button" class="btn-close"
										onclick="closeForm('popupForm')"></button>
								</div>
								<div class="modal-body">
									<form id="myForm" method="post" accept-charset="UTF-8"
										enctype="multipart/form-data">
										<div class="mb-3">
											<label for="tenNhacCu" class="form-label">Tên nhạc
												cụ:</label> <input type="text" class="form-control" id="tenNhacCu"
												name="tenNhacCu" required maxlength="50">
										</div>
										<div class="mb-3">
											<label for="loaiNhacCu" class="form-label">Loại nhạc
												cụ:</label> <select class="form-select" id="loaiNhacCu"
												name="loaiNhacCu" required>
												<%
												List<LoaiNhacCu> danhSachLoaiNhacCu = (List<LoaiNhacCu>) request.getAttribute("danhSachLoaiNhacCu");
												%>
												<%
												for (LoaiNhacCu loaiNhacCu : danhSachLoaiNhacCu) {
												%>
												<option value="<%=loaiNhacCu.getMaLoaiNhacCu()%>"><%=loaiNhacCu.getTenLoaiNhacCu()%></option>
												<%
												}
												%>
											</select>
										</div>
										<div class="mb-3">
											<label for="nhaSanXuat" class="form-label">Nhà sản
												xuất:</label> <select class="form-select" id="nhaSanXuat"
												name="nhaSanXuat" required>
												<%
												List<NhaSanXuat> danhSachNhaSanXuat = (List<NhaSanXuat>) request.getAttribute("danhSachNhaSanXuat");
												%>
												<%
												for (NhaSanXuat nhaSanXuat : danhSachNhaSanXuat) {
												%>
												<option value="<%=nhaSanXuat.getMaNhaSanXuat()%>"><%=nhaSanXuat.getTenNhaSanXuat()%></option>
												<%
												}
												%>
											</select>
										</div>
										<div class="mb-3">
											<label for="donGia" class="form-label">Đơn giá:</label> <input
												type="number" class="form-control" id="donGia" name="donGia"
												required maxlength="10">
										</div>
										<div class="mb-3">
											<label for="soLuongTon" class="form-label">Số lượng
												tồn:</label> <input type="number" class="form-control" id="soLuongTon"
												name="soLuongTon" required>
										</div>
										<div class="mb-3">
											<label for="xuatXu" class="form-label">Xuất xứ:</label> <input
												type="text" class="form-control" id="xuatXu" name="xuatXu"
												required>
										</div>
										<div class="mb-3">
											<label for="namSanXuat" class="form-label">Năm sản
												xuất:</label> <input type="number" class="form-control"
												id="namSanXuat" name="namSanXuat" required>
										</div>
										<div class="mb-3">
											<label for="moTa" class="form-label">Mô tả:</label>
											<textarea class="form-control" id="moTa" name="moTa" required></textarea>
										</div>
										<div class="mb-3">
											<label for="hinhAnh" class="form-label">Hình ảnh:</label>
											<!-- Thêm vào thẻ input cho hinhAnh -->
											<input type="file" class="form-control" id="hinhAnh"
												name="hinhAnh" accept="image/*"
												onchange="previewImage(this)" required> <img
												id="preview" class="img-fluid" alt="Preview Image"
												ondragover="handleDragOver(event)"
												ondrop="handleDrop(event)"
												ondragleave="handleDragLeave(event)">

										</div>

										<!-- Add more fields as needed -->
										<input type="hidden" id="maNhacCu" name="maNhacCu" value="">
										<button type="submit" class="btn btn-primary">
											<i class="fa-solid fa-floppy-disk"></i> Lưu
										</button>
										<button type="button" class="btn btn-secondary"
											onclick="closeForm('popupForm')">
											<i class="fa-solid fa-xmark"></i> Hủy
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        function openForm(formId, maNhacCu, tenNhacCu, loaiNhacCu, nhaSanXuat, donGia, soLuongTon, xuatXu, namSanXuat, moTa, hinhAnh) {
            var form = document.getElementById(formId);
            var formTitle = document.getElementById('formTitle');
            var maNhacCuInput = document.getElementById('maNhacCu');
            var tenNhacCuInput = document.getElementById('tenNhacCu');
            var loaiNhacCuSelect = document.getElementById('loaiNhacCu');
            var nhaSanXuatSelect = document.getElementById('nhaSanXuat');
            var donGiaInput = document.getElementById('donGia');
            var soLuongTonInput = document.getElementById('soLuongTon');
            var xuatXuInput = document.getElementById('xuatXu');
            var namSanXuatInput = document.getElementById('namSanXuat');
            var moTaInput = document.getElementById('moTa');
            var hinhAnhInput = document.getElementById('hinhAnh');
            var formAction = document.getElementById('formAction');
            var selectedImage = document.getElementById('selectedImage');
            var preview = document.getElementById('preview');
       

            if (maNhacCu !== null) {
             
                formTitle.innerHTML = 'Cập nhật nhạc cụ';
             
                var tenNhacCu = tenNhacCu;
         
                tenNhacCuInput.value = tenNhacCu;
                setSelectedValue(loaiNhacCuSelect, loaiNhacCu);
                setSelectedValue(nhaSanXuatSelect, nhaSanXuat);
                donGiaInput.value = donGia;
                soLuongTonInput.value = soLuongTon;
                maNhacCuInput.value = maNhacCu;
                xuatXuInput.value = xuatXu;
                namSanXuatInput.value = namSanXuat;
                moTaInput.value = moTa;
            
               
                if (hinhAnh) {
                	 preview.src = hinhAnh
                    var filename = hinhAnh.split("/").pop();
                    fetch(hinhAnh)
                        .then(response => response.blob())
                        .then(blob => {
                            const myFile = new File([blob], filename, {
                                type: 'image/png', 
                                lastModified: new Date()
                            });

                            const dataTransfer = new DataTransfer();
                            dataTransfer.items.add(myFile);

                            hinhAnhInput.files = dataTransfer.files;
                        })
                        .catch(error => {
                            console.error('Error fetching image:', error);
                        });
                } 

                document.getElementById('myForm').action  = 'EditNhacCu';
            } else {
                formTitle.innerHTML = 'Thêm nhạc cụ';
                tenNhacCuInput.value = '';
                loaiNhacCuSelect.value = '';
                nhaSanXuatSelect.value = '';
                donGiaInput.value = '';
                soLuongTonInput.value = '';
                maNhacCuInput.value = '';
                preview.src = '';
                document.getElementById('myForm').action = 'CreateNhacCu';
            }

            form.style.display = 'block';
            document.body.classList.add('modal-open'); 
            var modalBackdrop = document.createElement('div');
            modalBackdrop.className = 'modal-backdrop fade show';
            document.body.appendChild(modalBackdrop);
        }

        function closeForm(formId) {
            var form = document.getElementById(formId);
            form.style.display = 'none';
            document.body.classList.remove('modal-open');
            var modalBackdrop = document.querySelector('.modal-backdrop');
            if (modalBackdrop) {
                modalBackdrop.parentNode.removeChild(modalBackdrop);
            }
        }




        function setSelectedValue(selectObj, valueToSet) {
        	
            for (var i = 0; i < selectObj.options.length; i++) {
            	
                if (selectObj.options[i].value == valueToSet) {
                    selectObj.options[i].selected = true;
                    return;
                }
            }
        }
        function previewImage(input) {
            var preview = document.getElementById('preview');
            
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    preview.src = e.target.result;
                };

                reader.readAsDataURL(input.files[0]);
            } else {
                preview.src = '';
            }
        }
        function handleDragOver(event) {
            event.preventDefault();
            event.dataTransfer.dropEffect = 'copy';

            // Add the 'dragover' class to #preview during dragover
            document.getElementById('preview').classList.add('dragover');
        }

        function handleDrop(event) {
            event.preventDefault();

            var hinhAnhInput = document.getElementById('hinhAnh');
            var preview = document.getElementById('preview');

            // Remove the 'dragover' class from #preview after drop
            preview.classList.remove('dragover');

            if (event.dataTransfer.items) {
                for (var i = 0; i < event.dataTransfer.items.length; i++) {
                    if (event.dataTransfer.items[i].type.indexOf('image') !== -1) {
                        var file = event.dataTransfer.items[i].getAsFile();
                        handleFile(file);
                    }
                }
            } else {
                for (var i = 0; i < event.dataTransfer.files.length; i++) {
                    var file = event.dataTransfer.files[i];
                    handleFile(file);
                }
            }

            function handleFile(file) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    preview.src = e.target.result;
                };

                reader.readAsDataURL(file);

                var dataTransfer = new DataTransfer();
                dataTransfer.items.add(file);
                hinhAnhInput.files = dataTransfer.files;
            }
        }

        function handleDragLeave(event) {
            // Remove the 'dragover' class from #preview when the user leaves the drop area
            document.getElementById('preview').classList.remove('dragover');
        }
    </script>
</body>
</html>
