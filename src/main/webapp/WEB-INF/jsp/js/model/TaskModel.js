function getTasks(command) {
    return $.getJSON("/?command=" + command);
}



function TaskList() {
    this.list = []
    this.put = function (task) {
        this.list.push(task)
    }
    this.getTask = function (id) {
        let task;
        let referenceId = id;
        let targetId;
        this.list.forEach(function (t, index) {
            targetId = t.id;
            if (referenceId == targetId) {
                task =  t;
            }
        })
        return task;
    }

    this.forEach = function (action) {
        this.list.forEach(function (t) {
            action(t);
        })
    }
}

function Task(id) {

    this.id = id;
    this.getId = function () {
        return this.id;
    }
    this.getName = function () {
        return document.getElementById("taskName" + id).innerText;
    }
    this.getDescription = function () {
        return document.getElementById("taskDescription" + id).innerText;
    }
    this.getDeadline = function () {
        return document.getElementById("taskDeadline" + id).innerText;
    }
    this.isInBasket = function () {
        return document.getElementById("inBasket" + id).value;
    }
    this.isFixed = function () {
        return document.getElementById("fixed" + id).value;
    }


    this.setName = function (val) {
        document.getElementById("taskName" + id).innerText = val;
    }
    this.setDescription = function (val) {
        return document.getElementById("taskDescription" + id).innerText = val;
    }
    this.setDeadline = function (val) {
        document.getElementById("taskDeadline" + id).innerText = val;
    }
    this.setInBasket = function (val) {
        document.getElementById("inBasket" + id).value = val;
    }
    this.setFixed = function (val) {
        document.getElementById("fixed" + id).value = val;
    }

    this.hide = function () {
        let $t = $("#headingTask" + id);
        fadeOut($t);
    }

    this.disappear = function () {
        let $t = $("#headingTask" + id);
        $t.fadeOut("fast");
    }

    this.toggleFixed = function () {
        let $li = $("#headingTask" + id)
        let f = this.isFixed();
        let t = true;
        if(String(f) == String(t)) {
            $li.find("#fixedIcon").removeClass("inactive")
            fadeOut($li)
            this.setFixed(false);
        } else {
            $li.find("#fixedIcon").addClass("inactive")
            fadeOut($li)
            this.setFixed(true);
        }
    }

    this.toggleInBasket = function () {
        let f = this.isInBasket();
        let t = true;
        if(String(f) == String(t)) {
            this.hide();
            this.setInBasket(false);
        } else {
            this.hide();
            this.setInBasket(true);
        }
    }

    this.expired = function () {
        $("#headingTask" + id).find(".check-icon").addClass("expired");
        $("#daysLeft" + id).addClass("expired");
    }
}

function TaskToolBoxStandard(id) {

    this.toTrashBoxToolboxBtn = $("#toTrashBoxToolboxBtn" + id);
    this.updateToolboxBtn = $("#updateToolboxBtn" + id);
    this.uploadToolboxBtn = $("#uploadToolboxBtn" + id);
    this.downloadToolboxBtn = $("#downloadToolboxBtn" + id);
    this.fixedToolboxToggleSwitch = $("#fixedToolboxToggleSwitch" + id);
}

function TaskToolBoxForTrashBox(id) {
    this.outOfTrashBoxBtn = $("#outOfTrashBoxBtn" + id);
    this.deleteTaskBtn = $("#deleteTaskBtn" + id);
}


function getJsonTask(updateData) {
    let task = {}
    task.id = updateData.getId();
    task.name = updateData.getName();
    task.description = updateData.getDescription();

    let date = new Date(updateData.getDeadline());
    task.deadLine = Date.UTC(date.getFullYear(), date.getMonth(), date.getDate());

    task.inBasket = updateData.isInBasket();
    task.fixed = updateData.isFixed();

    let jsonTask = JSON.stringify({task});
    return jsonTask;
}


function sendTask(data, command, success) {
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

