<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Đánh giá</h2>
    </div>
    <div class="card-body">
        <form action="${basePath}/teacher?page=saveAssessment" method="post">
            <input type="hidden" name="classId" value="${param.classId}">
            <input type="hidden" name="moduleId" value="${param.moduleId}">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>Mã HS</th>
                    <th>Tên HS</th>
                    <th>Điểm trung bình</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.studentName}</td>
                        <td>
                            <input type="number" step="0.1" class="form-control" name="averageScore_${student.studentId}" required>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Lưu</button>
        </form>
    </div>
</div>