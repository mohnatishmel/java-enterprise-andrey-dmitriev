
function updateUser(id) {
    let user = viewList.getUser(id);
    let jsonUser = getJsonUser(user);

    sendJson(jsonUser, "UpdateUser", function success(data) {
        console.log(data.message);
    });
}

function unlockAllUserFromLockedView() {

    viewList.forEach(function (user) {
        user.setAccountNotLocked(true);
    })
}

function updateAllUsersFromViewList() {

    viewList.forEach(function (user) {

        user.hide();
        let jsonUser = getJsonUser(user);
        sendJson(jsonUser, "UpdateUser", function success(data) {
            console.log(data.message);
        });

    })
}
