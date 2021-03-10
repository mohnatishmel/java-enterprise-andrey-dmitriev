var header = {};

header.userName = $("#userDropdownMenuButton");

header.setUserName = function (name) {
    this.userName.text(name);
}