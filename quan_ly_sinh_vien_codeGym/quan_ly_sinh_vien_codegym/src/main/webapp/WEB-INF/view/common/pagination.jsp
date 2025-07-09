<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/4/2025
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${totalPages > 1}">
    <nav aria-label="Page navigation">
        <ul class="pagination justify-content-center custom-pagination">
            <!-- Nút Previous -->
            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                <a class="page-link" href="?page=${pageType}&currentPage=${currentPage - 1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>

            <!-- Các số trang -->
            <c:forEach begin="1" end="${totalPages}" var="i">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="?page=${pageType}&currentPage=${i}">${i}</a>
                </li>
            </c:forEach>

            <!-- Nút Next -->
            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                <a class="page-link" href="?page=${pageType}&currentPage=${currentPage + 1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</c:if>

<!-- CSS phân trang màu #272882 -->
<style>
    .custom-pagination .page-link {
        color: #272882;
        /*border-color: #272882;*/
    }

    .custom-pagination .page-link:hover {
        background-color: #272882;
        color: white;
    }

    .custom-pagination .page-item.active .page-link {
        background-color: #272882;
        border-color: #272882;
        color: white;
    }

    .custom-pagination .page-item.disabled .page-link {
        color: #aaa;
        background-color: #f8f9fa;
        /*border-color: #dee2e6;*/
    }
</style>




