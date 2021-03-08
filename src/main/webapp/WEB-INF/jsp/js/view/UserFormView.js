function hideUserFormAlert() {
    let $alert = $("#userForm").find(".alert");
    $alert.hide();
    $alert.find(".alert").empty();
}

function showUserFormAlert(message) {
    $("#userForm").find(".alert").text(message);
}

function hideUserForm() {
    hideUserFormAlert()
    $("#userForm").modal("hide");
}

function showUserForm() {
    hideUserFormAlert()
    $("#userForm").modal("show");
}