taskNav.todayViewBtn.on("click", function () {
    currentView = "TODAY";
    taskNav.todayView();
    showTaskListCommonView("LoadTaskListToday");
    console.log("current view: " + currentView);
});

taskNav.tomorrowViewBtn.on("click", function () {
    currentView = "TOMORROW";
    taskNav.tomorrowView();
    showTaskListCommonView("LoadTaskListTomorrow");
    console.log("current view: " + currentView);
});

taskNav.somedayViewBtn.on("click", function () {
    currentView = "SOMEDAY";
    taskNav.somedayView();
    showTaskListCommonView("LoadTaskListSomeday");
    console.log("current view: " + currentView);
});

taskNav.fixedViewBtn.on("click", function () {
    currentView = "FIXED";
    taskNav.fixedView();
    showTaskListCommonView("LoadTaskListFixed");
    console.log("current view: " + currentView);
});

taskNav.trashBoxViewBtn.on("click", function () {
    currentView = "TRASH_BOX";
    taskNav.trashBoxView();
    showTaskListTrashBoxView("LoadTaskListTrashBox");
    console.log("current view: " + currentView);
});
