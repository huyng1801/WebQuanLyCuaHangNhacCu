<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.NguoiDung" %>
<%@ page import="dao.NguoiDungDAO" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="DangNhap" method="post" class="border p-4 rounded">
                <h2 class="text-center mb-4">Đăng Nhập</h2>
                
                <%-- Display error message if login fails --%>
                <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
                <% if (errorMessage != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= errorMessage %>
                    </div>
                <% } %>

                <div class="mb-3">
                    <label for="tenDangNhap" class="form-label">Tên Đăng Nhập:</label>
                    <input type="text" class="form-control" id="tenDangNhap" name="tenDangNhap" required>
                </div>
                <div class="mb-3">
                    <label for="matKhau" class="form-label">Mật Khẩu:</label>
                    <input type="password" class="form-control" id="matKhau" name="matKhau" required>
                </div>
                <button type="submit" class="btn btn-primary">Đăng Nhập</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
