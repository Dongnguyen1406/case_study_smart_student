<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/2/2025
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="layout/style.css">
</head>
<div id="sidebar" class="p-0 m-0 d-flex flex-column" style="height: 100vh; width: 250px; transition: width 0.3s; background-color: #ffffff">
    <!-- Logo -->
    <div id="sidebar-logo" class="d-flex align-items-center justify-content-center" style="height: 70px; border-bottom: 1px solid #ddd; ">
        <img id="logo-img" src="${pageContext.request.contextPath}/assets/user/img/logo2.png" alt="Logo" style="height: 50px; object-fit: contain;">
    </div>


    <!-- Menu -->
    <!-- sidebar.jsp -->
    
    <ul class="nav flex-column px-2" id="sidebar-menu">
        <li class="nav-item d-flex align-items-center py-2">
            <a class="nav-link text-dark" href="${pageContext.request.contextPath}/view/user/student.jsp?page=dashboard">
                <i class="bi bi-house-door fs-5"></i>
                <span class="ms-2 sidebar-text">Trang chủ</span>
            </a>
        </li>
        <li class="nav-item d-flex align-items-center py-2">
            <a class="nav-link text-dark" href="${pageContext.request.contextPath}/student?page=score">
                <i class="bi bi-award fs-5"></i>
                <span class="ms-2 sidebar-text">Điểm</span>
            </a>
        </li>
        <li class="nav-item d-flex align-items-center py-2">
            <a class="nav-link text-dark" href="${pageContext.request.contextPath}/student?page=attendance">
                <i class="bi bi-calendar-check fs-5"></i>
                <span class="ms-2 sidebar-text">Điểm danh</span>
            </a>
        </li>
        <li class="nav-item d-flex align-items-center py-2">
            <a class="nav-link text-dark" href="${pageContext.request.contextPath}/student?page=assessment">
                <i class="bi bi-star fs-5"></i>
                <span class="ms-2 sidebar-text">Đánh giá</span>
            </a>
        </li>
    </ul>

</div>



