
function fillViewList() {
    switch (currentView) {
        case "UNLOCKED_USERS":
            userNav.unlockedUsersViewBtn.click();
            break;
        case "LOCKED_USERS":
            taskNav.lockedUsersViewBtn.click();
            break;
        case "INCOME_MESSAGES":
            taskNav.incomeMessagesBtn.click();
            break;
        case "TODAY":
            taskNav.todayViewBtn.click();
            break;
        case "TOMORROW":
            taskNav.tomorrowViewBtn.click();
            break;
        case "TRASH_BOX":
            taskNav.trashBoxViewBtn.click();
            break;
        case "FIXED":
            taskNav.fixedViewBtn.click();
            break;
        default:
            taskNav.somedayViewBtn.click();
    }
}

function switchToTaskView() {

}

function switchToUserView() {

}