<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Quản lý đánh giá</h2>
    </div>
    <div class="card-body">
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Mã HS</th>
                <th>Tên HS</th>
                <th>Điểm trung bình</th>
                <th>Kết quả</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${students}">
                <c:set var="existingAssessment" value="${existingAssessments[student.studentId]}" />
                <tr>
                    <td>${student.studentId}</td>
                    <td>${student.studentName}</td>
                    <td>
                        <c:if test="${existingAssessment != null}">
                            ${existingAssessment.averageScore}
                        </c:if>
                        <c:if test="${existingAssessment == null}">
                            <span class="text-muted">Chưa có điểm</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${existingAssessment != null}">
                            <span class="${existingAssessment.status ? 'text-success' : 'text-danger'}">
                                ${existingAssessment.status ? 'Đạt' : 'Không đạt'}
                            </span>
                        </c:if>
                        <c:if test="${existingAssessment == null}">
                            <span class="text-muted">-</span>
                        </c:if>
                    </td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm" 
                                data-bs-toggle="modal" 
                                data-bs-target="#assessmentModal${student.studentId}">
                            Nhập đánh giá
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal for each student -->
<c:forEach var="student" items="${students}">
    <c:set var="existingAssessment" value="${existingAssessments[student.studentId]}" />
    <div class="modal fade" id="assessmentModal${student.studentId}" tabindex="-1" aria-labelledby="assessmentModalLabel${student.studentId}" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="assessmentModalLabel${student.studentId}">Đánh giá cho ${student.studentName}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${basePath}/teacher?page=saveStudentAssessment" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="classId" value="${param.classId}">
                        <input type="hidden" name="moduleId" value="${param.moduleId}">
                        <input type="hidden" name="studentId" value="${student.studentId}">
                        
                        <div class="mb-3">
                            <label for="averageScore${student.studentId}" class="form-label">Điểm trung bình:</label>
                            <input type="number" step="0.1" min="0" max="10" class="form-control" 
                                id="averageScore${student.studentId}" name="averageScore" 
                                value="${existingAssessment != null ? existingAssessment.averageScore : ''}" required>
                            <div class="form-text">Điểm từ 7.5 trở lên sẽ được đánh giá là Đạt</div>
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