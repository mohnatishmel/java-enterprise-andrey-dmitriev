
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

function Task(jsonTask) {
    this.hasFile = jsonTask.hasFile;
    this.id = jsonTask.id;
    this.getId = function () {
        return this.id;
    }
    this.getName = function () {
        return document.getElementById("taskName" + this.id).innerText;
    }
    this.getDescription = function () {
        return document.getElementById("taskDescription" + this.id).innerText;
    }
    this.getDeadline = function () {
        return document.getElementById("taskDeadline" + this.id).innerText;
    }
    this.isInBasket = function () {
        return document.getElementById("inBasket" + this.id).value;
    }
    this.isFixed = function () {
        return document.getElementById("fixed" + this.id).value;
    }


    this.setName = function (val) {
        document.getElementById("taskName" + this.id).innerText = val;
    }
    this.setDescription = function (val) {
        return document.getElementById("taskDescription" + this.id).innerText = val;
    }
    this.setDeadline = function (val) {
        document.getElementById("taskDeadline" + this.id).innerText = val;
    }
    this.setInBasket = function (val) {
        document.getElementById("inBasket" + this.id).value = val;
    }
    this.setFixed = function (val) {
        document.getElementById("fixed" + this.id).value = val;
    }

    this.hide = function () {
        let $t = $("#headingTask" + this.id);
        fadeOut($t);
    }

    this.disappear = function () {
        let $t = $("#headingTask" + this.id);
        $t.fadeOut("fast");
    }

    this.toggleFixed = function () {
        let $li = $("#headingTask" + this.id)
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
        $("#headingTask" + this.id).find(".check-icon").addClass("expired");
        $("#daysLeft" + this.id).addClass("expired");
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
    task.hasFile = updateData.hasFile;
    task.id = updateData.getId();
    task.name = updateData.getName();
    task.description = updateData.getDescription();

    let date = new Date(updateData.getDeadline());
    task.deadLine = Date.UTC(date.getFullYear(), date.getMonth(), date.getDate());

    task.inBasket = updateData.isInBasket();
    task.fixed = updateData.isFixed();

    let jsonTask = JSON.stringify(task);
    return jsonTask;
}
