<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<h2 class="mb-4">👨‍🎓 Danh sách học sinh</h2>
<c:if test="${not empty param.msg}">
    <div class="alert alert-success">
        <c:if test="${param.msg == 'updateSuccess'}">Cập nhật học sinh thành công!</c:if>
    </div>
</c:if>
<c:if test="${not empty param.error}">
    <div class="alert alert-danger">
        <c:choose>
            <c:when test="${param.error == 'updateFailed'}">Cập nhật học sinh thất bại. Vui lòng thử lại!</c:when>
        </c:choose>
    </div>
</c:if>

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
        <tr>
            <th>STT</th>
            <th>Mã</th>
            <th>Họ tên</th>
            <th>Giới tính</th>
            <th>Ngày sinh</th>
            <th>Email</th>
            <th>Lớp học</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${students}" var="student" varStatus="temp">
            <tr>
                <td class="text-center">${startIndex + temp.count}</td>
                <td class="text-center">${student.studentId}</td>
                <td class="text-center">${student.studentName}</td>
                <td class="text-center">${student.gender}</td>
                <td class="text-center">${student.dob}</td>
                <td class="text-center">${student.email}</td>
                <td class="text-center">${student.className}</td>
                    <%--                <td class="text-center">--%>
                    <%--                    <span class="badge" style="background-color: #272882;">Hoạt động</span>--%>
                    <%--                </td>--%>
                <td class="text-center">
                    <button type="button" class="btn btn-sm btn-edit"
                            data-id="${student.studentId}"
                            data-fullname="${student.studentName}"
                            data-gender="${student.gender}"
                            data-dob="${student.dob}"
                            data-address="${student.address}"
                            data-email="${student.email}"
                            data-phone="${student.numberPhone}"
                            data-classid="${student.classId}"
                            data-startdate="${student.startLearnDate}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                    <button type="button" class="btn btn-sm btn-delete"
                            data-id="${student.studentId}">
                        <i class="bi bi-trash-fill"></i>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <div class="d-flex justify-content-center mt-3">
        <jsp:include page="/WEB-INF/view/common/pagination.jsp"/>
    </div>
    <!-- Modal Thêm Học Sinh -->
    <div class="modal fade" id="addStudentModal" tabindex="-1" aria-labelledby="addStudentModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form action="${basePath}/admin?page=addStudent" method="post" class="needs-validation" novalidate>
                <div class="modal-header">
                        <h5 class="modal-title" id="addStudentModalLabel">Thêm học sinh mới</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label for="addId" class="form-label">Mã học sinh</label>
                                <input type="text" class="form-control" id="addId" name="studentId" required>
                                <div class="invalid-feedback">Vui lòng mã học sinh.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addFullname" class="form-label">Họ tên</label>
                                <input type="text" name="studentName" id="addFullname" class="form-control" required>
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
                                <input type="date" name="dob" id="addDob" class="form-control" required>
                                <div class="invalid-feedback">Vui lòng chọn ngày sinh.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addAddress" class="form-label">Địa chỉ</label>
                                <input type="text" name="address" id="addAddress" class="form-control" required>
                                <div class="invalid-feedback">Vui lòng nhập địa chỉ.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addEmail" class="form-label">Email</label>
                                <input type="email" name="email" id="addEmail" class="form-control" required>
                                <div class="invalid-feedback">Vui lòng nhập email hợp lệ.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addPhone" class="form-label">Số điện thoại</label>
                                <input type="text" name="numberPhone" id="addPhone" class="form-control" required>
                                <div class="invalid-feedback">Vui lòng nhập số điện thoại.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="addStartDate" class="form-label">Ngày bắt đầu học</label>
                                <input type="date" name="startLearnDate" id="addStartDate" class="form-control" required>
                                <div class="invalid-feedback">Vui lòng chọn ngày bắt đầu học.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addClassName" class="form-label">Lớp học</label>
                                <select id="addClassName" name="classId" class="form-select" required>
                                    <c:forEach items="${classes}" var="clazz">
                                        <option value="${clazz.classId}">${clazz.className}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback">Vui lòng nhập lớp học.</div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn" style="background-color: #272882; color: #ffffff">Lưu</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal Cập Nhật Học Sinh -->
    <div class="modal fade" id="editStudentModal" tabindex="-1" aria-labelledby="editStudentModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form action="${basePath}/admin?page=updateStudent" method="post" class="needs-validation" novalidate>
                    <!-- Hidden để giữ lại studentId khi gửi về server -->
                    <input type="hidden" id="editId" name="studentId"/>
                    <div class="modal-header">
                        <h5 class="modal-title" id="editStudentModalLabel">Cập nhật học sinh</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label for="editFullname" class="form-label">Họ tên</label>
                                <input type="text" class="form-control" id="editFullname" name="studentName" required>
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
                                <label for="editAddress" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="editAddress" name="address" required>
                                <div class="invalid-feedback">Vui lòng nhập địa chỉ.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="editEmail" class="form-label">Email</label>
                                <input type="email" class="form-control" id="editEmail" name="email" required>
                                <div class="invalid-feedback">Vui lòng nhập email hợp lệ.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="editPhone" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="editPhone" name="numberPhone" required>
                                <div class="invalid-feedback">Vui lòng nhập số điện thoại.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="editStartDate" class="form-label">Ngày bắt đầu học</label>
                                <input type="date" class="form-control" id="editStartDate" name="startLearnDate" required>
                                <div class="invalid-feedback">Vui lòng chọn ngày bắt đầu học.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="editClassName" class="form-label">Lớp học</label>
                                <select id="editClassName" name="classId" class="form-select" required>
                                    <c:forEach items="${classes}" var="clazz">
                                        <option value="${clazz.classId}">${clazz.className}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback">Vui lòng chọn lớp học.</div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn" style="background-color: #272882; color: #ffffff">Cập nhật</button>
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
                <form action="${basePath}/admin?page=deleteStudent" method="post">
                    <input type="hidden" id="deleteId" name="id">
                    <div class="modal-header">
                        <h5 class="modal-title">Xác nhận xóa học sinh</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn xóa học sinh này không?
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn" style="background-color: #272882; color: #ffffff">Xác nhận</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    document.getElementById('btnAddStudent').addEventListener('click', () => {
        let addModal = new bootstrap.Modal(document.getElementById('addStudentModal'));
        addModal.show();
    });

    const editButtons = document.querySelectorAll('.btn-edit');
    editButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('editStudentModal'));
            document.getElementById('editId').value = btn.dataset.id;
            document.getElementById('editFullname').value = btn.dataset.fullname;
            document.getElementById('editGender').value = btn.dataset.gender;
            document.getElementById('editDob').value = btn.dataset.dob;
            document.getElementById('editAddress').value = btn.dataset.address;
            document.getElementById('editEmail').value = btn.dataset.email;
            document.getElementById('editPhone').value = btn.dataset.phone;
            document.getElementById('editStartDate').value = btn.dataset.startdate;
            document.getElementById('editClassName').value = btn.dataset.classid;
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



