<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/3/2025
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="p-3">
    <h2 class="mb-4">📊 Bảng điểm</h2>
    <table class="table table-hover table-bordered">
        <thead class="table-primary">
        <tr>
            <th> Tên Module </th>
            <th> Điểm lý thuyết </th>
            <th> Điểm thực hành</th>
            <th> Điểm trung bình </th>
            <th> Kết quả </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${moduleScore.module}</td>
            <td>${moduleScore.quizScore}</td>
            <td>${moduleScore.practiceScore}</td>
            <td>${moduleScore.averageScore}</td>
            <td> <c:if test="${moduleScore.averageScore>=7.5}">Đạt</c:if></td>
        </tr>
        </tbody>
    </table>
</div>

