
function UserList() {
    this.list = [];
    this.put = function (user) {
        this.list.push(user)
    }
    this.getUser = function (id) {
        let user;
        let referenceId = id;
        let targetId;
        this.list.forEach(function (t, index) {
            targetId = t.id;
            if (referenceId == targetId) {
                user =  t;
            }
        })
        return user;
    }

    this.forEach = function (action) {
        this.list.forEach(function (t) {
            action(t);
        })
    }
}

function User(id) {

    this.id = id;
    this.getId = function () {
        return this.id;
    }
    this.getName = function () {
        return document.getElementById("userName" + id).innerText;
    }

    this.isAccountNotLocked = function () {
        return document.getElementById("accountNotLocked" + id).value;
    }



    this.setName = function (val) {
        document.getElementById("userName" + id).innerText = val;
    }

    this.setAccountNotLocked = function (val) {
        document.getElementById("accountNotLocked" + id).value = val;
    }

    this.hide = function () {
        let $t = $("#headingUser" + id);
        fadeOut($t);
    }

    this.disappear = function () {
        let $t = $("#headingUser" + id);
        $t.fadeOut("fast");
    }

    this.toggleAccountNotLocked = function () {
        let f = this.isAccountNotLocked();
        let t = true;
        if(String(f) == String(t)) {
            this.hide();
            this.setAccountNotLocked(false);
        } else {
            this.hide();
            this.setAccountNotLocked(true);
        }
    }
}

function UserToolBox(id) {
    this.lockUserAccountToggleSwitch = $("#lockUserAccountToggleSwitch" + id);
    this.deleteUserBtn = $("#deleteUserBtn" + id);
}

function getJsonUser(updateData) {
    let user = {}
    user.id = updateData.getId();
    user.name = updateData.getName();
    user.accountNotLocked = updateData.isAccountNotLocked();

    let jsonTask = JSON.stringify(user);
    return jsonTask;
}

