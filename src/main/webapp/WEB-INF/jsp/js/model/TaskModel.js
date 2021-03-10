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

}

function TaskToolBoxStandard(id) {

    this.toTrashBoxToolboxBtn = $("#toTrashBoxToolboxBtn" + id);
    this.updateToolboxBtn = $("#updateToolboxBtn" + id);
    this.uploadToolboxBtn = $("#uploadToolboxBtn" + id);
    this.downloadToolboxBtn = $("#downloadToolboxBtn" + id);
    this.fixedToolboxToggleSwitch = function () {
        return document.getElementById("fixedToolboxToggleSwitch" + id);
    }

}