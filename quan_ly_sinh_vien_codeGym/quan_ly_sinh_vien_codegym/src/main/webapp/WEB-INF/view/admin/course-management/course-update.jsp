<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="editCourseModal" tabindex="-1" aria-labelledby="editCourseModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${basePath}/course/update" method="post">
                <div class="modal-header">
                    <h5 class="modal-title">✏️ Cập nhật khóa học</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <input type="hidden" name="id" id="editCourseId">
                    <div class="mb-3">
                        <label class="form-label">Tên khóa học</label>
                        <input type="text" name="name" id="editCourseName" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mã khóa học</label>
                        <input type="text" name="code" id="editCourseCode" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Thời gian (tháng)</label>
                        <input type="number" name="duration" id="editCourseDuration" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Trạng thái</label>
                        <select name="status" id="editCourseStatus" class="form-select">
                            <option value="1">Đang mở</option>
                            <option value="0">Đã đóng</option>
                        </select>
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

