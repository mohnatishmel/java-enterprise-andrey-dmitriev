var taskNav = {}

taskNav.todayViewBtn = $("#todayViewBtn");
taskNav.tomorrowViewBtn = $("#tomorrowViewBtn");
taskNav.somedayViewBtn = $("#somedayViewBtn");
taskNav.trashBoxViewBtn = $("#trashBoxViewBtn");
taskNav.fixedViewBtn = $("#fixedViewBtn");

taskNav.todayView = function () {
    currentView = "TODAY";
    this.todayViewBtn.addClass("active-btn");
    this.tomorrowViewBtn.removeClass("active-btn");
    this.somedayViewBtn.removeClass("active-btn");
    this.trashBoxViewBtn.removeClass("active-btn");
    this.fixedViewBtn.removeClass("active-btn");
    console.log("current view: " + currentView);
}



