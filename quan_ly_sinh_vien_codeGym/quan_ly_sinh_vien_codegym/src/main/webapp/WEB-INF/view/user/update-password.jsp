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


    <!-- Phần bên phải: Form cập nhật thông tin -->
    <div class="col-md-8">
      <div class="card shadow-sm">
        <div class="card-body">
          <h5 class="card-title mb-4 fw-semibold">Thay đổi mật khẩu</h5>
          <form action="${basePath}/student" method="post">
            <input type="hidden" name="page" value="updatePassword"/>
            <div class="mb-3">
              <label for="currentPassword" class="form-label">Mật khẩu hiện tại</label>
              <input  type="password" class="form-control" name="currentPassword" id="currentPassword"  required>
            </div>
            <c:if test="${not empty error}">
              <div style="color: red">${error}</div>
            </c:if>
            <div class="mb-3">
              <label for="newPassword" class="form-label">Mật khẩu mới</label>
              <input type="password" class="form-control" name="newPassword" id="newPassword" required>
            </div>
            <div class="mb-3">
              <label for="confirmNewPassword" class="form-label">Nhập lại mật khẩu mới</label>
              <input type="password" class="form-control"  name="confirmNewPassword" id="confirmNewPassword"  required >
            </div>
            <c:if test="${not empty newError}">
              <div style="color: red">${newError}</div>
            </c:if>

            <button type="submit" class="btn btn-primary">Thay đổi </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

