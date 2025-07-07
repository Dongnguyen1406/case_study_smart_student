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
            <th>Mã</th>
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
                <td class="text-center">${startIndex + temp.count}</td>
                <td class="text-center">${clazz.classId}</td>
                <td class="text-center">${clazz.className}</td>
                <td class="text-center">${clazz.moduleName}</td>
                <td class="text-center">${clazz.courseName}</td>
                <td class="text-center">${clazz.teacherName}</td>
                <td class="text-center">${clazz.startDate}</td>
                <td class="text-center">${clazz.quantity}</td>
                <td class="text-center">
                    <button type="button" class="btn btn-sm btn-warning btn-edit-class"
                            data-id="${clazz.classId}"
                            data-name="${clazz.className}"
                            data-module="${clazz.moduleName}"
                            data-course="${clazz.courseName}"
                            data-teacher="${clazz.teacherName}"
                            data-start="${clazz.startDate}"
                            data-size="${clazz.quantity}">
                        ✏️
                    </button>
                    <button type="button" class="btn btn-sm btn-danger btn-delete"
                            data-id="${clazz.classId}">
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
    <!-- Modal Thêm Lớp -->
    <div class="modal fade" id="addClassModal" tabindex="-1" aria-labelledby="addClassModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form action="${basePath}/admin?page=addClass" method="post" class="needs-validation" novalidate>
                    <div class="modal-header">
                        <h5 class="modal-title" id="addClassModalLabel">➕ Thêm lớp học mới</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label for="addClassCode" class="form-label">Mã lớp</label>
                                <input type="text" class="form-control" id="addClassCode" name="classCode" required>
                                <div class="invalid-feedback">Vui lòng nhập mã lớp.</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addClassName" class="form-label">Tên lớp</label>
                                <input type="text" class="form-control" id="addClassName" name="className" required>
                                <div class="invalid-feedback">Vui lòng nhập tên lớp.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="addModule" class="form-label">Module</label>
                                <select id="addModule" name="module" class="form-select" required>
                                    <c:forEach items="${modules}" var="module">
                                        <option value="${module.moduleId}">${module.moduleName}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback">Vui lòng chọn module</div>
                            </div>
                            <div class="col-md-3">
                                <label for="addCourse" class="form-label">Course</label>
                                <select id="addCourse" name="course" class="form-select" required>
                                    <c:forEach items="${courses}" var="course">
                                        <option value="${course.courseId}">${course.courseName}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback">Vui lòng chọn course</div>
                            </div>
                            <div class="col-md-6">
                                <label for="addClassTeacher" class="form-label">Giáo viên chủ nhiệm</label>
                                <select id="addClassTeacher" name="teacher" class="form-select" required>
                                    <c:forEach items="${teachers}" var="teacher">
                                        <option value="${teacher.teacherId}">${teacher.teacherName}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback">Vui lòng nhập tên giáo viên.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="addClassSize" class="form-label">Sĩ số</label>
                                <input type="number" class="form-control" id="addClassSize" name="size" min="1" required>
                                <div class="invalid-feedback">Vui lòng nhập sĩ số hợp lệ.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="addStartDate" class="form-label">Ngày bắt đầu</label>
                                <input type="date" class="form-control" id="addStartDate" name="startDate" required>
                                <div class="invalid-feedback">Vui lòng chọn ngày bắt đầu.</div>
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
    <!-- Modal Cập Nhật Lớp -->
    <div class="modal fade" id="editClassModal" tabindex="-1" aria-labelledby="editClassModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <form action="${basePath}/admin?page=updateClass" method="post" class="needs-validation" novalidate>
                    <input type="hidden" id="editClassId" name="id"/>
                    <div class="modal-header">
                        <h5 class="modal-title" id="editClassModalLabel">✏️ Cập nhật lớp học</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row g-3">
                            <div class="col-md-6">
                                <label for="editClassName" class="form-label">Tên lớp</label>
                                <input type="text" class="form-control" id="editClassName" name="className" required>
                                <div class="invalid-feedback">Vui lòng nhập tên lớp.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="editModule" class="form-label">Module</label>
                                <select id="editModule" name="module" class="form-select" required>
                                    <c:forEach items="${modules}" var="module">
                                        <option value="${module.moduleId}">${module.moduleName}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback">Vui lòng chọn module</div>
                            </div>
                            <div class="col-md-3">
                                <label for="editCourse" class="form-label">Course</label>
                                <select id="editCourse" name="course" class="form-select" required>
                                    <c:forEach items="${courses}" var="course">
                                        <option value="${course.courseId}">${course.courseName}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback">Vui lòng chọn course</div>
                            </div>
                            <div class="col-md-6">
                                <label for="editClassTeacher" class="form-label">Giáo viên chủ nhiệm</label>
                                <select id="editClassTeacher" name="teacher" class="form-select" required>
                                    <c:forEach items="${teachers}" var="teacher">
                                        <option value="${teacher.teacherId}">${teacher.teacherName}</option>
                                    </c:forEach>
                                </select>
                                <div class="invalid-feedback">Vui lòng nhập tên giáo viên.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="editClassSize" class="form-label">Sĩ số</label>
                                <input type="number" class="form-control" id="editClassSize" name="size" min="1" required>
                                <div class="invalid-feedback">Vui lòng nhập sĩ số hợp lệ.</div>
                            </div>
                            <div class="col-md-3">
                                <label for="editStartDate" class="form-label">Ngày bắt đầu</label>
                                <input type="date" class="form-control" id="editStartDate" name="startDate" required>
                                <div class="invalid-feedback">Vui lòng chọn ngày bắt đầu.</div>
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
    <!-- Modal Xác Nhận Xóa -->
    <div class="modal fade" id="confirmDeleteModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${basePath}/admin?page=deleteClass" method="post">
                    <input type="hidden" id="deleteId" name="id">
                    <div class="modal-header">
                        <h5 class="modal-title">Xác nhận xóa module</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        Bạn có chắc chắn muốn xóa module này không?
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
    document.getElementById('btnAddClass').addEventListener('click', () => {
        let addModal = new bootstrap.Modal(document.getElementById('addClassModal'));
        addModal.show();
    });

    // Khi bấm nút Sửa thì mở modal sửa và điền dữ liệu
    const editClassButtons = document.querySelectorAll('.btn-edit-class');
    editClassButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('editClassModal'));
            document.getElementById('editClassId').value = btn.dataset.id;
            document.getElementById('editClassName').value = btn.dataset.name;
            document.getElementById('editModule').value = btn.dataset.module;
            document.getElementById('editCourse').value = btn.dataset.course;
            document.getElementById('editClassTeacher').value = btn.dataset.teacher;
            document.getElementById('editClassSize').value = btn.dataset.size;

            modal.show();
        });
    });

    const deleteClassButtons = document.querySelectorAll('.btn-delete');
    deleteClassButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
            document.getElementById('deleteId').value = btn.dataset.id;
            modal.show();
        });
    });
</script>
