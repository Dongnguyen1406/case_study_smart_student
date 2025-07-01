<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="editStudentModal" tabindex="-1" aria-labelledby="editStudentModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <form action="${basePath}/student/update" method="post" class="needs-validation" novalidate>
                <input type="hidden" id="editId" name="id" value=""/>
                <div class="modal-header">
                    <h5 class="modal-title" id="editStudentModalLabel">✏️ Cập nhật học sinh</h5>
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

