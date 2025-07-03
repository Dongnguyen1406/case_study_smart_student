<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 7/3/2025
  Time: 8:23 AM
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
    <th> Tên Module </th>
    <th> Điểm lý thuyết </th>
    <th> Điểm thực hành</th>
    <th> Điểm trung bình </th>
  </tr>
  <tr>
    <td>${moduleScore.module}</td>
    <td>${moduleScore.quizScore}</td>
    <td>${moduleScore.practiceScore}</td>
    <td>${moduleScore.averageScore}</td>
  </tr>
</table>
</body>
</html>
