userNav.unlockedUsersViewBtn.on("click", function () {
    currentView = "UNLOCKED_USERS";
    userNav.unlockedUsersView();
    showUserList("LoadUsersUnlocked");
    console.log("current view: " + currentView);
});

userNav.lockedUsersViewBtn.on("click", function () {
    currentView = "LOCKED_USERS";
    userNav.lockedUsersView();
    showUserList("LoadUsersLocked");
    console.log("current view: " + currentView);
});

userNav.incomeMessagesBtn.on("click", function () {
    currentView = "INCOME_MESSAGES";
    userNav.incomeMessages();
    showMessageList("LoadUserUnlockRequestList");
    console.log("current view: " + currentView);
});
