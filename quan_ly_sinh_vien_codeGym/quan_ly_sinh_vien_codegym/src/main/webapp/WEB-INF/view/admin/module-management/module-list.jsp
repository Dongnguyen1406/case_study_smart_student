<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<h2 class="mb-4">🗂️ Danh sách học phần (Module)</h2>

<!-- Nút thêm -->
<div class="mb-3 text-end">
    <button class="btn" id="btnAddModule" style="background-color: #272882; color: #ffffff">Thêm học phần</button>
</div>

<!-- Bảng danh sách -->
<div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light text-center">
        <tr>
            <th>STT</th>
            <th>Tên học phần</th>
            <th>Thao tác</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${modules}" var="module" varStatus="temp">
            <tr>
                <td class="text-center">${startIndex + temp.count}</td>
                <td class="text-center">${module.moduleName}</td>
                <td class="text-center">
                    <button type="button" class="btn btn-sm  btn-edit"
                            data-id="${module.moduleId}"
                            data-name="${module.moduleName}">
                        <i class="bi bi-pencil-square"></i>
                    </button>
                    <button type="button" class="btn btn-sm btn-delete"
                            data-id="${module.moduleId}">
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

<!-- Modal Thêm Module -->
<div class="modal fade" id="addModuleModal" tabindex="-1" aria-labelledby="addModuleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${basePath}/admin?page=addModule" method="post" class="needs-validation">
                <div class="modal-header">
                    <h5 class="modal-title">Thêm học phần</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Tên học phần</label>
                        <input type="text" name="name" class="form-control" required
                               pattern="^[A-Za-zÀ-ỹ0-9 ]{3,50}$"
                               title="Tên học phần phải từ 3 đến 50 ký tự, chỉ chứa chữ cái, số và khoảng trắng">
                        <div class="invalid-feedback">Vui lòng nhập tên học phần hợp lệ (ít nhất 3 ký tự, không chứa ký tự đặc biệt).</div>
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

<!-- Modal Cập Nhật Module -->
<div class="modal fade" id="editModuleModal" tabindex="-1" aria-labelledby="editModuleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="${basePath}/admin?page=updateModule" method="post">
                <input type="hidden" name="id" id="editModuleId">
                <div class="modal-header">
                    <h5 class="modal-title">✏️ Cập nhật học phần</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label">Tên học phần</label>
                        <input type="text" name="name" id="editModuleName" class="form-control" required
                               pattern="^[A-Za-zÀ-ỹ0-9 ]{3,50}$"
                               title="Tên học phần phải từ 3 đến 50 ký tự, chỉ chứa chữ cái, số và khoảng trắng">
                        <div class="invalid-feedback">Vui lòng nhập tên học phần hợp lệ (ít nhất 3 ký tự, không chứa ký tự đặc biệt).</div>

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
            <form action="${basePath}/admin?page=deleteModule" method="post">
                <input type="hidden" id="deleteModuleId" name="id">
                <div class="modal-header">
                    <h5 class="modal-title">Xác nhận xóa học phần</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    Bạn có chắc chắn muốn xóa học phần này không?
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

<!-- Script xử lý modal -->
<script>
    // Mở modal thêm
    document.getElementById('btnAddModule').addEventListener('click', () => {
        let modal = new bootstrap.Modal(document.getElementById('addModuleModal'));
        modal.show();
    });

    // Mở modal sửa
    document.querySelectorAll('.btn-edit').forEach(button => {
        button.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('editModuleModal'));
            document.getElementById('editModuleId').value = button.dataset.id;
            document.getElementById('editModuleName').value = button.dataset.name;
            modal.show();
        });
    });


    // Mở modal xác nhận xóa
    document.querySelectorAll('.btn-delete').forEach(button => {
        button.addEventListener('click', () => {
            const modal = new bootstrap.Modal(document.getElementById('confirmDeleteModal'));
            document.getElementById('deleteModuleId').value = button.dataset.id;
            modal.show();
        });
    });

</script>
