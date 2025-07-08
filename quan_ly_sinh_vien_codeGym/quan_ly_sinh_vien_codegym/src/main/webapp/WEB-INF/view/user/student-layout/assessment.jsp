<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/3/2025
  Time: 10:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Đánh giá</h2>
    </div>
    <div class="card-body">
        <c:if test="${assessments != null && not empty assessments}">
            <div class="row mb-4">
                <div class="col-md-12">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Module</th>
                            <th>Điểm trung bình</th>
                            <th>Kết quả</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="assessment" items="${assessments}">
                            <tr>
                                <td>${assessment.moduleName}</td>
                                <td>${assessment.averageScore}</td>
                                <td>
                                    <span class="${assessment.status ? 'text-success' : 'text-danger'}">
                                        ${assessment.status ? 'Đạt' : 'Không đạt'}
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
        <c:if test="${assessments == null || empty assessments}">
            <div class="alert alert-info">
                Chưa có dữ liệu đánh giá.
            </div>
        </c:if>
    </div>
</div>
