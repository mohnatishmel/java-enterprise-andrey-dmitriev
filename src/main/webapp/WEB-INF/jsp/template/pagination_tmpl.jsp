<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row align-items-end d-none" id="pagination">
    <nav aria-label="Page navigation example">
        <ul id="paginationUl" class="pagination pg-blue pagination-sm">

        </ul>
    </nav>
</div>

<div class="d-none" id="paginationTemplate">

    <li id="previousPageBtnTemplate" class="page-item">
        <button type="button" class="page-link" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
        </button>
    </li>

    <li class="page-item">
        <button id="pageBtnTemplate" type="button" class="page-link"></button>
    </li>

    <li class="page-item">
        <button id="nextPageBtnTemplate" type="button" class="page-link" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
        </button>
    </li>

</div>


