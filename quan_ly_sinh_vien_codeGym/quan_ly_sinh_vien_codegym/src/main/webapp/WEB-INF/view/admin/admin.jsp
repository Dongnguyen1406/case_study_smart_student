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

<div id="main-container" class="container-fluid p-0 d-flex flex-column" style="height: 100vh;">
    <div class="row g-0 flex-grow-1" style="overflow-y: auto;">
        <%@ include file="/WEB-INF/view/admin/layout/sidebar.jsp" %>

        <div class="col d-flex flex-column">
            <%@ include file="/WEB-INF/view/admin/layout/header.jsp" %>

            <!-- Nội dung thay đổi dựa vào param.page -->
            <div class="p-3 flex-grow-1" id="main-content" style="overflow-y: auto; background-color: #f1f4f5">
            <c:set var="paramPage" value="${empty param.page ? 'dashboard' : param.page}"/>

                <c:choose>
                    <c:when test="${paramPage == 'dashboard'}">
                        <jsp:include page="/WEB-INF/view/admin/layout/dashboard-admin.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'students'}">
                        <jsp:include page="/WEB-INF/view/admin/user-management/student-management/student-list.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'teachers'}">
                        <jsp:include page="/WEB-INF/view/admin/user-management/teacher-management/teacher-list.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'classes'}">
                        <jsp:include page="/WEB-INF/view/admin/class-management/class-list.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'courses'}">
                        <jsp:include page="/WEB-INF/view/admin/course-management/course-list.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'modules'}">
                        <jsp:include page="/WEB-INF/view/admin/module-management/module-list.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <h5>Vui lòng chọn một mục từ sidebar để xem nội dung.</h5>
                    </c:otherwise>
                </c:choose>

            </div>

            <%@ include file="/WEB-INF/view/admin/layout/footer.jsp" %>
        </div>
    </div>
</div>
