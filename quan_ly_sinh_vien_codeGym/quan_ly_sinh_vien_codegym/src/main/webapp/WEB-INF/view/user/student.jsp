<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/2/2025
  Time: 11:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<div id="main-container" class="container-fluid p-0 d-flex flex-column" style="height: 100vh;">
    <div class="row g-0 flex-grow-1" style="overflow-y: auto;">
        <%@ include file="/WEB-INF/view/common/layout/sidebar.jsp" %>

        <div class="col d-flex flex-column">
            <%@ include file="/WEB-INF/view/common/layout/header.jsp" %>

            <!-- Nội dung thay đổi dựa vào param.page -->
            <div class="p-3 flex-grow-1" id="main-content" style="overflow-y: auto; background-color: #f1f4f5">
                <c:set var="paramPage" value="${param.page != null ? param.page : 'dashboard'}"/>

                <c:choose>
                    <c:when test="${paramPage == 'dashboard'}">
                        <jsp:include page="/WEB-INF/view/user/student-layout/dashboard-student.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'score'}">
                        <jsp:include page="/WEB-INF/view/user/student-layout/score.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'attendance'}">
                        <jsp:include page="/WEB-INF/view/user/student-layout/attendance.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'assessment'}">
                        <jsp:include page="/WEB-INF/view/user/student-layout/assessment.jsp"/>
                    </c:when>
                    <c:when test="${paramPage == 'profile'}">
                        <jsp:include page="/WEB-INF/view/user/student-profile.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <h5>Vui lòng chọn một mục từ sidebar để xem nội dung.</h5>
                    </c:otherwise>
                </c:choose>

            </div>

        </div>
        <div class="p-3">
            <h2 class="mb-4">📝 Đánh giá</h2>
            <table class="table table-hover table-bordered">
                <thead class="table-primary">
                <tr>
                    <th> Tên</th>
                    <th> Ngày sinh</th>
                    <th> Giới tính</th>
                    <th> Địa chỉ</th>
                    <th> Số điện thoại</th>
                    <th> Email</th>
                    <th> Ngày bắt đầu nhập học</th>
                    <th> Tên lớp</th>
                    <th> Chỉnh sửa</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${student.studentName}</td>
                    <td>${student.dob}</td>
                    <td>${student.gender}</td>
                    <td>${student.address}</td>
                    <td>${student.numberPhone}</td>
                    <td>${student.email}</td>
                    <td>${student.startLearnDate}</td>
                    <td>${student.className}</td>
                    <td><button onclick="window.location.href=`/student?action=update`"><i
                            class="bi bi-pencil text-primary"></i></button>
                    </td>
                    </button>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>


<%@ include file="/WEB-INF/view/common/layout/footer.jsp" %>


