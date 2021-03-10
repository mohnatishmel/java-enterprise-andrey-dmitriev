
document.getElementById("submitUserFormLogin").addEventListener("click", function () {
    let token = userForm.getToken();
    authenticate(token,"Login", authenticateSuccess, authenticationError);
})

document.getElementById("submitUserFormRegister").addEventListener("click", function () {
    let token = userForm.getToken();
    authenticate(token, "Register", authenticateSuccess, authenticationError);
})

function authenticateSuccess(data) {
    userForm.hide();
    header.setUserName(data.credential.login);
    console.log("User " + data.credential.login + " authenticated");
}

function authenticationError(data) {
    const message = data.responseJSON.message;
    console.log("Authentication error: " + message);
    userForm.showAlert(message);
}