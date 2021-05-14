viewerNav.todayViewBtn.on("click", function () {
    todayTaskView(0)
});

viewerNav.tomorrowViewBtn.on("click", function () {
    tomorrowTaskView(0)
});

viewerNav.somedayViewBtn.on("click", function () {
    somedayTaskView(0)
});

viewerNav.fixedViewBtn.on("click", function () {
    fixedTaskView(0)
});

viewerNav.trashBoxViewBtn.on("click", function () {
    trashBoxTaskView(0)
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


function todayTaskView(pageNumber) {
    currentView = "TODAY";
    viewerNav.todayView();
    showTaskListCommonView("/rest/tasks/today", pageNumber);
    console.log("current view: " + currentView);
};

function tomorrowTaskView(pageNumber) {
    currentView = "TOMORROW";
    viewerNav.tomorrowView();
    showTaskListCommonView("/rest/tasks/tomorrow", pageNumber);
    console.log("current view: " + currentView);
};

function somedayTaskView(pageNumber) {
    currentView = "SOMEDAY";
    viewerNav.somedayView();
    showTaskListCommonView("/rest/tasks/someday", pageNumber);
    console.log("current view: " + currentView);
};

function fixedTaskView(pageNumber) {
    currentView = "FIXED";
    viewerNav.fixedView();
    showTaskListCommonView("/rest/tasks/fixed", pageNumber);
    console.log("current view: " + currentView);
};

function trashBoxTaskView(pageNumber) {
    currentView = "TRASH_BOX";
    viewerNav.trashBoxView();
    showTaskListTrashBoxView("/rest/tasks/trashBox", pageNumber);
    console.log("current view: " + currentView);
};



