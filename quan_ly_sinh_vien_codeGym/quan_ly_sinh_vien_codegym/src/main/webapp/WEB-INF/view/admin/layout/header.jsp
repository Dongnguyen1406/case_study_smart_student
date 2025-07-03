<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="navbar navbar-expand-lg navbar-light bg-light border-bottom px-4">
    <a class="navbar-brand" href="#">Quản Lý Học Sinh</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader"
            aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarHeader">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">Xin chào, Admin</a>
            </li>
            <li class="nav-item">
                <a class="nav-link text-danger" href="${basePath}/logout">Đăng xuất</a>
            </li>
        </ul>
    </div>
</header>

