<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/29/2025
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container-fluid">
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

    <!-- Biểu đồ thống kê cột và tròn -->
    <div class="row mb-5">
        <!-- Biểu đồ cột -->
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title">Biểu đồ thống kê</h5>
                    <div class="chart-container" style="position: relative; height: 300px;">
                        <canvas id="dashboardChart"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Biểu đồ tròn học sinh theo lớp -->
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-body">
                    <h5 class="card-title">Học sinh theo lớp</h5>
                    <div class="chart-container" style="position: relative; width: 100%; height: 300px;">
                        <canvas id="classPieChart" style="max-width: 100%; height: auto;"></canvas>
                    </div>
                </div>
            </div>
        </div>
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
<script>
    const pieCtx = document.getElementById('classPieChart').getContext('2d');
    const classPieChart = new Chart(pieCtx, {
        type: 'pie',
        data: {
            labels: ['Lớp 10A1', 'Lớp 10B2'],
            datasets: [{
                label: 'Sĩ số học sinh',
                data: [40, 38], // Lấy từ bảng lớp phía dưới
                backgroundColor: ['#FF6384', '#36A2EB'],
                borderColor: '#fff',
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'bottom'
                }
            }
        }
    });
</script>




