const userNav = {}

userNav.unlockedUsersViewBtn = $("#unlockedUsersViewBtn");
userNav.lockedUsersViewBtn = $("#lockedUsersViewBtn");
userNav.incomeMessagesBtn = $("#incomeMessagesBtn");

userNav.unlockedUsersView = function () {
    this.unlockedUsersViewBtn.addClass("active-btn");
    this.lockedUsersViewBtn.removeClass("active-btn");
    this.incomeMessagesBtn.removeClass("active-btn");
};

userNav.lockedUsersView = function () {
    this.unlockedUsersViewBtn.removeClass("active-btn");
    this.lockedUsersViewBtn.addClass("active-btn");
    this.incomeMessagesBtn.removeClass("active-btn");
};

userNav.incomeMessages = function () {
    this.unlockedUsersViewBtn.removeClass("active-btn");
    this.lockedUsersViewBtn.removeClass("active-btn");
    this.incomeMessagesBtn.addClass("active-btn");
};

userNav.hide = function () {
    $("#user-view-nav").children("div").addClass("d-none");
}

userNav.show = function () {
    $("#user-view-nav").children("div").removeClass("d-none");
}
