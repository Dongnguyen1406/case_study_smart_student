<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 1:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<h2 class="mb-4">🏫 Danh sách lớp học</h2>

<!-- Nút thêm -->
<div class="mb-3 text-end">
    <button type="button" class="btn btn-primary" id="btnAddClass">
        ➕ Thêm lớp học
    </button>
</div>

<!-- Bảng danh sách -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light text-center">
        <tr>
            <th>STT</th>
            <th>Mã lớp</th>
            <th>Tên lớp</th>
            <th>Giáo viên chủ nhiệm</th>
            <th>Sĩ số</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="text-center">1</td>
            <td>LH01</td>
            <td>Lớp 10A1</td>
            <td>Nguyễn Văn C</td>
            <td class="text-center">40</td>
            <td class="text-center">
                <span class="badge bg-success">Đang hoạt động</span>
            </td>
            <td class="text-center">
                <button type="button" class="btn btn-sm btn-warning btn-edit-class"
                        data-id="1" data-code="LH01" data-name="Lớp 10A1"
                        data-teacher="Nguyễn Văn C" data-size="40" data-status="1">
                    ✏️ Sửa
                </button>
                <a href="#" class="btn btn-sm btn-danger">🗑️ Xóa</a>
            </td>
        </tr>
        <tr>
            <td class="text-center">2</td>
            <td>LH02</td>
            <td>Lớp 10B2</td>
            <td>Trần Thị D</td>
            <td class="text-center">38</td>
            <td class="text-center">
                <span class="badge bg-secondary">Ngưng hoạt động</span>
            </td>
            <td class="text-center">
                <button type="button" class="btn btn-sm btn-warning btn-edit-class"
                        data-id="2" data-code="LH02" data-name="Lớp 10B2"
                        data-teacher="Trần Thị D" data-size="38" data-status="0">
                    ✏️ Sửa
                </button>
                <a href="#" class="btn btn-sm btn-danger">🗑️ Xóa</a>
            </td>
        </tr>
        </tbody>
    </table>
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
