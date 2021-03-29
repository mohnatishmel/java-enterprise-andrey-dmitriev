var userForm = {}

userForm.alert = $("#userForm").find(".alert");
userForm.loginInput = document.getElementById("loginInput");
userForm.passwordInput = document.getElementById("passwordInput");

userForm.hideAlert = function () {
    this.alert.hide();
    this.alert.empty();
}

userForm.showAlert = function (message) {
    this.alert.show();
    this.alert.text(message);
}

userForm.hide = function () {
    this.hideAlert();
    $("#userForm").modal("hide");
}

userForm.show = function () {
    this.hideAlert();
    $("#userForm").modal("show");
}

userForm.getToken = function () {
    let token = {}
    token.login = this.loginInput.value;
    token.password = this.passwordInput.value;
    return token
}