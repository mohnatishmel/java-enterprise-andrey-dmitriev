
function logOutSuccess(data) {
    window.location.href = "/login";
    console.log(data.message);
}

function getPrincipal() {
    $.when(getJson("/principal")).then(function (jsonPrincipal) {
        principal = new Principal(jsonPrincipal);
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