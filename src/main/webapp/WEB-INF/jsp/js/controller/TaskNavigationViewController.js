$(document).on("click", "#todayViewBtn", function () {
    currentView = "TODAY";
    taskNav.todayView();
    showTaskListCommonView("LoadTaskListToday");
    console.log("current view: " + currentView);
});

$(document).on("click", "#tomorrowViewBtn", function () {
    currentView = "TOMORROW";
    taskNav.tomorrowView();
    showTaskListCommonView("LoadTaskListTomorrow");
    console.log("current view: " + currentView);
});

$(document).on("click", "#somedayViewBtn", function () {
    currentView = "SOMEDAY";
    taskNav.somedayView();
    showTaskListCommonView("LoadTaskListSomeday");
    console.log("current view: " + currentView);
});

$(document).on("click", "#fixedViewBtn", function () {
    currentView = "FIXED";
    taskNav.fixedView();
    showTaskListCommonView("LoadTaskListFixed");
    console.log("current view: " + currentView);
});

$(document).on("click", "#trashBoxViewBtn", function () {
    currentView = "TRASH_BOX";
    taskNav.trashBoxView();
    showTaskListTrashBoxView("LoadTaskListTrashBox");
    console.log("current view: " + currentView);
});
