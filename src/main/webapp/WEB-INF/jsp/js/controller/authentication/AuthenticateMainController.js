
$(document).on("click", "#logOut", function () {
    currentUser = null;
    logout(logOutSuccess, logOutError);
})

function logOutSuccess(data) {
    // userForm.show();
    window.location.href = "/?command=LoginPage";
    console.log(data.message);
}

function logOutError(data) {
    let message = data.responseJSON.message;
    console.log("Authentication error: " + message);
    alert(message);
}

function getPrincipal() {
    $.when(getCurrentUser()).then(function (jsonPrincipal) {
        principal = new Principal(jsonPrincipal);
        // header.setUserName(jsonPrincipal.credential.login);
        console.log("User " + jsonPrincipal.credential.login + " authenticated");
        authorize();
    })
}


function authorize() {
    $.each(principal.roles, function (index, role) {
        if (role.role === "ADMIN_ROLE") {
            header.enableAdministratorFunctionality();
        }
    })
}