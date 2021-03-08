

function authenticateSuccess(data) {
    hideUserForm();
    header.setUserName(data.credential.login);
    console.log("User " + data.credential.login + " authenticated");
}

function authenticationError(data) {
    const message = data.responseJSON.message;
    console.log("Authentication error: " + message);
    showUserFormAlert(message);
}

function initializeAuthenticationToken() {
    let token = {}
    token.login = document.getElementById("loginInput").value;
    token.password = document.getElementById("passwordInput").value;
    return token
}