<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/3/2025
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="p-3">
    <h2 class="mb-4">📋 Danh sách điểm danh</h2>
<%--    <form action="${basePath}/student" method="post">--%>
<%--        <input type="hidden" name="page" value="update"/>--%>
<%--        <div class="mb-3">--%>

<%--            <select class="form-select" name="moduleId" id="gender" required>--%>
<%--                <option value="">-- Chọn giới tính --</option>--%>
<%--                <c:forEach items="${module}" var="module">--%>
<%--                    <option value="${module.moduleId}">${module.moduleName}</option>--%>
<%--                </c:forEach>--%>

<%--                &lt;%&ndash;            <option value="Nam" <c:if test="${student.gender == 'Nam'}">selected</c:if>>Nam</option>&ndash;%&gt;--%>
<%--                &lt;%&ndash;            <option value="Nữ" <c:if test="${student.gender == 'Nữ'}">selected</c:if>>Nữ</option>&ndash;%&gt;--%>
<%--                &lt;%&ndash;            <option value="Khác" <c:if test="${student.gender == 'Khác'}">selected</c:if>>Khác</option>&ndash;%&gt;--%>
<%--            </select>--%>
<%--            <button type="submit" class="btn btn-primary">Chọn</button>--%>
<%--        </div>--%>
<%--        <table class="table table-hover table-bordered">--%>
<%--            <thead class="table-primary">--%>
<%--            <tr>--%>
<%--                <th>Ngày điểm danh</th>--%>
<%--                <th>Trạng thái</th>--%>
<%--            </tr>--%>
<%--            </thead>--%>
<%--            <tbody>--%>
<%--            <tr>--%>
<%--                <td>${attendanceDate.attendanceDate}</td>--%>
<%--                <td>${attendanceDate.status}</td>--%>

<%--                &lt;%&ndash;            <td>✅ Có mặt</td>&ndash;%&gt;--%>
<%--            </tr>--%>
<%--            </tbody>--%>
<%--        </table>--%>

<%--    </form>--%>


    <table class="table table-hover table-bordered">
        <thead class="table-primary">
        <tr>
            <th>Tên module</th>
            <th>Ngày đăng kí module</th>
            <th>số ngày vắng</th>
            <th>Kết quả</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${moduleAttendance.moduleName}</td>
            <td>${moduleAttendance.registrationDate}</td>
            <td>${moduleAttendance.unexcusedAbsences}</td>
            <td>${moduleAttendance.result}</td>
            <%--            <td>✅ Có mặt</td>--%>
        </tr>
        </tbody>
    </table>
</div>

