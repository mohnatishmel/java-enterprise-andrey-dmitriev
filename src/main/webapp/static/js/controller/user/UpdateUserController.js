
function updateUser(id, command) {
    let user = viewList.getUser(id);
    let jsonUser = getJsonUser(user);

    sendJson(jsonUser, command, function success(data) {
        console.log(data.message);
    });
}

function updateUserIsLocked(id) {
    updateUser(id, "UpdateUserIsLocked");
}

function unlockAllUserFromLockedView() {

    viewList.forEach(function (user) {
        user.setAccountNotLocked(true);
        user.hide();
        let jsonUser = getJsonUser(user);
        sendJson(jsonUser, "UpdateUserIsLocked", function success(data) {
            console.log(data.message);
        });
    })
}