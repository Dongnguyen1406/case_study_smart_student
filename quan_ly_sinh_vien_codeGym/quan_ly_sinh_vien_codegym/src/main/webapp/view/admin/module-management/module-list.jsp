<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<h2 class="mb-4">🗂️ Danh sách học phần (Module)</h2>

<!-- Nút thêm -->
<div class="mb-3 text-end">
    <button class="btn btn-primary" id="btnAddModule">➕ Thêm học phần</button>
</div>

<!-- Bảng danh sách -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light text-center">
        <tr>
            <th>STT</th>
            <th>Tên học phần</th>
            <th>Mã học phần</th>
            <th>Mô tả</th>
            <th>Trạng thái</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="text-center">1</td>
            <td>Java Core</td>
            <td>MOD101</td>
            <td>Giới thiệu Java cơ bản và lập trình hướng đối tượng</td>
            <td class="text-center"><span class="badge bg-success">Hoạt động</span></td>
            <td class="text-center">
                <button class="btn btn-sm btn-warning btn-edit"
                        data-id="1" data-name="Java Core" data-code="MOD101"
                        data-desc="Giới thiệu Java cơ bản và lập trình hướng đối tượng" data-status="1">
                    ✏️ Sửa
                </button>
                <a href="#" class="btn btn-sm btn-danger">🗑️ Xóa</a>
            </td>
        </tr>
        <tr>
            <td class="text-center">2</td>
            <td>Web Development</td>
            <td>MOD102</td>
            <td>Thiết kế và phát triển website bằng HTML, CSS, JavaScript</td>
            <td class="text-center"><span class="badge bg-secondary">Không hoạt động</span></td>
            <td class="text-center">
                <button class="btn btn-sm btn-warning btn-edit"
                        data-id="2" data-name="Web Development" data-code="MOD102"
                        data-desc="Thiết kế và phát triển website bằng HTML, CSS, JavaScript" data-status="0">
                    ✏️ Sửa
                </button>
                <a href="#" class="btn btn-sm btn-danger">🗑️ Xóa</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<!-- Gọi modal thêm & sửa -->
<jsp:include page="module-form.jsp"/>
<jsp:include page="module-update.jsp"/>

<script>
    // Modal thêm
    document.getElementById('btnAddModule').addEventListener('click', () => {
        new bootstrap.Modal(document.getElementById('addModuleModal')).show();
    });

    // Modal sửa
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', () => {
            document.getElementById('editModuleId').value = button.dataset.id;
            document.getElementById('editModuleName').value = button.dataset.name;
            document.getElementById('editModuleCode').value = button.dataset.code;
            document.getElementById('editModuleDesc').value = button.dataset.desc;
            document.getElementById('editModuleStatus').value = button.dataset.status;
            new bootstrap.Modal(document.getElementById('editModuleModal')).show();
        });
    });
</script>

