<%@ page import="java.util.List" %>
<%@ page import="model.NhaSanXuat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <%@ include file="../partialview/header.jsp" %>
    <body>
        <div class="container-fluid">
            <div class="row full-vh">
                <%@ include file="../partialview/sidebar.jsp" %>

                <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                    <div class="container mt-5">
                        <!-- Form tìm kiếm -->
                      		<form action="SearchNhaSanXuat" method="get" class="mb-3">
				    <div class="input-group">
				        <input type="text" class="form-control" placeholder="Nhập tên nhà sản xuất" name="q">
				        <button class="btn btn-primary" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
				    </div>
				</form>
                        <h2 class="text-center text-uppercase">DANH SÁCH NHÀ SẢN XUẤT</h2>

                        <!-- Nút mở form thêm/sửa -->
                        <button type="button" class="btn btn-success" onclick="openForm('popupForm', null, null, null, null)"><i class="fa-solid fa-plus"></i> Thêm</button>

                        <!-- Table to display manufacturers -->
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Mã nhà sản xuất</th>
                                    <th scope="col">Tên nhà sản xuất</th>
                                    <th scope="col">Số điện thoại</th>
                                    <th scope="col">Địa chỉ</th>
                                    <th scope="col">Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% List<NhaSanXuat> danhSachNhaSanXuat = (List<NhaSanXuat>) request.getAttribute("danhSachNhaSanXuat"); %>
                                <% for (NhaSanXuat nhaSanXuat : danhSachNhaSanXuat) { %>
                                    <tr>
                                        <td><%= nhaSanXuat.getMaNhaSanXuat() %></td>
                                        <td><%= nhaSanXuat.getTenNhaSanXuat() %></td>
                                        <td><%= nhaSanXuat.getSoDienThoai() %></td>
                                        <td><%= nhaSanXuat.getDiaChi() %></td>
                                        <td>
                                            <button type="button" class="btn btn-primary btn-sm" onclick="openForm('popupForm', <%= nhaSanXuat.getMaNhaSanXuat() %>, '<%= nhaSanXuat.getTenNhaSanXuat() %>' , '<%= nhaSanXuat.getSoDienThoai() %>', '<%= nhaSanXuat.getDiaChi() %>' )"><i class="fa-solid fa-pen-to-square"></i></button>
                                            <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#xoaModal<%= nhaSanXuat.getMaNhaSanXuat() %>">
                                                <i class="fa-solid fa-trash"></i>
                                            </button>
                                            <!-- Modal -->
                                            <div class="modal fade" id="xoaModal<%= nhaSanXuat.getMaNhaSanXuat() %>" tabindex="-1" aria-labelledby="xoaModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="xoaModalLabel">Xác nhận xóa</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            Bạn có chắc muốn xóa nhà sản xuất này không?
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                                            <a href="DeleteNhaSanXuat?maNhaSanXuat=<%= nhaSanXuat.getMaNhaSanXuat() %>" class="btn btn-danger">Xóa</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>

                        <%@ include file="../partialview/pagination.jsp" %>

                        <!-- Modal for create/edit form -->
                        <div id="popupForm" class="modal" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h2  class="text-center text-uppercase" id="formTitle">Thêm Nhà Sản Xuất</h2>
                                        <button type="button" class="btn-close" onclick="closeForm('popupForm')"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form id="myForm" method="post" accept-charset="UTF-8">
                                            <div class="mb-3">
                                                <label for="tenNhaSanXuat" class="form-label">Tên nhà sản xuất:</label>
                                                <input type="text" class="form-control" id="tenNhaSanXuat" name="tenNhaSanXuat" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="soDienThoai" class="form-label">Số điện thoại:</label>
                                                <input type="text" class="form-control" id="soDienThoai" name="soDienThoai" required maxlength="10">
                                            </div>
                                            <div class="mb-3">
                                                <label for="diaChi" class="form-label">Địa chỉ:</label>
                                                <input type="text" class="form-control" id="diaChi" name="diaChi" required maxlength="255">
                                            </div>
                                            <input type="hidden" id="maNhaSanXuat" name="maNhaSanXuat" value="">
                                            <button type="submit" class="btn btn-primary"><i class="fa-solid fa-floppy-disk"></i> Lưu</button>
                                            <button type="button" class="btn btn-secondary" onclick="closeForm('popupForm')"><i class="fa-solid fa-xmark"></i> Hủy</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <script>


        function openForm(formId, maNhaSanXuat, tenNhaSanXuat, soDienThoai, diaChi) {
               var form = document.getElementById(formId);
               var formTitle = document.getElementById('formTitle');
               var tenNhaSanXuatInput = document.getElementById('tenNhaSanXuat');
               var maNhaSanXuatInput = document.getElementById('maNhaSanXuat');
               var soDienThoaiInput = document.getElementById('soDienThoai');
               var diaChiInput = document.getElementById('diaChi');
               
               var formAction = document.getElementById('formAction');

               if (maNhaSanXuat !== null) {
                   // If maLoaiNhacCu is not null, it means editing an existing item
                   formTitle.innerHTML = 'Cập nhật nhà sản xuất';
                   // Fetch data from the corresponding row in the table
       			var tenNhaSanXuat = tenNhaSanXuat;

                   // Set values for the fields in the form
                   tenNhaSanXuatInput.value = tenNhaSanXuat;
                   maNhaSanXuatInput.value = maNhaSanXuat;
                   soDienThoaiInput.value = soDienThoai;
                   diaChiInput.value = diaChi;
                   // Set the action to edit
                   document.getElementById('myForm').action  = 'EditNhaSanXuat';
               } else {
                   // If maLoaiNhacCu is null, it means adding a new item
                   formTitle.innerHTML = 'Thêm nhà sản xuất';
                   // Set default values for the fields in the form
                   tenNhaSanXuatInput.value = '';
                   maNhaSanXuatInput.value = '';
                   soDienThoaiInput.value = '';
                   diaChiInput.value = '';
                   // Set the action to create
                   document.getElementById('myForm').action  = 'CreateNhaSanXuat';
               }

               form.style.display = 'block';
               document.body.classList.add('modal-open'); // Add class to body to disable scrolling
               var modalBackdrop = document.createElement('div');
               modalBackdrop.className = 'modal-backdrop fade show';
               document.body.appendChild(modalBackdrop);
           }

           function closeForm(formId) {
               var form = document.getElementById(formId);
               form.style.display = 'none';
               document.body.classList.remove('modal-open'); // Remove class to enable scrolling
               var modalBackdrop = document.querySelector('.modal-backdrop');
               if (modalBackdrop) {
                   modalBackdrop.parentNode.removeChild(modalBackdrop);
               }
           }


        </script>
       
    </body>
</html>
