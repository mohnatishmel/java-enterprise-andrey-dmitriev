function initPagination(page, count, action) {

    let pages = (page.total / pageCapacity);
    pages = Math.ceil(pages);
    if (pages == pagination.pages) {
        pagination.show();
    }
    if (pages <= 1) {
        pagination.hide();
    }
    if (pages != pagination.pages & pages > 1) {
        pagination.clear();
        pagination.pages = pages;
        pagination.currentPage = page.number;
        createPreviousPageBtn();
        createNextPageBtn();
        pagination.show();
        for (let i = 0; i < pages; i++) {
            let pageNumber = i + 1;
            createNewPageBtn(pageNumber);
            $("#pageBtn" + pageNumber).click(function () {
                action(i);
                pagination.currentPage = i ;
                pagination.activePage(i);
            })
        }
        pagination.show()
        $("#pageBtn" + (page.number + 1)).addClass("page-btn-active");
        $("#previousPageBtn").click(function () {
            if (pagination.currentPage > 0) {
                pagination.currentPage--;
                pagination.activePage(pagination.currentPage);
                action(pagination.currentPage);
            }
        })
        $("#nextPageBtn").click(function () {
            if (pagination.currentPage < (pagination.pages - 1)) {
                pagination.currentPage++;
                pagination.activePage(pagination.currentPage);
                action(pagination.currentPage);
            }
        })
    }
}