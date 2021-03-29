header.taskViewBtn.on("click", function () {
    header.switchToTaskView();
    taskNav.todayViewBtn.click();
})

header.userViewBtn.on("click", function () {
    header.switchToUserView();
    userNav.unlockedUsersViewBtn.click();
})

header.disableAdministratorFunctionality();
