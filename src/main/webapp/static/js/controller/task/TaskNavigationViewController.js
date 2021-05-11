viewerNav.todayViewBtn.on("click", function () {
    currentView = "TODAY";
    viewerNav.todayView();
    showTaskListCommonView("/rest/tasks/today", 0);
    console.log("current view: " + currentView);
});

viewerNav.tomorrowViewBtn.on("click", function () {
    currentView = "TOMORROW";
    viewerNav.tomorrowView();
    showTaskListCommonView("/rest/tasks/tomorrow", 0);
    console.log("current view: " + currentView);
});

viewerNav.somedayViewBtn.on("click", function () {
    currentView = "SOMEDAY";
    viewerNav.somedayView();
    showTaskListCommonView("/rest/tasks/someday", 0);
    console.log("current view: " + currentView);
});

viewerNav.fixedViewBtn.on("click", function () {
    currentView = "FIXED";
    viewerNav.fixedView();
    showTaskListCommonView("/rest/tasks/fixed", 0);
    console.log("current view: " + currentView);
});

viewerNav.trashBoxViewBtn.on("click", function () {
    currentView = "TRASH_BOX";
    viewerNav.trashBoxView();
    showTaskListTrashBoxView("/rest/tasks/trashBox", 0);
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


