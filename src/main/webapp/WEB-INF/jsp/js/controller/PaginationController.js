function initPagination(page, count, action) {
    let pages = (count / pageCapacity);
    pages = Math.ceil(pages);
    pagination.clear();
    if (pages > 1) {
        createPreviousPageBtn();
        createNextPageBtn();
        pagination.show();
        for (let i = 0; i < pages; i++) {
            let pageNumber = i + 1;
            createNewPageBtn(pageNumber);
            $("#pageBtn" + pageNumber).click(function () {
                action(i);
            })
        }
        $("#pageBtn" + page).addClass("active");
    }
}