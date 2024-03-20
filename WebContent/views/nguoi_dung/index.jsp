<%@ page import="java.util.List"%>
<%@ page import="model.NguoiDung"%>
<%@ page import="dao.NguoiDungDAO"%>
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
				

					<h2 class="text-center text-uppercase">Danh sách người dùng</h2>

					<!-- Nút mở form thêm/sửa -->
					<button type="button" class="btn btn-success"
						onclick="openForm('popupForm', null, null, null, null)">
						<i class="fa-solid fa-plus"></i> Thêm
					</button>

					<!-- Table to display musical instruments -->
					<table class="table">
						<thead>
							<tr>
							<th scope="col">Hình ảnh</th>
								<th scope="col">Mã người dùng</th>
								<th scope="col">Tên người dùng</th>
								<th scope="col">Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<%
							List<NguoiDung> danhSachNguoiDung = (List<NguoiDung>) request.getAttribute("danhSachNguoiDung");
							%>
							<%
							for (NguoiDung nguoiDung : danhSachNguoiDung) {
							%>
							<tr>
								<td class="text-center align-middle"><img
									src="<%=nguoiDung.getHinhAnh()%>" alt="<%=nguoiDung.getTenNguoiDung()%>"
									height="60"></td>
								<td class="align-middle"><%=nguoiDung.getMaNguoiDung()%></td>
								<td class="align-middle"><%=nguoiDung.getTenNguoiDung()%></td>
								<td class="align-middle">
									<button type="button" class="btn btn-primary btn-sm"
										onclick="openForm('popupForm', '<%=nguoiDung.getMaNguoiDung()%>', '<%=nguoiDung.getTenNguoiDung()%>',
								 '<%=nguoiDung.getHinhAnh()%>' )">
										<i class="fa-solid fa-pen-to-square"></i>

									</button>


									<button type="button" class="btn btn-danger btn-sm"
										data-bs-toggle="modal"
										data-bs-target="#xoaModal<%=nguoiDung.getMaNguoiDung()%>">
										<i class="fa-solid fa-trash"></i>
									</button> <!-- Modal -->
									<div class="modal fade" id="xoaModal<%=nguoiDung.getMaNguoiDung()%>"
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
												<div class="modal-body">Bạn có chắc muốn xóa người dùng này không?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Đóng</button>
													<a
														href="DeleteNguoiDung?maNguoiDung=<%=nguoiDung.getMaNguoiDung()%>"
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
										người dùng</h2>
									<button type="button" class="btn-close"
										onclick="closeForm('popupForm')"></button>
								</div>
								<div class="modal-body">
									<form id="myForm" method="post" accept-charset="UTF-8"
										enctype="multipart/form-data">
										<div class="mb-3">
											<label for="maNguoiDung" class="form-label">Mã người dùng:</label> <input type="text" class="form-control" id="maNguoiDung"
												name="maNguoiDung" required maxlength="50">
										</div>
										
										<div class="mb-3">
											<label for="tenNguoiDung" class="form-label">Tên người dùng:</label> <input type="text" class="form-control" id="tenNguoiDung"
												name="tenNguoiDung" required maxlength="50">
										</div>
										
										<div class="mb-3">
											<label for="matKhau" class="form-label">Mật khẩu:</label> <input
												type="password" class="form-control" id="matKhau" name="matKhau"
												required>
												
												<input type="checkbox" id="changePasswordCheckbox" name="changePasswordCheckbox" onchange="togglePasswordInput()">
							<label for="changePasswordCheckbox">Đổi mật khẩu</label>

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
        function openForm(formId, maNguoiDung, tenNguoiDung, hinhAnh) {
            var form = document.getElementById(formId);
            var formTitle = document.getElementById('formTitle');
            var maNguoiDungInput = document.getElementById('maNguoiDung');
            var tenNguoiDungInput = document.getElementById('tenNguoiDung');
            var hinhAnhInput = document.getElementById('hinhAnh');
            var selectedImage = document.getElementById('selectedImage');
            var preview = document.getElementById('preview');
            var changePasswordCheckbox = document.getElementById("changePasswordCheckbox");
            var passwordInput = document.getElementById("matKhau"); 
            if (maNguoiDung !== null) {
                formTitle.innerHTML = 'Cập nhật người dùng';
                tenNguoiDungInput.value = tenNguoiDung;
                maNguoiDungInput.value = maNguoiDung;
                passwordInput.readOnly = true;
                maNguoiDungInput.readOnly = true;
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

                document.getElementById('myForm').action  = 'EditNguoiDung';
            } else {
            	 changePasswordCheckbox.style.display = "none";
                formTitle.innerHTML = 'Thêm người dùng';
                tenNguoiDungInput.value = '';
                maNguoiDungInput.value = '';
                passwordInput.value = '';
                preview.src = '';
                document.getElementById('myForm').action = 'CreateNguoiDung';
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
        function togglePasswordInput() {
            var passwordInput = document.getElementById("matKhau"); // replace "passwordInput" with the actual ID of your password input field
            var changePasswordCheckbox = document.getElementById("changePasswordCheckbox");

            if (changePasswordCheckbox.checked) {
                // If the checkbox is checked, disable readonly and enable the password input
                passwordInput.readOnly = false;
            } else {
                // If the checkbox is not checked, enable readonly for the password input
                passwordInput.readOnly = true;
                passwordInput.value = '';
            }
        }
    </script>
</body>
</html>
