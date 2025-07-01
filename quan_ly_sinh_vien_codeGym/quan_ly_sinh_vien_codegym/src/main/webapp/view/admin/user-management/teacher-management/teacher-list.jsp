<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<h2 class="mb-4">👩‍🏫 Danh sách giáo viên</h2>

<!-- Nút thêm -->
<div class="mb-3 text-end">
    <button type="button" class="btn btn-primary" id="btnAddTeacher">
        ➕ Thêm giáo viên
    </button>
</div>

<!-- Bảng danh sách -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light text-center">
        <tr>
            <th>STT</th>
            <th>Họ tên</th>
            <th>Giới tính</th>
            <th>Ngày sinh</th>
            <th>Email</th>
            <th>Số điện thoại</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="text-center">1</td>
            <td>Phạm Văn C</td>
            <td>Nam</td>
            <td>1980-07-15</td>
            <td>phamc@example.com</td>
            <td>0987654321</td>
            <td class="text-center">
                <span class="badge bg-success">Hoạt động</span>
            </td>
            <td class="text-center">
                <button type="button" class="btn btn-sm btn-warning btn-edit"
                        data-id="1" data-fullname="Phạm Văn C" data-gender="Nam" data-dob="1980-07-15"
                        data-email="phamc@example.com" data-phone="0987654321" data-status="1">
                    ✏️ Sửa
                </button>
                <a href="#" class="btn btn-sm btn-danger">🗑️ Xóa</a>
            </td>
        </tr>
        <tr>
            <td class="text-center">2</td>
            <td>Trần Thị D</td>
            <td>Nữ</td>
            <td>1985-11-23</td>
            <td>trand@example.com</td>
            <td>0976543210</td>
            <td class="text-center">
                <span class="badge bg-secondary">Không hoạt động</span>
            </td>
            <td class="text-center">
                <button type="button" class="btn btn-sm btn-warning btn-edit"
                        data-id="2" data-fullname="Trần Thị D" data-gender="Nữ" data-dob="1985-11-23"
                        data-email="trand@example.com" data-phone="0976543210" data-status="0">
                    ✏️ Sửa
                </button>
                <a href="#" class="btn btn-sm btn-danger">🗑️ Xóa</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<!-- Include modal thêm mới và sửa -->
<jsp:include page="teacher-form.jsp"/>
<jsp:include page="teacher-update.jsp"/>

<script>
    // Mở modal thêm mới khi bấm nút
    document.getElementById('btnAddTeacher').addEventListener('click', () => {
        let addModal = new bootstrap.Modal(document.getElementById('addTeacherModal'));
        addModal.show();
    });

    // Khi bấm nút Sửa thì mở modal sửa và điền dữ liệu
    const editButtons = document.querySelectorAll('.btn-edit');
    editButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('editTeacherModal'));
            // Lấy dữ liệu từ data-attributes
            const id = btn.getAttribute('data-id');
            const fullname = btn.getAttribute('data-fullname');
            const gender = btn.getAttribute('data-gender');
            const dob = btn.getAttribute('data-dob');
            const email = btn.getAttribute('data-email');
            const phone = btn.getAttribute('data-phone');
            const status = btn.getAttribute('data-status');

            // Gán dữ liệu vào form sửa
            document.getElementById('editId').value = id;
            document.getElementById('editFullname').value = fullname;
            document.getElementById('editGender').value = gender;
            document.getElementById('editDob').value = dob;
            document.getElementById('editEmail').value = email;
            document.getElementById('editPhone').value = phone;
            document.getElementById('editStatus').value = status;

            modal.show();
        });
    });
</script>
