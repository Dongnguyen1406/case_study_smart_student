<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card">
    <div class="card-header">
        <h2>Điểm số</h2>
    </div>
    <div class="card-body">
        <c:if test="${moduleScore != null}">
            <div class="row mb-4">
                <div class="col-md-12">
                    <h4>Module: ${moduleScore.module}</h4>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>Điểm bài kiểm tra</th>
                            <th>Điểm thực hành</th>
                            <th>Điểm trung bình</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${moduleScore.quizScore}</td>
                            <td>${moduleScore.practiceScore}</td>
                            <td>${moduleScore.averageScore}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
        <c:if test="${moduleScore == null}">
            <div class="alert alert-info">
                Chưa có dữ liệu điểm.
            </div>
        </c:if>
    </div>
</div>

