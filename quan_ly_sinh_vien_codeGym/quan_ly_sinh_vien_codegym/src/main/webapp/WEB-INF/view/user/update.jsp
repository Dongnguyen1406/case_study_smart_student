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
                    <h5 class="card-title fw-semibold">${student.studentName}</h5>
                    <p class="text-muted">Học sinh</p>
                </div>
            </div>
        </div>

        <!-- Phần bên phải: Form cập nhật thông tin -->
        <div class="col-md-8">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title mb-4 fw-semibold">Thông tin cá nhân</h5>
                    <form action="${basePath}/student" method="post">
                        <input type="hidden" name="page" value="update"/>
                        <div class="mb-3">
                            <label for="studentName" class="form-label">Họ và tên</label>
                            <input type="text" class="form-control" name="studentName" id="studentName" required value="${student.studentName}">
                        </div>
                        <div class="mb-3">
                            <label for="dob" class="form-label">Ngày sinh</label>
                            <input type="text" class="form-control" name="dob" id="dob" required value="${student.dob}">
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Giới tính</label>
                            <input type="text" class="form-control"  name="gender" id="gender" required value="${student.gender}">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control"  name="email" id="email" required value="${student.email}">
                        </div>
                        <div class="mb-3">
                            <label for="numberPhone" class="form-label">Số điện thoại</label>
                            <input type="text" class="form-control"  name="numberPhone" id="numberPhone" required value="${student.numberPhone}">
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Địa chỉ</label>
                            <input type="text" class="form-control"  name="address" id="address" required
                                   value="${student.address}">
                        </div>
                        <div class="mb-3">
                            <label for="startLearnDate" class="form-label"> Ngày bắt đầu nhập học</label>
                            <input type="text" class="form-control"  name="startLearnDate" id="startLearnDate" required value="${student.startLearnDate}">
                        </div>
                        <div class="mb-3">
                            <label for="className" class="form-label">Tên lớp</label>
                            <input type="text" class="form-control"  name="className" id="className" required value="${student.className}">
                        </div>
                        <button type="submit" class="btn btn-primary">Thay đổi </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

