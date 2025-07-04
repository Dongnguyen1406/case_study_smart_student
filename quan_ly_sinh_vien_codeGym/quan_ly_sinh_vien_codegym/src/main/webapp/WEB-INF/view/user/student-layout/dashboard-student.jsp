<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/3/2025
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .course-card {
            height: 200px;
            background-size: cover;
            background-position: center;
            color: white;
            position: relative;
            border-radius: 10px;
            overflow: hidden;
        }
        .card-overlay {
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            padding: 10px;
            /*background: rgba(0, 0, 0, 0.6);*/
            background-color: green;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <c:if test="${not empty sessionScope.successMessage}">
        <div id="toast-alert" class="custom-toast animate-slide-down">
                ${sessionScope.successMessage}
        </div>
        <c:remove var="successMessage" scope="session" />

        <style>
            .custom-toast {
                position: fixed;
                top: 30px;
                left: 50%;
                transform: translateX(-50%);
                background-color: white;
                color: black;
                padding: 15px 25px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
                z-index: 9999;
                font-weight: bold;
                opacity: 0.95;
            }

            @keyframes slideDown {
                from {
                    transform: translate(-50%, -50px);
                    opacity: 0;
                }
                to {
                    transform: translate(-50%, 0);
                    opacity: 1;
                }
            }

            .animate-slide-down {
                animation: slideDown 0.5s ease-out;
            }
        </style>

        <script>
            setTimeout(function() {
                var toast = document.getElementById("toast-alert");
                if (toast) {
                    toast.style.display = "none";
                }
            }, 3000);
        </script>
    </c:if>

    <!-- Course Overview Section -->
    <div style="background-color: #fefefe; padding: 20px; border-radius: 8px;">
        <h3>Course Overview</h3>
        <div class="row g-3">
            <div class="col-md-3">
                <div class="course-card" style="background-image: url('/assets/user/img/course3.png');">
                    <div class="card-overlay">Full Stack</div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="course-card" style="background-image: url('/assets/user/img/course3.png');">
                    <div class="card-overlay">Front-End</div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="course-card" style="background-image: url('/assets/user/img/course3.png');">
                    <div class="card-overlay">Back-End</div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="course-card" style="background-image: url('/assets/user/img/course3.png');">
                    <div class="card-overlay">DevOps</div>
                </div>
            </div>
        </div>
    </div>

    <!-- Recently Accessed Courses Section -->
    <div style="background-color: #fefefe; padding: 20px; border-radius: 8px; margin-top: 40px;">
        <h3>Recently Accessed Courses</h3>
        <div class="row g-3">
            <div class="col-md-3">
                <div class="course-card" style="background-image: url('/assets/user/img/course3.png');">
                    <div class="card-overlay">Full Stack</div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="course-card" style="background-image: url('/assets/user/img/course3.png');">
                    <div class="card-overlay">Front-End</div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="course-card" style="background-image: url('/assets/user/img/course3.png');">
                    <div class="card-overlay">Back-End</div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="course-card" style="background-image: url('/assets/user/img/course3.png');">
                    <div class="card-overlay">DevOps</div>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
</html>

