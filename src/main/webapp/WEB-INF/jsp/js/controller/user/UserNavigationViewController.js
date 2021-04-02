viewerNav.unlockedUsersViewBtn.on("click", function () {
    currentView = "UNLOCKED_USERS";
    viewerNav.unlockedUsersView();
    showUserList("LoadUsersUnlocked");
    console.log("current view: " + currentView);
});

viewerNav.lockedUsersViewBtn.on("click", function () {
    currentView = "LOCKED_USERS";
    viewerNav.lockedUsersView();
    showUserList("LoadUsersLocked");
    console.log("current view: " + currentView);
});

viewerNav.incomeMessagesBtn.on("click", function () {
    currentView = "INCOME_MESSAGES";
    viewerNav.incomeMessages();
    showMessageList("LoadUserUnlockRequestList");
    console.log("current view: " + currentView);
});

viewerNav.enableAllLockedUsersBtn.on("click", function () {
    unlockAllUserFromLockedView();
    updateAllUsersFromViewList();
    viewList = {};
});

viewerNav.deleteAllLockedUsersBtn.on("click", function () {
    deleteAllUsersFromViewList();
    viewList = {};
});
