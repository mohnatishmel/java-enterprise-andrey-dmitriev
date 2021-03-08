
document.getElementById("submitUserFormLogin").addEventListener("click", function () {
    let token = initializeAuthenticationToken()
    authenticate(token,"Login", authenticateSuccess, authenticationError);
})

document.getElementById("submitUserFormRegister").addEventListener("click", function () {
    let token = initializeAuthenticationToken()
    authenticate(token, "Register", authenticateSuccess, authenticationError);
})