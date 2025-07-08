<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Điểm danh</h2>
    </div>
    <div class="card-body">
        <form action="${basePath}/teacher?page=saveAttendance" method="post">
            <input type="hidden" name="classId" value="${param.classId}">
            <div class="mb-3">
                <label for="attendanceDate" class="form-label">Ngày điểm danh:</label>
                <input type="date" class="form-control" id="attendanceDate" name="attendanceDate" required>
            </div>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>Mã HS</th>
                    <th>Tên HS</th>
                    <th>Trạng thái</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.studentName}</td>
                        <td>
                            <select class="form-select" name="status_${student.studentId}">
                                <c:forEach var="status" items="${attendanceStatuses}">
                                    <option value="${status.statusId}">${status.statusName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <button type="submit" class="btn btn-primary">Lưu</button>
        </form>
    </div>
</div>