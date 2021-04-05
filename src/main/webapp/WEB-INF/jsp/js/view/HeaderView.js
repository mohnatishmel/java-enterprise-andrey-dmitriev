let header = {}

header.taskViewBtn = $("#taskViewBtn");
header.userViewBtn = $("#userViewBtn");

header.userName = $("#userDropdownMenuButton");
header.setUserName = function (name) {
    this.userName.text(name);
};
header.switchToTaskView = function () {
    header.taskViewBtn.addClass("active-btn");
    header.userViewBtn.removeClass("active-btn");
};
header.switchToUserView = function () {
    header.taskViewBtn.removeClass("active-btn");
    header.userViewBtn.addClass("active-btn");
};

header.enableAdministratorFunctionality = function () {
    this.taskViewBtn.show();
    this.userViewBtn.show();
}

header.disableAdministratorFunctionality = function () {
    this.taskViewBtn.hide();
    this.userViewBtn.hide();
}