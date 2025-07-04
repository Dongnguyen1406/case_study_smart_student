<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 1:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<h2 class="mb-4">🏫 Danh sách lớp học</h2>

<!-- Nút thêm -->
<div class="mb-3 text-end">
    <button type="button" class="btn" id="btnAddClass" style="background-color: #272882; color: #ffffff">Thêm lớp học
    </button>
</div>

<!-- Bảng danh sách -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light text-center">
        <tr>
            <th>STT</th>
            <th>Tên lớp</th>
            <th>Module</th>
            <th>Course</th>
            <th>Giáo viên dạy</th>
            <th>Ngày bắt đầu</th>
            <th>Số lượng học sinh</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${classes}" var="clazz" varStatus="temp">
            <tr>
                <td class="text-center">${temp.count}</td>
                <td class="text-center">${clazz.className}</td>
                <td class="text-center">${clazz.moduleName}</td>
                <td class="text-center">${clazz.courseName}</td>
                <td class="text-center">${clazz.teacherName}</td>
                <td class="text-center">${clazz.startDate}</td>
                <td class="text-center">${clazz.quantity}</td>
                <td class="text-center">
                    <button type="button" class="btn btn-sm btn-warning btn-edit-class"
                            data-id="1" data-code="LH01" data-name="Lớp 10A1"
                            data-teacher="Nguyễn Văn C" data-size="40" data-status="1">
                        ✏️
                    </button>
                    <a href="#" class="btn btn-sm btn-danger">🗑️</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center mt-3">
        <jsp:include page="/WEB-INF/view/common/pagination.jsp"/>
    </div>

</div>

<!-- Include modal thêm mới và sửa -->
<jsp:include page="class-form.jsp"/>
<jsp:include page="class-update.jsp"/>

<script>
    // Mở modal thêm mới khi bấm nút
    document.getElementById('btnAddClass').addEventListener('click', () => {
        let addModal = new bootstrap.Modal(document.getElementById('addClassModal'));
        addModal.show();
    });

    // Khi bấm nút Sửa thì mở modal sửa và điền dữ liệu
    const editButtons = document.querySelectorAll('.btn-edit-class');
    editButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('editClassModal'));

            const id = btn.getAttribute('data-id');
            const code = btn.getAttribute('data-code');
            const name = btn.getAttribute('data-name');
            const teacher = btn.getAttribute('data-teacher');
            const size = btn.getAttribute('data-size');
            const status = btn.getAttribute('data-status');

            // Gán dữ liệu vào form sửa
            document.getElementById('editClassId').value = id;
            document.getElementById('editClassCode').value = code;
            document.getElementById('editClassName').value = name;
            document.getElementById('editClassTeacher').value = teacher;
            document.getElementById('editClassSize').value = size;
            document.getElementById('editClassStatus').value = status;

            modal.show();
        });
    });
</script>
