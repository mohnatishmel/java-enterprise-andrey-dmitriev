
let userView = {}
userView.append = function (user) {
    $("#viewList").append($($.parseHTML(user)).hide().fadeIn(200));
}

    userView.clear = function () {
        $("#viewList").empty()
    }

    function initUser(jsonUser, toolbox) {

        let $template = $("#user-template");
        let $newUser = $template.clone();
        let id = jsonUser.id;

        $newUser.find("#headingUser").attr("id", "headingUser" + id);
        $newUser.find("#headingUser" + id).append(toolbox);
        $newUser.find("#accountNotLocked").attr("id", "accountNotLocked" + id).val(jsonUser.accountNotLocked);
        $newUser.find("#userName").attr("id", "userName" + id).text(jsonUser.credential.login);

        $newUser.removeClass("d-none");
        $newUser.removeClass("user-template");

        console.log("user_" + id + " initialized")
        return $newUser.html();
    }

    function initUserToolBox(id) {

        let $template = $("#userToolBox");
        let $newToolBox = $template.clone();
        $newToolBox.attr("id", "userToolBoxBox" + id)

        $newToolBox.find("#lockUserAccountToggleSwitch").attr("id", "lockUserAccountToggleSwitch" + id);
        $newToolBox.find("#deleteUserBtn").attr("id", "deleteUserBtn" + id);

        console.log("tool box for user_" + id + " initialized");

        $newToolBox.removeClass("d-none");

        return $newToolBox.html();
    }
