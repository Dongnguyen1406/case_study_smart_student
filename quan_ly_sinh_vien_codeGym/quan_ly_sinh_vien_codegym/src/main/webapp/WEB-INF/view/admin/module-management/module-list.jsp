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
<%--                <td class="text-center"><span class="badge" style="background-color: #272882;">Hoạt động</span></td>--%>
                <td class="text-center">
                    <button class="btn btn-sm btn-warning btn-edit"
                            data-id="1" data-name="Java Core" data-code="MOD101"
                            data-desc="Giới thiệu Java cơ bản và lập trình hướng đối tượng" data-status="1">
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

