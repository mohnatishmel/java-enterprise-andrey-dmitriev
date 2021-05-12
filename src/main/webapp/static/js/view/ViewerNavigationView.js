const viewerNav = {}

viewerNav.todayViewBtn = $("#todayViewBtn");
viewerNav.tomorrowViewBtn = $("#tomorrowViewBtn");
viewerNav.somedayViewBtn = $("#somedayViewBtn");
viewerNav.trashBoxViewBtn = $("#trashBoxViewBtn");
viewerNav.fixedViewBtn = $("#fixedViewBtn");

viewerNav.unlockedUsersViewBtn = $("#unlockedUsersViewBtn");
viewerNav.lockedUsersViewBtn = $("#lockedUsersViewBtn");
viewerNav.incomeMessagesBtn = $("#incomeMessagesBtn");

viewerNav.openCreateTaskFormBtn = $("#openCreateTaskFormBtn");

viewerNav.trashBoxDropdownMenuButton = $("#trashBoxDropdownMenuButton");
viewerNav.fixedDropdownMenu = $("#fixedDropdownMenu");
viewerNav.lockedUsersDropdownMenu = $("#lockedUsersDropdownMenu");

viewerNav.emptyTaskTrashBoxBtn = $("#emptyTaskTrashBoxBtn");
viewerNav.restoreAllTasksBtn = $("#restoreAllTasksBtn");
viewerNav.clearFixedTasksBtn = $("#clearFixedTasksBtn");
viewerNav.enableAllLockedUsersBtn = $("#enableAllLockedUsersBtn");
viewerNav.deleteAllLockedUsersBtn = $("#deleteAllLockedUsersBtn");

viewerNav.unlockedUsersView = function () {
    this.unlockedUsersViewBtn.addClass("active-btn");
    this.lockedUsersViewBtn.removeClass("active-btn");
    this.incomeMessagesBtn.removeClass("active-btn");

    this.trashBoxDropdownMenuButton.hide();
    this.fixedDropdownMenu.hide();
    this.lockedUsersDropdownMenu.hide();

};

viewerNav.lockedUsersView = function () {
    this.unlockedUsersViewBtn.removeClass("active-btn");
    this.lockedUsersViewBtn.addClass("active-btn");
    this.incomeMessagesBtn.removeClass("active-btn");

    this.trashBoxDropdownMenuButton.hide();
    this.fixedDropdownMenu.hide();
    this.lockedUsersDropdownMenu.show();
};

viewerNav.incomeMessages = function () {
    this.unlockedUsersViewBtn.removeClass("active-btn");
    this.lockedUsersViewBtn.removeClass("active-btn");
    this.incomeMessagesBtn.addClass("active-btn");

    this.trashBoxDropdownMenuButton.hide();
    this.fixedDropdownMenu.hide();
    this.lockedUsersDropdownMenu.hide();
};


viewerNav.todayView = function () {
    this.todayViewBtn.addClass("active-btn");
    this.tomorrowViewBtn.removeClass("active-btn");
    this.somedayViewBtn.removeClass("active-btn");
    this.trashBoxViewBtn.removeClass("active-btn");
    this.fixedViewBtn.removeClass("active-btn");

    this.trashBoxDropdownMenuButton.hide();
    this.fixedDropdownMenu.hide();
    this.lockedUsersDropdownMenu.hide();
};

viewerNav.tomorrowView = function () {
    this.todayViewBtn.removeClass("active-btn");
    this.tomorrowViewBtn.addClass("active-btn");
    this.somedayViewBtn.removeClass("active-btn");
    this.trashBoxViewBtn.removeClass("active-btn");
    this.fixedViewBtn.removeClass("active-btn");

    this.trashBoxDropdownMenuButton.hide();
    this.fixedDropdownMenu.hide();
    this.lockedUsersDropdownMenu.hide();
};

viewerNav.somedayView = function () {
    this.todayViewBtn.removeClass("active-btn");
    this.tomorrowViewBtn.removeClass("active-btn");
    this.somedayViewBtn.addClass("active-btn");
    this.trashBoxViewBtn.removeClass("active-btn");
    this.fixedViewBtn.removeClass("active-btn");

    this.trashBoxDropdownMenuButton.hide();
    this.fixedDropdownMenu.hide();
    this.lockedUsersDropdownMenu.hide();
};

viewerNav.fixedView = function () {
    this.todayViewBtn.removeClass("active-btn");
    this.tomorrowViewBtn.removeClass("active-btn");
    this.somedayViewBtn.removeClass("active-btn");
    this.trashBoxViewBtn.removeClass("active-btn");
    this.fixedViewBtn.addClass("active-btn");

    this.trashBoxDropdownMenuButton.hide();
    this.fixedDropdownMenu.show();
    this.lockedUsersDropdownMenu.hide();
};

viewerNav.trashBoxView = function () {
    this.todayViewBtn.removeClass("active-btn");
    this.tomorrowViewBtn.removeClass("active-btn");
    this.somedayViewBtn.removeClass("active-btn");
    this.trashBoxViewBtn.addClass("active-btn");
    this.fixedViewBtn.removeClass("active-btn");

    this.trashBoxDropdownMenuButton.show();
    this.fixedDropdownMenu.hide();
    this.lockedUsersDropdownMenu.hide();
};


viewerNav.userNav = function () {
    this.unlockedUsersViewBtn.show();
    this.lockedUsersViewBtn.show();
    this.incomeMessagesBtn.show();

    this.todayViewBtn.hide();
    this.tomorrowViewBtn.hide();
    this.somedayViewBtn.hide();
    this.trashBoxViewBtn.hide();
    this.fixedViewBtn.hide();

    this.openCreateTaskFormBtn.hide();
};

viewerNav.taskNav = function () {

    this.unlockedUsersViewBtn.hide();
    this.lockedUsersViewBtn.hide();
    this.incomeMessagesBtn.hide();

    this.todayViewBtn.show();
    this.tomorrowViewBtn.show();
    this.somedayViewBtn.show();
    this.trashBoxViewBtn.show();
    this.fixedViewBtn.show();

    this.openCreateTaskFormBtn.show();
};

