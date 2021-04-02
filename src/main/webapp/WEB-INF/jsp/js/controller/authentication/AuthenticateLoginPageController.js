

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
    if (e.which == 13) {
        login();
    }
});

loginForm.passwordInput.keypress(function(e) {
    if (e.which == 13) {
       login();
    }
});

registerForm.loginInput.keypress(function(e) {
    if (e.which == 13) {
        register();
    }
});

registerForm.passwordInput.keypress(function(e) {
    if (e.which == 13) {
        register();
    }
});

function login() {
    let token = loginForm.getToken();
    authenticate(token,"Login", authenticateSuccess, loginError);
}

function register() {
    let token = registerForm.getToken();
    authenticate(token,"Register", authenticateSuccess, registrationError);
}

function authenticateSuccess(jsonPrinciple) {
    principal = new Principal(jsonPrinciple);
    if (jsonPrinciple.accountNotLocked === false) {
        accountLockedForm.show();
    } else {
        window.location.href = "/?command=MainPage";
        header.setUserName(jsonPrinciple.credential.login);
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

function getPrincipal() {
    $.when(getCurrentUser()).then(function (jsonPrincipal) {
        principal = new Principal(jsonPrincipal);
        // header.setUserName(jsonPrincipal.credential.login);
        console.log("User " + jsonPrincipal.credential.login + " authenticated");
    })
}