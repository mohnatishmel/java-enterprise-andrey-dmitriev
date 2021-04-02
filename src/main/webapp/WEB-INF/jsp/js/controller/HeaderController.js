header.taskViewBtn.on("click", function () {
    header.switchToTaskView();
    viewerNav.todayViewBtn.click();
    viewerNav.taskNav();
})

header.userViewBtn.on("click", function () {
    header.switchToUserView();
    viewerNav.unlockedUsersViewBtn.click();
    viewerNav.userNav();
})

header.disableAdministratorFunctionality();
