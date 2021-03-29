
document.getElementById("submitUserFormLogin").addEventListener("click", function () {
    let token = userForm.getToken();
    authenticate(token,"Login", authenticateSuccess, authenticationError);
})

document.getElementById("submitUserFormRegister").addEventListener("click", function () {
    let token = userForm.getToken();
    authenticate(token, "Register", authenticateSuccess, authenticationError);
})

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

function authenticationError(data) {
    const message = data.responseJSON.message;
    console.log("Authentication error: " + message);
    userForm.showAlert(message);
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