<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/2/2025
  Time: 11:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/WEB-INF/view/common/layout/style.css">

<nav class="navbar navbar-expand-lg shadow-sm" style="height: 70px; background-color: #ffffff">
    <div class="container-fluid d-flex justify-content-between align-items-center px-3">

        <!-- Button hamburger ở bên trái -->
        <button id="sidebarToggle" class="btn btn-light border-0 d-flex align-items-center">
            <i class="bi bi-list fs-3"></i>
        </button>

        <!-- Phần còn lại bên phải -->
        <div class="d-flex align-items-center">
            <!-- Icon thông báo -->
            <a href="#" class="text-dark position-relative me-3">
                <i class="bi bi-bell fs-5"></i>
                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                    3
                    <span class="visually-hidden">unread messages</span>
                </span>
            </a>

            <!-- Tên người dùng -->
            <span class="fw-semibold text-dark me-3">Admin</span>

            <!-- Avatar dropdown -->
            <div class="dropdown">
                <a href="#" class="d-flex align-items-center text-decoration-none" id="userDropdown" role="button"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}/assets/user/img/avata.png"
                         alt="User" width="40" height="40"
                         class="rounded-circle" style="object-fit: cover;">
                </a>
                <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="userDropdown">
<%--                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/view/user/student.jsp?page=profile">👤 Profile</a></li>--%>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li><a class="dropdown-item" href="/login">🚪 Logout</a></li>

                </ul>
            </div>
        </div>
    </div>
</nav>


<!-- Bootstrap Icons + JS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const sidebarToggle = document.getElementById('sidebarToggle');
        const sidebar = document.getElementById('sidebar');
        const logoImg = document.getElementById('logo-img');

        if (sidebarToggle && sidebar && logoImg) {
            sidebarToggle.addEventListener('click', function () {
                sidebar.classList.toggle('collapsed');

                if (sidebar.classList.contains('collapsed')) {
                    // Thu nhỏ sidebar, đổi logo sang logo.png
                    logoImg.src = "${pageContext.request.contextPath}/assets/user/img/logo.png";
                    logoImg.style.height = '40px'; // có thể tùy chỉnh theo ý bạn
                } else {
                    // Mở rộng sidebar, trả về logo2.png
                    logoImg.src = "${pageContext.request.contextPath}/assets/user/img/logo2.png";
                    logoImg.style.height = '50px';
                }
            });
        }
    });
</script>



