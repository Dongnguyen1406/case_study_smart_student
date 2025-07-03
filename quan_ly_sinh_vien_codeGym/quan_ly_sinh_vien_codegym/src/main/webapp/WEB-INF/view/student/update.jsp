<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: LENOVO
  Date: 6/8/2025
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<html>
<head>
    <title>Title</title>

</head>
<body>
<div class="container" style="max-width: 900px; margin-top: 30px;background-color: #8aa3ea ; border-radius: 10px">
    <h4 class="mb-4 text-center">Chỉnh Sửa cá nhân</h4>

    <form method="post">
        <div class="mb-3">
            <label for="formGroupExampleInput6" class="form-label">Tên học sinh</label>
            <input  name="studentName" required value="${student.studentName}"
                    type="text" class="form-control" id="formGroupExampleInput6" placeholder="studentName">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput5" class="form-label">Ngày sinh</label>
            <input  name="dob" required value="${student.dob}"
                    type="date" class="form-control" id="formGroupExampleInput5" placeholder="dob">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label">Giới tính</label>
            <input  name="gender" required value="${student.gender}"
                    type="text" class="form-control" id="formGroupExampleInput4" placeholder="gender">
        </div>
        <div class="mb-3">
        <label for="formGroupExampleInput3" class="form-label">Địa chỉ</label>
        <input  name="address" required value="${student.address}"
                type="text" class="form-control" id="formGroupExampleInput3" placeholder="address">
    </div>
        <div class="mb-3">
        <label for="formGroupExampleInput2" class="form-label">Số điện thoại</label>
        <input  name="numberPhone" required value="${student.numberPhone}"
                type="text" class="form-control" id="formGroupExampleInput2" placeholder="numberPhone">
    </div>
        <div class="mb-3">
            <label for="formGroupExampleInput1" class="form-label">Email</label>
            <input  name="email" required value="${student.email}"
                    type="email" class="form-control" id="formGroupExampleInput1" placeholder="email">
        </div>
        <div class="mb-3">
        <label for="formGroupExampleInput" class="form-label">Ngày bắt đầu học</label>
        <input  name="startLearnDate" required value="${student.startLearnDate}"
                type="date" class="form-control" id="formGroupExampleInput" placeholder="startLearnDate">
    </div> <div class="mb-3">
        <label for="formGroupExampleInput9" class="form-label">Tên Lớp</label>
        <input  name="className" required value="${student.className}"
                type="text" class="form-control" id="formGroupExampleInput9" placeholder="className">
    </div>
        <div class="d-flex justify-content-end gap-2 mt-2">
            <button type="submit" class="btn btn-primary btn-sm">Chỉnh sửa</button>
        </div>
    </form>
</div>
</body>

</html>
