<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/3/2025
  Time: 2:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<div class="container mt-4">
    <c:if test="${not empty sessionScope.successMessage}">
        <div id="toast-alert" class="custom-toast animate-slide-down">
                ${sessionScope.successMessage}
        </div>
        <c:remove var="successMessage" scope="session"/>

        <style>
            .custom-toast {
                position: fixed;
                top: 30px;
                left: 50%;
                transform: translateX(-50%);
                background-color: white;
                color: black;
                padding: 15px 25px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                z-index: 9999;
                font-weight: bold;
                opacity: 0.95;
            }

            @keyframes slideDown {
                from {
                    transform: translate(-50%, -50px);
                    opacity: 0;
                }
                to {
                    transform: translate(-50%, 0);
                    opacity: 1;
                }
            }

            .animate-slide-down {
                animation: slideDown 0.5s ease-out;
            }
        </style>

        <script>
            setTimeout(function () {
                var toast = document.getElementById("toast-alert");
                if (toast) {
                    toast.style.display = "none";
                }
            }, 3000);
        </script>
    </c:if>

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
                    <form>
                        <div class="mb-3">
                            <label for="studentName" class="form-label">Họ và tên</label>
                            <input type="text" class="form-control" id="studentName" readonly
                                   value="${student.studentName}">
                        </div>
                        <div class="mb-3">
                            <label for="dob" class="form-label">Ngày sinh</label>
                            <input type="text" class="form-control" id="dob" readonly value="${student.dobFormatted}">
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Giới tính</label>
                            <input type="text" class="form-control" id="gender" readonly value="${student.gender}">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" readonly value="${student.email}">
                        </div>
                        <div class="mb-3">
                            <label for="numberPhone" class="form-label">Số điện thoại</label>
                            <input type="text" class="form-control" id="numberPhone" readonly
                                   value="${student.numberPhone}">
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Địa chỉ</label>
                            <input type="text" class="form-control" id="address" readonly
                                   value="${student.address}">
                        </div>
                        <div class="mb-3">
                            <label for="startLearnDate" class="form-label"> Ngày bắt đầu nhập học</label>
                            <input type="text" id = "startLearnDate" class="form-control" readonly value="${student.startLearnDateFormatted}">
                        </div>
                        <div class="mb-3">
                            <label for="className" class="form-label">Tên lớp</label>
                            <input type="text" class="form-control" id="className" readonly
                                   value="${student.className}">
                        </div>

                        <div >
                            <button type="button"
                                    onclick="window.location.href='${basePath}/student?page=updatePassword'"
                                    class="btn btn-light px-4">
                                Thay đổi mật khẩu
                            </button>
                            <button type="button"
                                    onclick="window.location.href='${basePath}/student?page=update'"
                                    class="btn btn-primary px-4">
                                Thay đổi thông tin
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal Cập Nhật Khóa Học -->
<%--<div class="modal fade" id="editCourseModal" tabindex="-1" aria-labelledby="editCourseModalLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <form action="${basePath}/admin?page=updateCourse" method="post" class="needs-validation" novalidate>--%>
<%--                <input type="hidden" name="id" id="editCourseId">--%>
<%--                <div class="modal-header">--%>
<%--                    <h5 class="modal-title">✏️ Cập nhật khóa học</h5>--%>
<%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                </div>--%>

<%--                <div class="modal-body">--%>
<%--                    <div class="mb-3">--%>
<%--                        <label class="form-label">Tên khóa học</label>--%>
<%--                        <input type="text" name="name" id="editCourseName" class="form-control" required>--%>
<%--                        <div class="invalid-feedback">Vui lòng nhập tên khóa học.</div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="modal-footer">--%>
<%--                    <button type="submit" class="btn btn-success">Cập nhật</button>--%>
<%--                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>