var taskNav = {}

taskNav.todayViewBtn = $("#todayViewBtn");
taskNav.tomorrowViewBtn = $("#tomorrowViewBtn");
taskNav.somedayViewBtn = $("#somedayViewBtn");
taskNav.trashBoxViewBtn = $("#trashBoxViewBtn");
taskNav.fixedViewBtn = $("#fixedViewBtn");

taskNav.todayView = function () {
    this.todayViewBtn.addClass("active-btn");
    this.tomorrowViewBtn.removeClass("active-btn");
    this.somedayViewBtn.removeClass("active-btn");
    this.trashBoxViewBtn.removeClass("active-btn");
    this.fixedViewBtn.removeClass("active-btn");
};

taskNav.tomorrowView = function () {
    this.todayViewBtn.removeClass("active-btn");
    this.tomorrowViewBtn.addClass("active-btn");
    this.somedayViewBtn.removeClass("active-btn");
    this.trashBoxViewBtn.removeClass("active-btn");
    this.fixedViewBtn.removeClass("active-btn");
};

taskNav.somedayView = function () {
    this.todayViewBtn.removeClass("active-btn");
    this.tomorrowViewBtn.removeClass("active-btn");
    this.somedayViewBtn.addClass("active-btn");
    this.trashBoxViewBtn.removeClass("active-btn");
    this.fixedViewBtn.removeClass("active-btn");

};

taskNav.fixedView = function () {
    this.todayViewBtn.removeClass("active-btn");
    this.tomorrowViewBtn.removeClass("active-btn");
    this.somedayViewBtn.removeClass("active-btn");
    this.trashBoxViewBtn.removeClass("active-btn");
    this.fixedViewBtn.addClass("active-btn");
};

taskNav.trashBoxView = function () {
    this.todayViewBtn.removeClass("active-btn");
    this.tomorrowViewBtn.removeClass("active-btn");
    this.somedayViewBtn.removeClass("active-btn");
    this.trashBoxViewBtn.addClass("active-btn");
    this.fixedViewBtn.removeClass("active-btn");
};


