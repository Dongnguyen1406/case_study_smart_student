<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<h2 class="mb-4">👨‍🎓 Danh sách học sinh</h2>

<!-- Nút thêm -->
<div class="mb-3 text-end">
    <button type="button" class="btn" id="btnAddStudent" style="background-color: #272882; color: #ffffff">
        Thêm học sinh
    </button>
</div>

<!-- Bảng danh sách -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light text-center">
        <tr >
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
            <td>Nguyễn Văn A</td>
            <td>Nam</td>
            <td>2005-01-12</td>
            <td>vana@example.com</td>
            <td>0912345678</td>
            <td class="text-center">
                <span class="badge" style="background-color: #272882;">Hoạt động</span>
            </td>
            <td class="text-center">
                <button type="button" class="btn btn-sm btn-warning btn-edit" data-id="1" data-fullname="Nguyễn Văn A" data-gender="Nam" data-dob="2005-01-12" data-email="vana@example.com" data-phone="0912345678" data-status="1">
                    ✏️
                </button>
                <a href="#" class="btn btn-sm btn-danger">🗑️</a>
            </td>
        </tr>
        <tr>
            <td class="text-center">2</td>
            <td>Trần Thị B</td>
            <td>Nữ</td>
            <td>2006-03-25</td>
            <td>thib@example.com</td>
            <td>0901234567</td>
            <td class="text-center">
                <span class="badge bg-secondary">Không hoạt động</span>
            </td>
            <td class="text-center">
                <button type="button" class="btn btn-sm btn-warning btn-edit" data-id="2" data-fullname="Trần Thị B" data-gender="Nữ" data-dob="2006-03-25" data-email="thib@example.com" data-phone="0901234567" data-status="0">
                    ✏️ 
                </button>
                <a href="#" class="btn btn-sm btn-danger">🗑️ </a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<!-- Include modal thêm mới và sửa -->
<jsp:include page="student-form.jsp"/>
<jsp:include page="student-update.jsp"/>

<script>
    // Mở modal thêm mới khi bấm nút
    document.getElementById('btnAddStudent').addEventListener('click', () => {
        let addModal = new bootstrap.Modal(document.getElementById('addStudentModal'));
        addModal.show();
    });

    // Khi bấm nút Sửa thì mở modal sửa và điền dữ liệu
    const editButtons = document.querySelectorAll('.btn-edit');
    editButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('editStudentModal'));
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


