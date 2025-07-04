<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<h2 class="mb-4">📚 Danh sách khóa học</h2>

<!-- Nút thêm -->
<div class="mb-3 text-end">
    <button class="btn" id="btnAddCourse" style="background-color: #272882; color: #ffffff">Thêm khóa học</button>
</div>

<!-- Bảng danh sách -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light text-center">
        <tr>
            <th>STT</th>
            <th>Tên khóa học</th>
<%--            <th>Trạng thái</th>--%>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${courses}" var="course" varStatus="temp">
            <tr>
                <td class="text-center">${temp.count}</td>
                <td class="text-center">${course.courseName}</td>
<%--                <td class="text-center">--%>
<%--                    <span class="badge" style="background-color: #272882;">Đang mở</span>--%>
<%--                </td>--%>
                <td class="text-center">
                    <button class="btn btn-sm btn-warning btn-edit"
                            data-id="1" data-name="Java Core" data-code="JC101"
                            data-duration="6" data-status="1">✏️ </button>
                    <a href="#" class="btn btn-sm btn-danger">🗑️ </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Gọi modal thêm & sửa -->
<jsp:include page="course-form.jsp"/>
<jsp:include page="course-update.jsp"/>

<script>
    // Modal thêm
    document.getElementById('btnAddCourse').addEventListener('click', () => {
        const modal = new bootstrap.Modal(document.getElementById('addCourseModal'));
        modal.show();
    });

    // Modal sửa
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('editCourseModal'));

            document.getElementById('editCourseId').value = button.dataset.id;
            document.getElementById('editCourseName').value = button.dataset.name;
            document.getElementById('editCourseCode').value = button.dataset.code;
            document.getElementById('editCourseDuration').value = button.dataset.duration;
            document.getElementById('editCourseStatus').value = button.dataset.status;

            modal.show();
        });
    });
</script>


