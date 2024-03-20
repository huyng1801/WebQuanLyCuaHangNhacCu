<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
    <div class="position-sticky">
        <ul class="nav flex-column">
         <li class="nav-item">
                <a class="nav-link active" href="ManagerTrangChu">
                  <i class="fa-solid fa-music"></i> Trang chủ
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ManagerNguoiDung">
                   <i class="fa-solid fa-user"></i> Người dùng
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="ManagerLoaiNhacCu">
                  <i class="fa-solid fa-music"></i> Loại nhạc cụ
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ManagerNhaSanXuat">
                 <i class="fa-solid fa-industry"></i> Nhà sản xuất
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ManagerNhacCu">
                   <i class="fa-solid fa-guitar"></i> Nhạc cụ
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="ManagerKhachHang">
                   <i class="fa-solid fa-people-group"></i> Khách hàng
                </a>
            </li>
<li class="nav-item" id="hoa-don">
    <a class="nav-link" href="#">
        <i class="fa-solid fa-file-invoice-dollar"></i> Hóa đơn <i class="fa-solid fa-chevron-down" id="chevron-icon"></i>
    </a>
    <ul class="submenu">
        <li><a href="ManagerHoaDon"><i class="fa-solid fa-newspaper"></i> Lập hóa đơn</a></li>
        <li><a href="XemHoaDon"><i class="fa-solid fa-clock-rotate-left"></i> Xem hóa đơn</a></li>
    </ul>
</li>


     
        </ul>
    </div>
</nav>
<script>
    var hoaDonItem = document.getElementById('hoa-don');
    var chevronIcon = document.getElementById('chevron-icon');

    hoaDonItem.addEventListener('click', function () {
        var submenu = this.querySelector('.submenu');
        if (submenu.style.display === 'none' || submenu.style.display === '') {
            submenu.style.display = 'block';
            chevronIcon.classList.remove('fa-chevron-down');
            chevronIcon.classList.add('fa-chevron-right');
        } else {
            submenu.style.display = 'none';
            chevronIcon.classList.remove('fa-chevron-right');
            chevronIcon.classList.add('fa-chevron-down');
        }
    });
</script>


