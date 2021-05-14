viewerNav.unlockedUsersViewBtn.on("click", function () {
    currentView = "UNLOCKED_USERS";
    viewerNav.unlockedUsersView();
    showUserList("/rest/users/notLocked");
    console.log("current view: " + currentView);
});

viewerNav.lockedUsersViewBtn.on("click", function () {
    currentView = "LOCKED_USERS";
    viewerNav.lockedUsersView();
    showUserList("/rest/users/locked");
    console.log("current view: " + currentView);
});

viewerNav.incomeMessagesBtn.on("click", function () {
    incomeMessagesView();
});

viewerNav.enableAllLockedUsersBtn.on("click", function () {
    unlockAllUserFromLockedView();
    viewList = {};
});

viewerNav.deleteAllLockedUsersBtn.on("click", function () {
    deleteAllUsersFromViewList();
    viewList = {};
});

function incomeMessagesView() {
    currentView = "INCOME_MESSAGES";
    viewerNav.incomeMessages();
    showMessageList("/rest/unlock/requests/messages");
    console.log("current view: " + currentView);
}