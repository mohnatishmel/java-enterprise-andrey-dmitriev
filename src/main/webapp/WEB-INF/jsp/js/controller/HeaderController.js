header.logOutBtn.on("click", function () {
    logout(logOutSuccess, logOutError);
    header.collapse();
})

header.personalInfoBtn.on("click", function () {
    header.collapse();
})

header.taskViewBtn.on("click", function () {
    header.switchToTaskView();
    viewerNav.todayViewBtn.click();
    viewerNav.taskNav();
    header.collapse();
})

header.userViewBtn.on("click", function () {
    header.switchToUserView();
    viewerNav.unlockedUsersViewBtn.click();
    viewerNav.userNav();
    header.collapse();
})

header.disableAdministratorFunctionality();
