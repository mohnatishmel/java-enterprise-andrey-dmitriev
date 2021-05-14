
function showTaskListCommonView(url, page) {

    initCreateTaskForm();

    taskView.clear();

    viewList = new TaskList();

    $.when(getJsonPage(url, page, pageCapacity)).then(function (page) {
        mapTasksFromJsonForCommonView(page.content);
        console.log("showTaskListCommonView");
        initPagination(page ,page.total, function (page) {
            showTaskListCommonView(url, page);
        })
    })


}

function showTaskListTrashBoxView(url, page) {

    initCreateTaskForm();

    taskView.clear();

    viewList = new TaskList();

    $.when(getJsonPage(url, page, pageCapacity)).then(function (page) {
        mapTasksFromJsonForTrashBoxView(page.content);
        console.log("showTaskListCommonView");
        initPagination(page ,page.total, function (page) {
            showTaskListCommonView(url, page);
        })
    })
}

function mapTasksFromJsonForCommonView(jsonTaskList) {
    $.each(jsonTaskList, function (index, jsonTask) {

        let id = jsonTask.id

        let toolbox = initTaskToolBoxStandard(jsonTask.id, jsonTask.fixed);
        let newTask = initTask(jsonTask, toolbox);

        taskView.append(newTask);

        toolbox = new TaskToolBoxStandard(id,);
        newTask = new Task(jsonTask);
        viewList.put(newTask);

        toolbox.uploadToolboxBtn.on("click", function () {
            initUploadFileForm(id);
        });
        toolbox.toTrashBoxToolboxBtn.click(function () {
            newTask.toggleInBasket();
            updateTaskWithoutFetching(id);
        });
        toolbox.downloadToolboxBtn.on("click", function () {
            downloadFile(id).fail(function(data) {
                alert(data.responseJSON.message);
            });
        });
        toolbox.updateToolboxBtn.on("click", function () {
            initUpdateTaskForm(id);
        });
        toolbox.fixedToolboxToggleSwitch.on("click", function () {
            newTask.toggleFixed();
            updateTaskWithoutFetching(id)
        });

        if (jsonTask.fixed === true) {
            toolbox.fixedToolboxToggleSwitch.prop("checked", true);
        }

        let today = new Date();
        today.setHours(0,0,0,0)
        let taskDeadLine = new Date(jsonTask.deadLine);

        if (taskDeadLine.getTime() < today.getTime()) {
            newTask.expired();
        }

        if (newTask.hasFile) {
            $("#downloadToolboxBtn" + id).show();
        }
    });
}

function mapTasksFromJsonForTrashBoxView(jsonTaskList) {
    $.each(jsonTaskList, function (index, jsonTask) {

        let id = jsonTask.id

        let toolbox = initTaskToolBoxForTrashBox(jsonTask.id);
        let newTask = initTask(jsonTask, toolbox);

        taskView.append(newTask);

        toolbox = new TaskToolBoxForTrashBox(id);
        newTask = new Task(jsonTask);
        viewList.put(newTask);

        toolbox.deleteTaskBtn.on("click", function () {
            deleteTaskById(id);
        });
        toolbox.outOfTrashBoxBtn.click(function () {
            newTask.toggleInBasket();
            updateTaskWithoutFetching(id);
        });

        let today = new Date();
        today.setHours(0,0,0,0)
        let taskDeadLine = new Date(jsonTask.deadLine);

        if (taskDeadLine.getTime() < today.getTime()) {
            newTask.expired();
        }

    });
}

function getDaysLeft(deadLine) {
    deadLine.setHours(0, 0, 0, 0);
    let today = new Date();
    today.setHours(0, 0, 0, 0);

    let daysLeft = deadLine.getTime() - today;
    daysLeft = Math.floor(daysLeft / (1000 * 3600 * 24));

    return daysLeft;
}

