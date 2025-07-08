<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/3/2025
  Time: 10:04 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Điểm danh</h2>
    </div>
    <div class="card-body">
        <c:if test="${moduleAttendance != null}">
            <div class="row mb-4">
                <div class="col-md-12">
                    <h4>Module: ${moduleAttendance.moduleName}</h4>
                    <table class="table table-bordered">
                        <tr>
                            <th>Ngày đăng ký</th>
                            <td>${moduleAttendance.registrationDate}</td>
                        </tr>
                        <tr>
                            <th>Số buổi vắng không phép</th>
                            <td>${moduleAttendance.unexcusedAbsences}</td>
                        </tr>
                        <tr>
                            <th>Kết quả</th>
                            <td>
                                <span class="${moduleAttendance.result == 'Đủ điều kiện thi' ? 'text-success' : 'text-danger'}">
                                    ${moduleAttendance.result}
                                </span>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </c:if>
        <c:if test="${moduleAttendance == null}">
            <div class="alert alert-info">
                Chưa có dữ liệu điểm danh.
            </div>
        </c:if>
    </div>
</div>

