<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<div class="col-md-2 d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="min-height: 100vh;">
    <a href="${basePath}/teacher" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
        <span class="fs-4">CodeGym Teacher</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="${basePath}/teacher" class="nav-link text-white ${param.page == null || param.page == 'dashboard' ? 'active' : ''}">
                <i class="bi bi-speedometer2 me-2"></i>
                Dashboard
            </a>
        </li>
        <li>
            <a href="#" class="nav-link text-white" data-bs-toggle="collapse" data-bs-target="#classesCollapse">
                <i class="bi bi-people me-2"></i>
                Quản lý lớp học
            </a>
            <div class="collapse ${param.page == 'attendance' || param.page == 'score' || param.page == 'assessment' ? 'show' : ''}" id="classesCollapse">
                <ul class="nav nav-pills flex-column ms-3">
                    <c:forEach var="classItem" items="${managedClasses}">
                        <li>
                            <a href="#" class="nav-link text-white" data-bs-toggle="collapse" data-bs-target="#class${classItem.classId}Collapse">
                                ${classItem.className}
                            </a>
                            <div class="collapse" id="class${classItem.classId}Collapse">
                                <ul class="nav nav-pills flex-column ms-3">
                                    <li>
                                        <a href="${basePath}/teacher?page=attendance&classId=${classItem.classId}" 
                                           class="nav-link text-white ${param.page == 'attendance' && param.classId == classItem.classId ? 'active' : ''}">
                                            Điểm danh
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${basePath}/teacher?page=score&classId=${classItem.classId}&moduleId=${classItem.moduleId}" 
                                           class="nav-link text-white ${param.page == 'score' && param.classId == classItem.classId ? 'active' : ''}">
                                            Nhập điểm
                                        </a>
                                    </li>
                                    <li>
                                        <a href="${basePath}/teacher?page=assessment&classId=${classItem.classId}&moduleId=${classItem.moduleId}" 
                                           class="nav-link text-white ${param.page == 'assessment' && param.classId == classItem.classId ? 'active' : ''}">
                                            Đánh giá
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </li>
    </ul>
    <hr>
    <div class="dropdown">
        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
            <strong>${teacher.teacherName}</strong>
        </a>
        <ul class="dropdown-menu dropdown-menu-dark text-small shadow" aria-labelledby="dropdownUser1">
            <li><a class="dropdown-item" href="#">Hồ sơ</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="${basePath}/logout">Đăng xuất</a></li>
        </ul>
    </div>
</div>