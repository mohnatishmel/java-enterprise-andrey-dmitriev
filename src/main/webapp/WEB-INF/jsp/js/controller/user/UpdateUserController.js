
function updateUser(id) {
    let user = viewList.getUser(id);
    let jsonTask = getJsonUser(user);

    sendUser(jsonTask, "UpdateUser", function success(data) {
        console.log(data.message);
    });
}

