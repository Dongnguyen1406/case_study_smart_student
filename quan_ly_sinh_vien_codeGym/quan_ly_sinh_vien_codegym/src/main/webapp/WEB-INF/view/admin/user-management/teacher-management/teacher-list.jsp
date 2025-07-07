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
            <th>Mã</th>
            <th>Họ tên</th>
            <th>Giới tính</th>
            <th>Ngày sinh</th>
            <th>Email</th>
            <%--            <th>Trạng thái</th>--%>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${teachers}" var="teacher" varStatus="temp">
            <tr>
                <td class="text-center">${startIndex + temp.count}</td>
                <td class="text-center">${teacher.teacherId}</td>
                <td class="text-center">${teacher.teacherName}</td>
                <td class="text-center">${teacher.gender}</td>
                <td class="text-center">${teacher.dob}</td>
                <td class="text-center">${teacher.email}</td>
                    <%--                <td class="text-center">--%>
                    <%--                    <span class="badge" style="background-color: #272882;">Hoạt động</span>--%>
                    <%--                </td>--%>
                <td class="text-center">
                    <button type="button" class="btn btn-sm btn-warning btn-edit"
                            data-id="${teacher.teacherId}"
                            data-fullname="${teacher.teacherName}"
                            data-gender="${teacher.gender}"
                            data-dob="${teacher.dob}"
                            data-address="${teacher.address}"
                            data-email="${teacher.email}"
                            data-phone="${teacher.numberPhone}"
                            data-status="${teacher.status ? 1 : 0}">
                        ✏️
                    </button>
                    <button type="button" class="btn btn-sm btn-danger btn-delete"
                            data-id="${teacher.teacherId}">
                        🗑️
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="d-flex justify-content-center mt-3">
        <jsp:include page="/WEB-INF/view/common/pagination.jsp"/>
    </div>
    <div class="modal fade" id="addTeacherModal" tabindex="-1" aria-labelledby="addTeacherModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form action="${basePath}/admin?page=addTeacher" method="post" class="needs-validation" novalidate>
                    <div class="modal-header">
                        <h5 class="modal-title" id="addTeacherModalLabel">➕ Thêm giáo viên mới</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label for="addFullname" class="form-label">Họ tên</label>
                                <input type="text" class="form-control" id="addFullname" name="fullname" required>
                                <div class="invalid-feedback">Vui lòng nhập họ tên.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="addGender" class="form-label">Giới tính</label>
                                <select id="addGender" name="gender" class="form-select" required>
                                    <option value="" selected disabled>Chọn giới tính</option>
                                    <option value="Nam">Nam</option>
                                    <option value="Nữ">Nữ</option>
                                </select>
                                <div class="invalid-feedback">Vui lòng chọn giới tính.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="addDob" class="form-label">Ngày sinh</label>
                                <input type="date" class="form-control" id="addDob" name="dob" required>
                                <div class="invalid-feedback">Vui lòng chọn ngày sinh.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addEmail" class="form-label">Email</label>
                                <input type="email" class="form-control" id="addEmail" name="email" required>
                                <div class="invalid-feedback">Vui lòng nhập email hợp lệ.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addAddress" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="addAddress" name="address" required>
                                <div class="invalid-feedback">Vui lòng nhập địa chỉ.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addPhone" class="form-label">Số điện thoại</label>
                                <input type="tel" class="form-control" id="addPhone" name="phone" required>
                                <div class="invalid-feedback">Vui lòng nhập số điện thoại.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addStatus" class="form-label">Trạng thái</label>
                                <select id="addStatus" name="status" class="form-select" required>
                                    <option value="1" selected>Hoạt động</option>
                                    <option value="0">Không hoạt động</option>
                                </select>
                                <div class="invalid-feedback">Vui lòng chọn trạng thái.</div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Lưu</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    
    <div class="modal fade" id="editTeacherModal" tabindex="-1" aria-labelledby="editTeacherModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form action="${basePath}/admin?page=updateTeacher" method="post" class="needs-validation" novalidate>
                    <input type="hidden" id="editId" name="id"/>
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTeacherModalLabel">✏️ Cập nhật giáo viên</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label for="editFullname" class="form-label">Họ tên</label>
                                <input type="text" class="form-control" id="editFullname" name="fullname" required>
                                <div class="invalid-feedback">Vui lòng nhập họ tên.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="editGender" class="form-label">Giới tính</label>
                                <select id="editGender" name="gender" class="form-select" required>
                                    <option value="" disabled>Chọn giới tính</option>
                                    <option value="Nam">Nam</option>
                                    <option value="Nữ">Nữ</option>
                                </select>
                                <div class="invalid-feedback">Vui lòng chọn giới tính.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="editDob" class="form-label">Ngày sinh</label>
                                <input type="date" class="form-control" id="editDob" name="dob" required>
                                <div class="invalid-feedback">Vui lòng chọn ngày sinh.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="editEmail" class="form-label">Email</label>
                                <input type="email" class="form-control" id="editEmail" name="email" required>
                                <div class="invalid-feedback">Vui lòng nhập email hợp lệ.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="editPhone" class="form-label">Số điện thoại</label>
                                <input type="tel" class="form-control" id="editPhone" name="phone" required>
                                <div class="invalid-feedback">Vui lòng nhập số điện thoại.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="editAddress" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="editAddress" name="address" required>
                                <div class="invalid-feedback">Vui lòng nhập địa chỉ.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="editStatus" class="form-label">Trạng thái</label>
                                <select id="editStatus" name="status" class="form-select" required>
                                    <option value="1">Hoạt động</option>
                                    <option value="0">Không hoạt động</option>
                                </select>
                                <div class="invalid-feedback">Vui lòng chọn trạng thái.</div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Cập nhật</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal Xác Nhận Xóa -->
    <div class="modal fade" id="confirmDeleteModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${basePath}/admin?page=deleteTeacher" method="post">
                    <input type="hidden" id="deleteId" name="id">
                    <div class="modal-header">
                        <h5 class="modal-title">Xác nhận xóa giáo viên</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn xóa giáo viên này không?
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-danger">Xác nhận</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

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
            document.getElementById('editId').value = btn.dataset.id;
            document.getElementById('editFullname').value = btn.dataset.fullname;
            document.getElementById('editGender').value = btn.dataset.gender;
            document.getElementById('editDob').value = btn.dataset.dob;
            document.getElementById('editAddress').value = btn.dataset.address;
            document.getElementById('editEmail').value = btn.dataset.email;
            document.getElementById('editPhone').value = btn.dataset.phone;
            document.getElementById('editStatus').value = btn.dataset.status;

            modal.show();
        });
    });
    
    const deleteButtons = document.querySelectorAll('.btn-delete');
    deleteButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
            document.getElementById('deleteId').value = btn.dataset.id;
            modal.show();
        });
    });
</script>
