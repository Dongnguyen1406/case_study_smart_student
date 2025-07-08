<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý giáo viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div id="main-container" class="container-fluid p-0 d-flex flex-column" style="height: 100vh;">
    <div class="row g-0 flex-grow-1" style="overflow-y: auto;">
        <%@ include file="/WEB-INF/view/teacher/sidebar.jsp" %>
        <div class="col d-flex flex-column">
            <%@ include file="/WEB-INF/view/teacher/header.jsp" %>
            <div class="p-3 flex-grow-1" id="main-content" style="overflow-y: auto; background-color: #f1f4f5">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
                <c:if test="${not empty successMessage}">
                    <div class="alert alert-success">${successMessage}</div>
                </c:if>
                <c:set var="paramPage" value="${param.page != null ? param.page : 'dashboard'}"/>
                <c:choose>
                    <c:when test="${paramPage == 'dashboard'}">
                        <jsp:include page="/WEB-INF/view/teacher/dashboard-teacher.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'score'}">
                        <jsp:include page="/WEB-INF/view/teacher/score.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'attendance'}">
                        <jsp:include page="/WEB-INF/view/teacher/attendance.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'assessment'}">
                        <jsp:include page="/WEB-INF/view/teacher/assessment.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <h5 class="text-muted">Vui lòng chọn một mục từ sidebar để xem nội dung.</h5>
                    </c:otherwise>
                </c:choose>
            </div>
            <%@ include file="/WEB-INF/view/common/layout/footer.jsp" %>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>