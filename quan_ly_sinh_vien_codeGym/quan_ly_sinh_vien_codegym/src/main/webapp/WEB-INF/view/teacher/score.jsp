<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Quản lý điểm</h2>
    </div>
    <div class="card-body">
        <%-- Add error and success message display --%>
        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        
        <%-- Add success message display --%>
        <c:if test="${not empty param.successMessage}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <%= java.net.URLDecoder.decode(request.getParameter("successMessage"), "UTF-8") %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>
        
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Mã HS</th>
                <th>Tên HS</th>
                <th>Điểm bài kiểm tra</th>
                <th>Điểm thực hành</th>
                <th>Điểm trung bình</th>
                <th>Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="student" items="${students}">
                <c:set var="existingScore" value="${existingScores[student.studentId]}" />
                <tr>
                    <td>${student.studentId}</td>
                    <td>${student.studentName}</td>
                    <td>
                        <c:if test="${existingScore != null}">
                            ${existingScore.quizScore}
                        </c:if>
                        <c:if test="${existingScore == null}">
                            <span class="text-muted">Chưa có điểm</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${existingScore != null}">
                            ${existingScore.practiceScore}
                        </c:if>
                        <c:if test="${existingScore == null}">
                            <span class="text-muted">Chưa có điểm</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${existingScore != null}">
                            <span class="fw-bold">${existingScore.averageScore}</span>
                        </c:if>
                        <c:if test="${existingScore == null}">
                            <span class="text-muted">-</span>
                        </c:if>
                    </td>
                    <td>
                        <button type="button" class="btn btn-primary btn-sm" 
                                data-bs-toggle="modal" 
                                data-bs-target="#scoreModal${student.studentId}">
                            Nhập điểm
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
    <c:set var="existingScore" value="${existingScores[student.studentId]}" />
    <div class="modal fade" id="scoreModal${student.studentId}" tabindex="-1" aria-labelledby="scoreModalLabel${student.studentId}" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="scoreModalLabel${student.studentId}">Nhập điểm cho ${student.studentName}</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="${basePath}/teacher?page=saveStudentScore" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="classId" value="${param.classId}">
                        <input type="hidden" name="moduleId" value="${param.moduleId}">
                        <input type="hidden" name="studentId" value="${student.studentId}">
                        
                        <div class="mb-3">
                            <label for="quizScore${student.studentId}" class="form-label">Điểm bài kiểm tra:</label>
                            <input type="number" step="0.1" min="0" max="10" class="form-control" 
                                id="quizScore${student.studentId}" name="quizScore" 
                                value="${existingScore != null ? existingScore.quizScore : ''}" required>
                        </div>
                        <div class="mb-3">
                            <label for="practiceScore${student.studentId}" class="form-label">Điểm thực hành:</label>
                            <input type="number" step="0.1" min="0" max="10" class="form-control" 
                                id="practiceScore${student.studentId}" name="practiceScore" 
                                value="${existingScore != null ? existingScore.practiceScore : ''}" required>
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