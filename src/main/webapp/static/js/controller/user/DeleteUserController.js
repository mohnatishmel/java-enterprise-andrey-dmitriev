
function deleteUserById(id) {
    let user = viewList.getUser(id);
    user.hide();

    let jsonUser = getJsonUser(user);
    console.log(jsonUser);

    deleteUser(jsonUser);
}

function deleteAllUsersFromViewList() {

    viewList.forEach(function (user) {

        user.disappear();

        let jsonUser = getJsonUser(user);
        console.log(jsonUser);

        deleteUser(jsonUser);

    })
}

function deleteUser(jsonUser) {
    sendJson(jsonUser, "/rest/users/", "DELETE", function success(data) {
        console.log(data.message);
    });
}