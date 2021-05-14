

loginForm.submitBtn.on("click", function () {
    login();
})

registerForm.submitBtn.on("click", function () {
    register();
})

loginForm.switchBtn.on("click", function () {
    $("#loginForm").fadeOut("fast",function () {
        $("#registerForm").fadeIn()
    });
});

registerForm.switchBtn.on("click", function () {
    $("#registerForm").fadeOut("fast",function () {
        $("#loginForm").fadeIn()

    });
});


loginForm.loginInput.keypress(function(e) {
    if (e.which === 13) {
        login();
    }
});

loginForm.passwordInput.keypress(function(e) {
    if (e.which === 13) {
       login();
    }
});

registerForm.loginInput.keypress(function(e) {
    if (e.which === 13) {
        register();
    }
});

registerForm.passwordInput.keypress(function(e) {
    if (e.which === 13) {
        register();
    }
});

function login() {
    let token = loginForm.getToken();
    authenticate(token,"/login", authenticateSuccess, loginError);
}

function register() {
    let token = registerForm.getToken();
    authenticate(token,"/register", authenticateSuccess, registrationError);
}

function authenticateSuccess(jsonPrinciple) {
    principal = new Principal(jsonPrinciple);
    if (jsonPrinciple.accountNotLocked === false) {
        accountLockedForm.show();
    } else {
        window.location.href = "/main";
        console.log("User " + jsonPrinciple.credential.login + " authenticated");
    }
}

function loginError(data) {
    const message = data.responseJSON.message;
    console.log("Authentication error: " + message);
    loginForm.showAlert(message);
}

function registrationError(data) {
    const message = data.responseJSON.message;
    console.log("Authentication error: " + message);
    registerForm.showAlert(message);
}

function switchToAuthenticationForm() {
    accountLockedForm.hide();
}

$("#accountLockedFormSwitchToLoginBtn").on("click", function () {
    switchToAuthenticationForm();
})