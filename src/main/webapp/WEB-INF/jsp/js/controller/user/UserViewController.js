
function showUserList(command) {

    userView.clear();

    viewList = new UserList();

    $.when(getUsers(command)).then(function (jsonUserList) {
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

        toolbox = new userToolBox(id);
        newUser = new User(id);
        viewList.put(newUser);

        toolbox.lockUserAccountToggleSwitch.on("click", function () {
            newUser.toggleAccountNotLocked();
            updateUser(id);
        });

        toolbox.deleteUserBtn.click(function () {
            deleteUser(id);
        });

        if (jsonUser.accountNotLocked === false) {
            toolbox.lockUserAccountToggleSwitch.prop("checked", true);
        }
    });
}

