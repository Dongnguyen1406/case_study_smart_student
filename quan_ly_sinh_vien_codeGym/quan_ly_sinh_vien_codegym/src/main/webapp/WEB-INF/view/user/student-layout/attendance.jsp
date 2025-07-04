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

