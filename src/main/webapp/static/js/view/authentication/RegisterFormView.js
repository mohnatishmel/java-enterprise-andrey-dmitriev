let registerForm = {}

registerForm.alert = $("#registerForm").find(".alert");
registerForm.loginInput = $("#registerFormLoginInput");
registerForm.passwordInput = $("#registerFormPasswordInput");
registerForm.submitBtn = $("#submitRegisterForm")
registerForm.switchBtn = $("#switchToLoginForm")

registerForm.hideAlert = function () {
    this.alert.hide();
    this.alert.empty();
}

registerForm.showAlert = function (message) {
    this.alert.show();
    this.alert.text(message);
}

registerForm.hide = function () {
    this.hideAlert();
    $("#registerForm").fadeOut();
}

registerForm.show = function () {
    this.hideAlert();
    $("#registerForm").fadeIn();
}

registerForm.getToken = function () {
    let token = {}
    token.login = this.loginInput.val();
    token.password = this.passwordInput.val();
    return token
}