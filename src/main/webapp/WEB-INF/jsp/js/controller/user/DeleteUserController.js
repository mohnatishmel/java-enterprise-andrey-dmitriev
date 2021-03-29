
function deleteUser(id) {
    let user = viewList.getUser(id);
    user.hide();

    let jsonUser = getJsonUser(user);
    console.log(jsonUser);

    sendTask(jsonUser, "DeleteUser", function success(data) {
        console.log(data.message);
    });
}