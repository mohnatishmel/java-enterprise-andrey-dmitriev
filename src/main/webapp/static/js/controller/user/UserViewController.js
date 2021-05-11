
function showUserList(command) {

    userView.clear();

    viewList = new UserList();

    $.when(getJson(command)).then(function (jsonUserList) {
        mapUsersFromJson(jsonUserList);
        console.log("userView");
    })
}

function mapUsersFromJson(jsonUserList) {
    $.each(jsonUserList, function (index, jsonUser) {

        let id = jsonUser.id

        let toolbox = initUserToolBox(jsonUser.id);
        let newUser = initUser(jsonUser, toolbox);

        userView.append(newUser);

        toolbox = new UserToolBox(id);
        newUser = new User(id);
        viewList.put(newUser);

        toolbox.lockUserAccountToggleSwitch.on("click", function () {
            newUser.toggleAccountNotLocked();
            updateUserIsLocked(id);
        });

        toolbox.deleteUserBtn.click(function () {
            deleteUserById(id);
        });

        if (jsonUser.accountNotLocked === false) {
            toolbox.lockUserAccountToggleSwitch.prop("checked", true);
        }
    });
}

