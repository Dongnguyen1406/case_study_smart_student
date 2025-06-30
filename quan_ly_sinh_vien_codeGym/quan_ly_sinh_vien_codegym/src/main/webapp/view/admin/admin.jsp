<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<c:set var="adminAssetsPath" value="${basePath}/assets/admin"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <%--    <link rel="stylesheet" href="${adminAssetsPath}/css/styles.min.css"/>--%>
</head>
<body>
<!--  Body Wrapper -->
<div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
     data-sidebar-position="fixed" data-header-position="fixed">

    <!--  App Topstrip -->
    <jsp:include page="layout/topstrip.jsp"/>
    <!-- Sidebar Start -->
    <jsp:include page="layout/sidebar.jsp"/>
    <!--  Sidebar End -->
    <!--  Main wrapper -->
    <div class="body-wrapper" style="margin-left: 220px;">
        <!-- Header -->
        <jsp:include page="layout/header.jsp"/>

        <!-- Nội dung chính thay đổi theo sidebar -->
        <div class="container-fluid mt-4">
            <c:choose>
                <c:when test="${param.page == 'dashboard'}">
                    <jsp:include page="dashboard-admin.jsp"/>
                </c:when>
                <c:when test="${param.page == 'studentForm'}">
                    <jsp:include page="student-management/student-form.jsp"/>
                </c:when>
                <c:when test="${param.page == 'studentUpdate'}">
                    <jsp:include page="student-management/student-update.jsp"/>
                </c:when>
                <c:when test="${param.page == 'teacherForm'}">
                    <jsp:include page="teacher-management/teacher-form.jsp"/>
                </c:when>
                <c:when test="${param.page == 'teacherUpdate'}">
                    <jsp:include page="teacher-management/teacher-update.jsp"/>
                </c:when>
                <c:when test="${param.page == 'students'}">
                    <jsp:include page="student-management/student-list.jsp"/>
                </c:when>
                <c:when test="${param.page == 'teachers'}">
                    <jsp:include page="teacher-management/teacher-list.jsp"/>
                </c:when>
                <c:when test="${param.page == 'courses'}">
                    <jsp:include page="course-management/course-list.jsp"/>
                </c:when>
                <c:when test="${param.page == 'modules'}">
                    <jsp:include page="module-management/module-list.jsp"/>
                </c:when>
                <c:otherwise>
                    <h5>Vui lòng chọn một mục từ sidebar để bắt đầu.</h5>
                </c:otherwise>
            </c:choose>
        </div>

        <!-- Footer -->
        <jsp:include page="layout/footer.jsp"/>
    </div>

</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</html>
