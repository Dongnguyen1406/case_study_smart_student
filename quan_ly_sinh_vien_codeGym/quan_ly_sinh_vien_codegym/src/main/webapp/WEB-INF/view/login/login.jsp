<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 6/30/2025
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>

    <!-- Custom CSS -->
    <link rel="stylesheet" href="/assets/login.css/login.css">
</head>
<body>
<div class="container-fluid login-container">
    <div class="row h-100">
        <!-- Left: image -->
        <div class="col-lg-6 d-none d-lg-flex login-image">
            <div>
                <i class="fas fa-school school-icon"></i>
                <h2>Cổng thông tin giáo viên</h2>
                <p>Truy cập các tài nguyên lớp học, thông tin học sinh và công cụ giảng dạy ở cùng một nơi.</p>
            </div>
        </div>

        <!-- Right: form -->
        <div class="col-lg-6 bg-white d-flex align-items-center">
            <div class="login-form w-100">
                <h2 class="text-center mb-3">Chào mừng!</h2>
                <p class="text-center mb-4">Vui lòng đăng nhập vào tài khoản giáo viên hoặc học sinh của bạn</p>
<%--                <form class="d-flex justify-content-end mb-3" method="get" action="/admin/product-management">--%>
<%--                    <input type="hidden" name="action" value="search"/>--%>
                <form action="${pageContext.request.contextPath}/login"  method="post" id="loginForm">
                    <input type="hidden" name="action" value="login"/>
                    <div class="mb-3 input-group">
                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                        <input type="text" class="form-control" name="username" id="username" placeholder="tên người dùng" required>
                    </div>

                    <div class="mb-3 input-group">
                        <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        <input type="password" class="form-control" name="password" id="password" placeholder="mật khẩu" required>
                    </div>

                    <div class="mb-3 d-flex justify-content-between align-items-center">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="rememberMe">
                            <label class="form-check-label" for="rememberMe">Lưu mật khẩu</label>
                        </div>
                        <a href="#" class="forgot-password">Quên mật khẩu?</a>
                    </div>

                    <div class="d-grid mb-3">
                        <button type="submit" class="btn btn-login">Đăng nhập</button>
                    </div>

                    <div class="divider"><span>or</span></div>

                    <div class="d-grid gap-2">
                        <button type="button" class="btn btn-google btn-login">
                            <i class="fab fa-google me-2"></i> Đăng nhập bằng Google
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<!-- Custom JS -->
<script src="/WEB-INF/view/login/script.js"></script>
</body>
</html>

