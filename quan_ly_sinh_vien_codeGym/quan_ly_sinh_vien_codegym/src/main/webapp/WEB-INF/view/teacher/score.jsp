<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Nhập điểm</h2>
    </div>
    <div class="card-body">
        <form action="${basePath}/teacher?page=saveScore" method="post">
            <input type="hidden" name="classId" value="${param.classId}">
            <input type="hidden" name="moduleId" value="${param.moduleId}">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>Mã HS</th>
                    <th>Tên HS</th>
                    <th>Điểm bài kiểm tra</th>
                    <th>Điểm thực hành</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.studentName}</td>
                        <td>
                            <input type="number" step="0.1" class="form-control" name="quizScore_${student.studentId}" required>
                        </td>
                        <td>
                            <input type="number" step="0.1" class="form-control" name="practiceScore_${student.studentId}" required>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Lưu</button>
        </form>
    </div>
</div>