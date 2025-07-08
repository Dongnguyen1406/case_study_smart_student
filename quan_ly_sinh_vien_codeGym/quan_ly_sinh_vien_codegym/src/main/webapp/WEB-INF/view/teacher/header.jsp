<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/user/style.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<nav class="navbar navbar-expand-lg shadow-sm" style="height: 70px; background-color: #ffffff">
    <div class="container-fluid d-flex justify-content-between align-items-center px-3">
        <button id="sidebarToggle" class="btn btn-light border-0 d-flex align-items-center">
            <i class="bi bi-list fs-3"></i>
        </button>
        <div class="d-flex align-items-center">
            <a href="#" class="text-dark position-relative me-3">
                <i class="bi bi-bell fs-5"></i>
                <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                    3
                    <span class="visually-hidden">unread messages</span>
                </span>
            </a>
            <span class="fw-semibold text-dark me-3">
                <c:if test="${not empty sessionScope.teacher}">
                    Xin chào, ${sessionScope.teacher.teacherName} <!-- Đảm bảo teacherName tồn tại -->
                </c:if>
                <c:if test="${empty sessionScope.teacher}">
                    Xin chào, Khách
                </c:if>
            </span>
            <div class="dropdown">
                <a href="#" class="d-flex align-items-center text-decoration-none" id="userDropdown" role="button"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    <img src="${pageContext.request.contextPath}/assets/user/img/avata.png" alt="User" width="40" height="40"
                         class="rounded-circle" style="object-fit: cover;">
                </a>
                <ul class="dropdown-menu dropdown-menu-end shadow" aria-labelledby="userDropdown">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">🚪 Logout</a></li>
                </ul>
            </div>
        </div>
    </div>
</nav>

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
                    logoImg.src = "${pageContext.request.contextPath}/assets/user/img/logo.png";
                    logoImg.style.height = '40px';
                } else {
                    logoImg.src = "${pageContext.request.contextPath}/assets/user/img/logo2.png";
                    logoImg.style.height = '50px';
                }
            });
        }
    });
</script>