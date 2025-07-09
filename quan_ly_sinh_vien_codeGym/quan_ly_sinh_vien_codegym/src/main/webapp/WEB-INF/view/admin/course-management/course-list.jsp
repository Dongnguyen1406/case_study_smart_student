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
    <button type="button" class="btn" id="btnAddCourse" style="background-color: #272882; color: #ffffff">
        Thêm khóa học
    </button>
</div>

<!-- Bảng danh sách -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light text-center">
        <tr>
            <th>STT</th>
            <th>Tên khóa học</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${courses}" var="course" varStatus="temp">
            <tr>
                <td class="text-center">${startIndex + temp.count}</td>
                <td class="text-center">${course.courseName}</td>
                <td class="text-center">
                    <button type="button" class="btn btn-sm  btn-edit"
                            data-id="${course.courseId}"
                            data-name="${course.courseName}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                    <button type="button" class="btn btn-sm  btn-delete"
                            data-id="${course.courseId}">
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
</div>

<!-- Modal Thêm Khóa Học -->
<div class="modal fade" id="addCourseModal" tabindex="-1" aria-labelledby="addCourseModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${basePath}/admin?page=addCourse" method="post" class="needs-validation">
            <div class="modal-header">
                    <h5 class="modal-title">Thêm khóa học</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Tên khóa học</label>
                        <input type="text" name="name" class="form-control"
                               pattern="^[a-zA-Z0-9\s]{3,50}$"
                               title="Tên khóa học chỉ được chứa chữ, số và khoảng trắng. Tối thiểu 3 ký tự."
                               required>

                        <div class="invalid-feedback">Vui lòng nhập tên khóa học.</div>
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

<!-- Modal Cập Nhật Khóa Học -->
<div class="modal fade" id="editCourseModal" tabindex="-1" aria-labelledby="editCourseModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${basePath}/admin?page=updateCourse" method="post" class="needs-validation" novalidate>
                <input type="hidden" name="id" id="editCourseId">
                <div class="modal-header">
                    <h5 class="modal-title">Cập nhật khóa học</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Tên khóa học</label>
                        <input type="text" name="name" id="editCourseName" class="form-control"
                               pattern="^[a-zA-Z0-9\s]{3,50}$"
                               title="Tên khóa học chỉ được chứa chữ, số và khoảng trắng. Tối thiểu 3 ký tự."
                               required>
                        <div class="invalid-feedback">Vui lòng nhập tên khóa học.</div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn" style="background-color: #272882; color: #ffffff">Cập nhật
                    </button>
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
            <form action="${basePath}/admin?page=deleteCourse" method="post">
                <input type="hidden" id="deleteId" name="id">
                <div class="modal-header">
                    <h5 class="modal-title">Xác nhận xóa khóa học</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    Bạn có chắc chắn muốn xóa khóa học này không?
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn" style="background-color: #272882; color: #ffffff">Xác nhận
                    </button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    // Mở modal thêm mới
    document.getElementById('btnAddCourse').addEventListener('click', () => {
        let modal = new bootstrap.Modal(document.getElementById('addCourseModal'));
        modal.show();
    });

    // Mở modal sửa và đổ dữ liệu
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('editCourseModal'));
            document.getElementById('editCourseId').value = button.dataset.id;
            document.getElementById('editCourseName').value = button.dataset.name;
            modal.show();
        });
    });

    // Mở modal xác nhận xóa
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
            document.getElementById('deleteId').value = button.dataset.id;
            modal.show();
        });
    });
</script>
<script>
    (() => {
        'use strict';
        const forms = document.querySelectorAll('.needs-validation');
        Array.from(forms).forEach(form => {
            form.addEventListener('submit', event => {
                if (!form.checkValidity()) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    })();
</script>




