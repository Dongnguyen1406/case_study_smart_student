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
                            <label for="studentName" class="form-label">Họ và tên <span style="color:red">*</span></label>
                            <input  type="text" class="form-control" name="studentName" id="studentName"  pattern="[A-Za-zÀ-ỹà-ỹ\s]{2,50}" title="Chỉ nhập chữ và khoảng trắng, độ dài 2-50 ký tự" required value="${student.studentName}">
                        </div>
                        <div class="mb-3">
                            <label for="dob" class="form-label">Ngày sinh <span style="color:red">*</span></label>
                            <input type="date" class="form-control" name="dob" id="dob" required value="${student.dob}">
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Giới tính <span style="color:red">*</span></label>
                            <select class="form-select" name="gender" id="gender" required>
                                <option value="">-- Chọn giới tính --</option>
                                <option value="Nam" <c:if test="${student.gender == 'Nam'}">selected</c:if>>Nam</option>
                                <option value="Nữ" <c:if test="${student.gender == 'Nữ'}">selected</c:if>>Nữ</option>
                                <option value="Khác" <c:if test="${student.gender == 'Khác'}">selected</c:if>>Khác</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email <span style="color:red">*</span></label>
                            <input type="email" class="form-control"  name="email" id="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Nhập đúng định dạng email" required value="${student.email}">
                        </div>
                        <div class="mb-3">
                            <label for="numberPhone" class="form-label">Số điện thoại <span style="color:red">*</span></label>
                            <input type="text" class="form-control"  name="numberPhone"  pattern="^(0|\+84)[0-9]{9}$"
                                   title="Số điện thoại phải bắt đầu bằng 0 hoặc +84 và có đúng 9 chữ số sau đó"  id="numberPhone" required value="${student.numberPhone}">
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Địa chỉ <span style="color:red">*</span></label>
                            <input type="text" class="form-control" pattern="[\wÀ-ỹà-ỹ\s,.-]{5,100}" title="Địa chỉ tối thiểu 5 ký tự" name="address" id="address" required
                                   value="${student.address}">
                        </div>
                        <div class="mb-3">
                            <label for="startLearnDate" class="form-label"> Ngày bắt đầu nhập học <span style="color:red">*</span></label>
                            <input type="date" class="form-control"  name="startLearnDate" id="startLearnDate" required value="${student.startLearnDate}">
                        </div>
                        <div class="mb-3">
                            <label for="className" class="form-label">Tên lớp <span style="color:red">*</span></label>
                            <input type="text" class="form-control"  name="className"  pattern="^C\d{4}G\d$"
                                   title="Tên lớp phải theo định dạng CxxxxGx, ví dụ: C0225G1"  id="className" required value="${student.className}">
                        </div>
                        <button type="submit" class="btn btn-primary">Thay đổi </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

