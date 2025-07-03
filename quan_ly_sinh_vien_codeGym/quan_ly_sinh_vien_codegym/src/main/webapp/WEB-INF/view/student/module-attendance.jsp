<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/3/2025
  Time: 11:16 AM
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
    <th>Tên module</th>
    <th>Ngày đăng kí module</th>
    <th>số ngày vắng</th>
    <th>Kết quả</th>
  </tr>
  <tr>
    <td>${moduleAttendance.moduleName}</td>
    <td>${moduleAttendance.registrationDate}</td>
    <td>${moduleAttendance.unexcusedAbsences}</td>
    <td>${moduleAttendance.result}</td>
  </tr>
</table>
</body>
</html>
