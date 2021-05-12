<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="d-none d-flex justify-content-center pagination" id="pagination">
    <nav aria-label="Page navigation example">
        <ul id="paginationUl" class="pagination pg-blue pagination-sm">

        </ul>
    </nav>
</div>

<div class="d-none" id="paginationTemplate">

    <li id="previousPageBtnTemplate">
        <button type="button" class="page-btn" aria-label="Previous"
                onmousedown="return false" onselectstart="return false">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
        </button>
    </li>

    <li>
        <button id="pageBtnTemplate" type="button" class="page-btn"
                onmousedown="return false" onselectstart="return false"></button>
    </li>

    <li>
        <button id="nextPageBtnTemplate" type="button" class="page-btn" aria-label="Next"
                onmousedown="return false" onselectstart="return false">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
        </button>
    </li>

</div>


