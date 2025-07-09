<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Dashboard</h2>
    </div>
    <div class="card-body">
        <h3>Danh sách lớp</h3>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Tên lớp</th>
                <th>Module</th>
                <th>Khóa học</th>
                <th>Giáo viên</th>
                <th>Ngày bắt đầu</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="classItem" items="${teacherClasses}">
                <tr>
                    <td>${classItem.className}</td>
                    <td>${classItem.moduleName}</td>
                    <td>${classItem.courseName}</td>
                    <td>${classItem.teacherName}</td>
                    <td>${classItem.startDate}</td>
                    <td>
                        <a href="${basePath}/teacher?page=attendance&classId=${classItem.classId}" class="btn btn-primary btn-sm">Điểm danh</a>
                        <a href="${basePath}/teacher?page=score&classId=${classItem.classId}&moduleId=${classItem.moduleId}" class="btn btn-success btn-sm">Nhập điểm</a>
                        <a href="${basePath}/teacher?page=assessment&classId=${classItem.classId}&moduleId=${classItem.moduleId}" class="btn btn-info btn-sm">Đánh giá</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>