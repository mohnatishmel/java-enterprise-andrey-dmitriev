function logOutSuccess(data) {
    showUserForm();
    console.log(data.message);
}

function logOutError(data) {
    let message = data.responseJSON.message;
    console.log("Authentication error: " + message);
    alert(message);
}