
<%@ page import="java.util.List" %>
<%@ page import="model.LoaiNhacCu" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <%@ include file="../partialview/header.jsp" %>
    <body>
        <div class="container-fluid">
            <div class="row full-vh">
                <%@ include file="../partialview/sidebar.jsp" %>
             
                      <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            <div class="container mt-5">
   
    <h2 class="text-center text-uppercase">DANH SÁCH LOẠI NHẠC CỤ</h2>
    
 
    
    <!-- Nút mở form thêm/sửa -->
    <button type="button" class="btn btn-success" onclick="openForm('popupForm', null, null)"><i class="fa-solid fa-plus"></i> Thêm</button>
<!-- Pagination Controls -->



    <table class="table">
        <thead>
        <tr>
            <th scope="col">Mã loại</th>
            <th scope="col">Tên loại nhạc cụ</th>
            <th scope="col">Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <% List<LoaiNhacCu> danhSachLoaiNhacCu = (List<LoaiNhacCu>) request.getAttribute("danhSachLoaiNhacCu"); %>
            <% for (LoaiNhacCu loaiNhacCu : danhSachLoaiNhacCu) { %>
                <tr>
                    <td><%= loaiNhacCu.getMaLoaiNhacCu() %></td>
                    <td><%= loaiNhacCu.getTenLoaiNhacCu() %></td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm" onclick="openForm('popupForm', <%= loaiNhacCu.getMaLoaiNhacCu() %>, '<%= loaiNhacCu.getTenLoaiNhacCu() %>' )"><i class="fa-solid fa-pen-to-square"></i></button>
                        <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#xoaModal<%= loaiNhacCu.getMaLoaiNhacCu() %>">
                            <i class="fa-solid fa-trash"></i>
                        </button>
                        <!-- Modal -->
                        <div class="modal fade" id="xoaModal<%= loaiNhacCu.getMaLoaiNhacCu() %>" tabindex="-1" aria-labelledby="xoaModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="xoaModalLabel">Xác nhận xóa</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        Bạn có chắc muốn xóa loại nhạc cụ này không?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                        <a href="DeleteLoaiNhacCu?maLoaiNhacCu=<%= loaiNhacCu.getMaLoaiNhacCu() %>" class="btn btn-danger">Xóa</a>
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
<div id="popupForm" class="modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h2  class="text-center text-uppercase" id="formTitle">Thêm Loại Nhạc Cụ</h2>
                <button type="button" class="btn-close" onclick="closeForm('popupForm')"></button>
            </div>
            <div class="modal-body">
                <form id="myForm" method="post" accept-charset="UTF-8">
                    <div class="mb-3">
                        <label for="tenLoaiNhacCu" class="form-label">Tên loại nhạc cụ:</label>
                        <input type="text" class="form-control" id="tenLoaiNhacCu" name="tenLoaiNhacCu" required maxlength="50">
                    </div>
                    <input type="hidden" id="maLoaiNhacCu" name="maLoaiNhacCu" value="">
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

 function openForm(formId, maLoaiNhacCu, tenLoaiNhacCu) {
        var form = document.getElementById(formId);
        var formTitle = document.getElementById('formTitle');
        var tenLoaiNhacCuInput = document.getElementById('tenLoaiNhacCu');
        console.log(tenLoaiNhacCuInput);
        var maLoaiNhacCuInput = document.getElementById('maLoaiNhacCu');

        if (maLoaiNhacCu !== null) {
            // If maLoaiNhacCu is not null, it means editing an existing item
            formTitle.innerHTML = 'Cập nhật Loại Nhạc Cụ';
            // Fetch data from the corresponding row in the table
			var tenLoaiNhacCu = tenLoaiNhacCu;

            // Set values for the fields in the form
            tenLoaiNhacCuInput.value = tenLoaiNhacCu;
            maLoaiNhacCuInput.value = maLoaiNhacCu;
            // Set the action to edit
            document.getElementById('myForm').action  = 'EditLoaiNhacCu';
        } else {
            // If maLoaiNhacCu is null, it means adding a new item
            formTitle.innerHTML = 'Thêm Loại Nhạc Cụ';
            // Set default values for the fields in the form
            tenLoaiNhacCuInput.value = '';
            maLoaiNhacCuInput.value = '';
            // Set the action to create
            document.getElementById('myForm').action  = 'CreateLoaiNhacCu';
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
