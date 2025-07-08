<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="p-3 bg-light border-bottom">
    <div class="container-fluid">
        <div class="d-flex flex-wrap align-items-center justify-content-between">
            <div>
                <h4 class="mb-0">
                    <c:choose>
                        <c:when test="${param.page == 'dashboard' || param.page == null}">Dashboard</c:when>
                        <c:when test="${param.page == 'attendance'}">Điểm danh</c:when>
                        <c:when test="${param.page == 'score'}">Nhập điểm</c:when>
                        <c:when test="${param.page == 'assessment'}">Đánh giá</c:when>
                        <c:otherwise>Dashboard</c:otherwise>
                    </c:choose>
                </h4>
            </div>
            <div class="d-flex align-items-center">
                <div class="me-3">
                    <span class="fw-bold">${teacher.teacherName}</span>
                    <span class="text-muted ms-2">Giáo viên</span>
                </div>
                <div class="dropdown text-end">
                    <a href="#" class="d-block link-dark text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="https://github.com/mdo.png" alt="User" width="32" height="32" class="rounded-circle">
                    </a>
                    <ul class="dropdown-menu text-small" aria-labelledby="dropdownUser1">
                        <li><a class="dropdown-item" href="#">Thông tin cá nhân</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</header>