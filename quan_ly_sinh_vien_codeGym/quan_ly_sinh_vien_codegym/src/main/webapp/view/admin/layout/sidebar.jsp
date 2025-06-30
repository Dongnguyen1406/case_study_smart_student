<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="bg-dark text-white vh-100 position-fixed" style="width: 220px;">
    <div class="sidebar-sticky pt-3 px-3">
        <h5 class="text-white mb-4">Bảng điều khiển</h5>
        <ul class="nav flex-column">
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="${basePath}/view/admin/admin.jsp?page=dashboard">Dashboard</a>
            </li>
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="${basePath}/view/admin/admin.jsp?page=students">Student Management</a>
            </li>
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="${basePath}/view/admin/admin.jsp?page=teachers">Teacher Management</a>
            </li>
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="${basePath}/view/admin/admin.jsp?page=courses">Course Management</a>
            </li>
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="${basePath}/view/admin/admin.jsp?page=modules">Module Management</a>
            </li>
            <li class="nav-item mb-2">
                <a class="nav-link text-white" href="${basePath}/view/admin/admin.jsp?page=classes">Class Management</a>
            </li>
        </ul>
    </div>
</aside>

