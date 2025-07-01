<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 1:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="editClassModal" tabindex="-1" aria-labelledby="editClassModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form action="${basePath}/class/update" method="post" class="needs-validation" novalidate>
                <input type="hidden" id="editClassId" name="id" value=""/>
                <div class="modal-header">
                    <h5 class="modal-title" id="editClassModalLabel">✏️ Cập nhật lớp học</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label for="editClassCode" class="form-label">Mã lớp</label>
                            <input type="text" class="form-control" id="editClassCode" name="classCode" required>
                            <div class="invalid-feedback">Vui lòng nhập mã lớp.</div>
                        </div>
                        <div class="col-md-6">
                            <label for="editClassName" class="form-label">Tên lớp</label>
                            <input type="text" class="form-control" id="editClassName" name="className" required>
                            <div class="invalid-feedback">Vui lòng nhập tên lớp.</div>
                        </div>
                        <div class="col-md-6">
                            <label for="editClassTeacher" class="form-label">Giáo viên chủ nhiệm</label>
                            <input type="text" class="form-control" id="editClassTeacher" name="teacher" required>
                            <div class="invalid-feedback">Vui lòng nhập tên giáo viên.</div>
                        </div>
                        <div class="col-md-3">
                            <label for="editClassSize" class="form-label">Sĩ số</label>
                            <input type="number" class="form-control" id="editClassSize" name="size" min="1" required>
                            <div class="invalid-feedback">Vui lòng nhập sĩ số hợp lệ.</div>
                        </div>
                        <div class="col-md-3">
                            <label for="editClassStatus" class="form-label">Trạng thái</label>
                            <select id="editClassStatus" name="status" class="form-select" required>
                                <option value="1">Đang hoạt động</option>
                                <option value="0">Ngưng hoạt động</option>
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
