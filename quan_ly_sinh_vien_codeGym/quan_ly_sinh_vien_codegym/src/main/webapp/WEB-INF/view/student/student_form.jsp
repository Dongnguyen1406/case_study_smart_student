<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 6/27/2025
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <tr>
        <th> Tên</th>
        <th> Ngày sinh</th>
        <th> Giới tính</th>
        <th> Địa chỉ</th>
        <th> Số điện thoại</th>
        <th> Email</th>
        <th> Ngày bắt đầu nhập học</th>
        <th> Tên lớp</th>
    </tr>

        <c:forEach items="${students}" var="student">
            <tr>
            <td>${student.studentName}</td>
            <td>${student.dob}</td>
            <td>${student.gender}</td>
            <td>${student.address}</td>
            <td>${student.numberPhone}</td>
            <td>${student.email}</td>
            <td>${student.startLearnDate}</td>
            <td>${student.studentName}</td>
            </tr>
        </c:forEach>

</table>
</body>
</html>
