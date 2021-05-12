let pagination = {
    pages: 0,
    currentPage: 0
};
pagination.hide = function () {
    $("#paginationUl").addClass("d-none");
}
pagination.show = function () {
    $("#paginationUl").removeClass("d-none");
}

pagination.clear = function () {
    $("#paginationUl").empty()
}

pagination.append = function (page) {
    $("#paginationUl").append($($.parseHTML(page)).hide().fadeIn(200));
}

pagination.activePage = function (pageNumber) {
    for (let i = 1; i <= this.pages; i++) {
        $("#pageBtn" + i ).removeClass("page-btn-active");
    }
    pageNumber++;
    $("#pageBtn" + pageNumber).addClass("page-btn-active")
}

function createPreviousPageBtn() {
    let $previousBtnTemplate = $("#previousPageBtnTemplate");
    let $newPreviousBtn = $previousBtnTemplate.clone();
    $newPreviousBtn.attr("id", "previousPageBtn");
    let $newLi = $("<li></li>");
    $newLi.addClass("page-item");
    $newLi.append($newPreviousBtn);
    let html = $newLi.html();
    pagination.append(html);
}

function createNextPageBtn() {
    let $nextBtnTemplate = $("#nextPageBtnTemplate");
    let $newNextBtn = $nextBtnTemplate.clone();
    $newNextBtn.attr("id", "nextPageBtn");
    let $newLi = $("<li></li>");
    $newLi.addClass("page-item");
    $newLi.append($newNextBtn);
    let html = $newLi.html();
    pagination.append(html);
}

function createNewPageBtn(pageNumber) {
    let $pageBtnTemplate = $("#pageBtnTemplate");
    let $newPageBtn = $pageBtnTemplate.clone();
    $newPageBtn.attr("id", "pageBtn" + pageNumber);
    $newPageBtn.text(pageNumber);
    let $newLi = $("<li></li>");
    $newLi.addClass("page-item");
    $newLi.append($newPageBtn);
    $newLi.insertBefore("#nextPageBtn");
}