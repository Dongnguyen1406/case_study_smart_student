<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<h2 class="mb-4">📚 Danh sách khóa học</h2>

<!-- Nút thêm -->
<div class="mb-3 text-end">
    <button class="btn btn-primary" id="btnAddCourse">➕ Thêm khóa học</button>
</div>

<!-- Bảng danh sách -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light text-center">
        <tr>
            <th>STT</th>
            <th>Tên khóa học</th>
            <th>Mã khóa học</th>
            <th>Thời gian (tháng)</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="text-center">1</td>
            <td>Java Core</td>
            <td>JC101</td>
            <td class="text-center">6</td>
            <td class="text-center">
                <span class="badge bg-success">Đang mở</span>
            </td>
            <td class="text-center">
                <button class="btn btn-sm btn-warning btn-edit"
                        data-id="1" data-name="Java Core" data-code="JC101"
                        data-duration="6" data-status="1">✏️ Sửa</button>
                <a href="#" class="btn btn-sm btn-danger">🗑️ Xóa</a>
            </td>
        </tr>
        <tr>
            <td class="text-center">2</td>
            <td>Web Development</td>
            <td>WD202</td>
            <td class="text-center">4</td>
            <td class="text-center">
                <span class="badge bg-secondary">Đã đóng</span>
            </td>
            <td class="text-center">
                <button class="btn btn-sm btn-warning btn-edit"
                        data-id="2" data-name="Web Development" data-code="WD202"
                        data-duration="4" data-status="0">✏️ Sửa</button>
                <a href="#" class="btn btn-sm btn-danger">🗑️ Xóa</a>
            </td>
        </tr>
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


