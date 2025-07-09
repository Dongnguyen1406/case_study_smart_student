<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Quản lý điểm danh</h2>
    </div>
    <div class="card-body">
        <form method="get" action="${basePath}/teacher" id="attendanceForm">
            <input type="hidden" name="page" value="attendance">
            <input type="hidden" name="classId" value="${param.classId}">
            <div class="mb-3">
                <label for="attendanceDate" class="form-label">Chọn ngày điểm danh:</label>
                <div class="d-flex">
                    <input type="date" class="form-control" id="attendanceDate" name="attendanceDate" 
                           value="${param.attendanceDate}" max="" required>
                    <button type="submit" class="btn btn-primary ms-2">Xem</button>
                </div>
            </div>
        </form>
        
        <c:if test="${not empty param.attendanceDate}">
            <table class="table table-bordered table-hover mt-3">
                <thead>
                <tr>
                    <th>Mã HS</th>
                    <th>Tên HS</th>
                    <th>Trạng thái</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.studentId}</td>
                        <td>${student.studentName}</td>
                        <td>
                            <c:if test="${not empty existingAttendance[student.studentId]}">
                                <c:forEach var="status" items="${attendanceStatuses}">
                                    <c:if test="${existingAttendance[student.studentId] == status.statusId}">
                                        <span class="${status.statusId == 1 ? 'text-danger' : status.statusId == 2 ? 'text-warning' : 'text-success'}">
                                            ${status.statusName}
                                        </span>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty existingAttendance[student.studentId]}">
                                <span class="text-muted">Chưa điểm danh</span>
                            </c:if>
                        </td>
                        <td>
                            <button type="button" class="btn btn-primary btn-sm" 
                                    data-bs-toggle="modal" 
                                    data-bs-target="#attendanceModal${student.studentId}">
                                Điểm danh
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>

<!-- Modal for each student -->
<c:forEach var="student" items="${students}">
    <div class="modal fade" id="attendanceModal${student.studentId}" tabindex="-1" aria-labelledby="attendanceModalLabel${student.studentId}" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="attendanceModalLabel${student.studentId}">Điểm danh cho ${student.studentName}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${basePath}/teacher?page=saveStudentAttendance" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="classId" value="${param.classId}">
                        <input type="hidden" name="attendanceDate" value="${param.attendanceDate}">
                        <input type="hidden" name="studentId" value="${student.studentId}">
                        
                        <div class="mb-3">
                            <label for="status${student.studentId}" class="form-label">Trạng thái điểm danh:</label>
                            <select class="form-select" id="status${student.studentId}" name="statusId">
                                <c:forEach var="status" items="${attendanceStatuses}">
                                    <option value="${status.statusId}" 
                                            ${existingAttendance[student.studentId] == status.statusId ? 'selected' : ''}>
                                        ${status.statusName}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Lưu</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</c:forEach>

<script>
document.addEventListener('DOMContentLoaded', function() {
    // Set max date to tomorrow
    var today = new Date();
    var tomorrow = new Date(today);
    tomorrow.setDate(today.getDate() + 1);
    
    var maxDate = tomorrow.toISOString().split('T')[0];
    document.getElementById('attendanceDate').setAttribute('max', maxDate);
});
</script>