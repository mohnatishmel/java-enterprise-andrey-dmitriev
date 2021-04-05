let loginForm = {}

loginForm.alert = $("#loginForm").find(".alert");
loginForm.loginInput = $("#loginFormLoginInput");
loginForm.passwordInput = $("#loginFormPasswordInput");
loginForm.submitBtn = $("#submitLoginForm")
loginForm.switchBtn = $("#switchToRegisterForm")

loginForm.hideAlert = function () {
    this.alert.hide();
    this.alert.empty();
}

loginForm.showAlert = function (message) {
    this.alert.show();
    this.alert.text(message);
}

loginForm.hide = function () {
    this.hideAlert();
    $("#loginForm").fadeOut();
}

loginForm.show = function () {
    this.hideAlert();
    $("#loginForm").fadeIn();
}

loginForm.getToken = function () {
    let token = {}
    token.login = this.loginInput.val();
    token.password = this.passwordInput.val();
    return token
}