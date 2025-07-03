<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2 class="mb-4">📊 Thống kê tổng quan</h2>

<!-- Thống kê nhanh -->
<div class="row">
    <div class="col-md-3 mb-4">
        <div class="card text-white bg-primary shadow">
            <div class="card-body">
                <h5 class="card-title">Student</h5>
                <p class="card-text fs-4">250</p>
            </div>
        </div>
    </div>
    <div class="col-md-3 mb-4">
        <div class="card text-white bg-success shadow">
            <div class="card-body">
                <h5 class="card-title">Teacher</h5>
                <p class="card-text fs-4">45</p>
            </div>
        </div>
    </div>
    <div class="col-md-3 mb-4">
        <div class="card text-white bg-warning shadow">
            <div class="card-body">
                <h5 class="card-title">Course</h5>
                <p class="card-text fs-4">12</p>
            </div>
        </div>
    </div>
    <div class="col-md-3 mb-4">
        <div class="card text-white bg-info shadow">
            <div class="card-body">
                <h5 class="card-title">Module</h5>
                <p class="card-text fs-4">30</p>
            </div>
        </div>
    </div>
</div>

<!-- Biểu đồ -->
<div class="card mb-5 shadow">
    <div class="card-body">
        <h5 class="card-title">Biểu đồ thống kê</h5>
        <canvas id="dashboardChart" height="100"></canvas>
    </div>
</div>

<!-- Chart.js CDN + Script -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    const ctx = document.getElementById('dashboardChart').getContext('2d');
    const dashboardChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: ['Học sinh', 'Giáo viên', 'Khóa học', 'Học phần'],
            datasets: [{
                label: 'Số lượng',
                data: [250, 45, 12, 30], // Thay bằng dữ liệu thật nếu cần
                backgroundColor: ['#0d6efd', '#198754', '#ffc107', '#0dcaf0'],
                borderColor: ['#0b5ed7', '#157347', '#e0a800', '#31d2f2'],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    ticks: {
                        stepSize: 50
                    }
                }
            }
        }
    });
</script>



