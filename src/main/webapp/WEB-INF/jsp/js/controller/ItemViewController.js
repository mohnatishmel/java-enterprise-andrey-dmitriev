
function fillViewList() {
    switch (currentView) {
        case "UNLOCKED_USERS":
            viewerNav.unlockedUsersViewBtn.click();
            break;
        case "LOCKED_USERS":
            viewerNav.lockedUsersViewBtn.click();
            break;
        case "INCOME_MESSAGES":
            viewerNav.incomeMessagesBtn.click();
            break;
        case "TODAY":
            viewerNav.todayViewBtn.click();
            break;
        case "TOMORROW":
            viewerNav.tomorrowViewBtn.click();
            break;
        case "TRASH_BOX":
            viewerNav.trashBoxViewBtn.click();
            break;
        case "FIXED":
            viewerNav.fixedViewBtn.click();
            break;
        default:
            viewerNav.somedayViewBtn.click();
    }
}

// function switchToTaskView() {
//
// }
//
// function switchToUserView() {
//
// }