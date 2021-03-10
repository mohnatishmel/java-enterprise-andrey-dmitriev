$(document).on("click", "#todayViewBtn", function () {
    taskNav.todayView();
    publishTaskList("LoadTaskListToday");
});