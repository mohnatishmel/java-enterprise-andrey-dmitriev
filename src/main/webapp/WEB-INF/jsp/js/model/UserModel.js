function getUsers(command) {
    return $.getJSON("/?command=" + command);
}

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

function userToolBox(id) {
    this.lockUserAccountToggleSwitch = $("#lockUserAccountToggleSwitch" + id);
    this.deleteUserBtn = $("#deleteUserBtn" + id);
}

function getJsonUser(updateData) {
    let user = {}
    user.id = updateData.getId();
    user.name = updateData.getName();
    user.accountNotLocked = updateData.isAccountNotLocked();

    let jsonTask = JSON.stringify({user});
    return jsonTask;
}


function sendUser(data, command, success) {
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    $.ajax({
        url: "/?command=" + command,
        type: 'POST',
        dataType: 'json',
        data: data,
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            success(data);
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}

