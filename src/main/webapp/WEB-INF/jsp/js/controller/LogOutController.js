$(document).on("click", "#logOut", function () {
    logout(logOutSuccess, logOutError);
})

function logOutSuccess(data) {
    userForm.show();
    console.log(data.message);
}

function logOutError(data) {
    let message = data.responseJSON.message;
    console.log("Authentication error: " + message);
    alert(message);
}