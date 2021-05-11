
function updateUser(id, command, method) {
    let user = viewList.getUser(id);
    let jsonUser = getJsonUser(user);

    sendJson(jsonUser, command, method, function success(data) {
        console.log(data.message);
    });
}

function updateUserIsLocked(id) {
    updateUser(id, "/rest/users/locked", "PUT");
}

function unlockAllUserFromLockedView() {

    viewList.forEach(function (user) {
        user.setAccountNotLocked(true);
        user.hide();
        let jsonUser = getJsonUser(user);
        sendJson(jsonUser, "/rest/users/locked", "PUT", function success(data) {
            console.log(data.message);
        });
    })
}