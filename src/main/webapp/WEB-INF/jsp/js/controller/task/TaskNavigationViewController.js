viewerNav.todayViewBtn.on("click", function () {
    currentView = "TODAY";
    viewerNav.todayView();
    showTaskListCommonView("LoadTaskListToday", 0);
    console.log("current view: " + currentView);
});

viewerNav.tomorrowViewBtn.on("click", function () {
    currentView = "TOMORROW";
    viewerNav.tomorrowView();
    showTaskListCommonView("LoadTaskListTomorrow", 0);
    console.log("current view: " + currentView);
});

viewerNav.somedayViewBtn.on("click", function () {
    currentView = "SOMEDAY";
    viewerNav.somedayView();
    showTaskListCommonView("LoadTaskListSomeday", 0);
    console.log("current view: " + currentView);
});

viewerNav.fixedViewBtn.on("click", function () {
    currentView = "FIXED";
    viewerNav.fixedView();
    showTaskListCommonView("LoadTaskListFixed", 0);
    console.log("current view: " + currentView);
});

viewerNav.trashBoxViewBtn.on("click", function () {
    currentView = "TRASH_BOX";
    viewerNav.trashBoxView();
    showTaskListTrashBoxView("LoadTaskListTrashBox", 0);
    console.log("current view: " + currentView);
});

viewerNav.emptyTaskTrashBoxBtn.on("click", function () {
    deleteAllTasksFromViewList();
    viewerNav.trashBoxView();
});


viewerNav.restoreAllTasksBtn.on("click", function () {
    restoreAllTasksFromTrashBox()
    updateAllTasksFromViewList();
    viewList = {};
});

viewerNav.clearFixedTasksBtn.on("click", function () {
    deleteAllTasksFromViewList();
    viewList = {};
});


