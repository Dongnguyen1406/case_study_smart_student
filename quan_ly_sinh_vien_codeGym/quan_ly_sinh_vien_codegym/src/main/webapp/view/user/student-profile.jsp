<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/3/2025
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<div class="container mt-4">
    <div class="row">
        <!-- Phần bên trái: Thông tin cơ bản -->
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <img src="${basePath}/assets/user/img/avata.png" alt="Avatar"
                         class="rounded-circle mb-3" width="120" height="120" style="object-fit: cover;">
                    <h5 class="card-title fw-semibold">Nguyễn Thị Thảo</h5>
                    <p class="text-muted">Học sinh</p>
                </div>
            </div>
        </div>

        <!-- Phần bên phải: Form cập nhật thông tin -->
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title mb-4 fw-semibold">Thông tin cá nhân</h5>
                    <form>
                        <div class="mb-3">
                            <label for="studentName" class="form-label">Họ và tên</label>
                            <input type="text" class="form-control" id="studentName" value="Nguyễn Thị Thảo">
                        </div>
                        <div class="mb-3">
                            <label for="studentEmail" class="form-label">Email</label>
                            <input type="email" class="form-control" id="studentEmail" value="thaonguyen@example.com">
                        </div>
                        <div class="mb-3">
                            <label for="studentPhone" class="form-label">Số điện thoại</label>
                            <input type="text" class="form-control" id="studentPhone" value="0123456789">
                        </div>
                        <div class="mb-3">
                            <label for="studentAddress" class="form-label">Địa chỉ</label>
                            <input type="text" class="form-control" id="studentAddress"
                                   value="123 Nguyễn Văn Cừ, Hà Nội">
                        </div>
                        <button type="submit" class="btn btn-primary">Lưu thông tin</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

