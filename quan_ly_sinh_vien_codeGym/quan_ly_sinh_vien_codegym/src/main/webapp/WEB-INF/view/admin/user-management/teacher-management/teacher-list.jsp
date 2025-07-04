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

<h2 class="mb-4">👩‍🏫 Danh sách giáo viên</h2>

<!-- Nút thêm -->
<div class="mb-3 text-end">
    <button type="button" class="btn" id="btnAddTeacher" style="background-color: #272882; color: #ffffff">
        Thêm giáo viên
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
            <th>Địa chỉ</th>
            <th>Email</th>
            <th>Số điện thoại</th>
<%--            <th>Trạng thái</th>--%>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers}" var="teacher" varStatus="temp">
            <tr>
                <td class="text-center">${temp.count}</td>
                <td class="text-center">${teacher.teacherName}</td>
                <td class="text-center">${teacher.gender}</td>
                <td class="text-center">${teacher.dob}</td>
                <td class="text-center">${teacher.address}</td>
                <td class="text-center">${teacher.email}</td>
                <td class="text-center">${teacher.numberPhone}</td>
<%--                <td class="text-center">--%>
<%--                    <span class="badge" style="background-color: #272882;">Hoạt động</span>--%>
<%--                </td>--%>
                <td class="text-center">
                    <button type="button" class="btn btn-sm btn-warning btn-edit"
                            data-id="1" data-fullname="Phạm Văn C" data-gender="Nam" data-dob="1980-07-15"
                            data-email="phamc@example.com" data-phone="0987654321" data-status="1">
                        ✏️
                    </button>
                    <a href="#" class="btn btn-sm btn-danger">🗑️ </a>
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
