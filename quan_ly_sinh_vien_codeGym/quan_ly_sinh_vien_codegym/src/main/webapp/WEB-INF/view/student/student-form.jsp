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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

</head>
<body>

<a href="/student?action=score">xem điểm</a>
<a href="/student?action=attendance">xem điểm danh</a>
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
        <th> Chỉnh sửa</th>
    </tr>
    <tr>
    <td>${student.studentName}</td>
    <td>${student.dob}</td>
    <td>${student.gender}</td>
    <td>${student.address}</td>
    <td>${student.numberPhone}</td>
    <td>${student.email}</td>
    <td>${student.startLearnDate}</td>
    <td>${student.className}</td>
      <td><button onclick="window.location.href=`/student?action=update`"><i
              class="bi bi-pencil text-primary"></i></button>
      </td>
        </button>
    </tr>
</table>
</body>
</html>
