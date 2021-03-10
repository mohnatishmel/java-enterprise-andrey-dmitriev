
function fillTaskTable(jsonTaskList) {
    switch (currentView) {
        case "TODAY":
            document.getElementById('todayViewBtn').click();
            break;
        case "TOMORROW":
            document.getElementById('tomorrowViewBtn').click();
            break;
        case "TRASH_BOX":
            document.getElementById('trashBoxViewBtn').click();
            break;
        case "FIXED":
            document.getElementById('fixedViewBtn').click();
            break;
        default:
            document.getElementById('somedayViewBtn').click();
    }
}

function publishTaskList(command) {

    taskView.clear();

    viewList = new TaskList();

    $.when(getTasks(command)).then(function (jsonTaskList) {
        mapTasksFromJson(jsonTaskList);
        console.log("publishTaskList");
    })
}

function mapTasksFromJson(jsonTaskList) {
    $.each(jsonTaskList, function (index, jsonTask) {

        let id = jsonTask.id

        let toolbox = initTaskToolBoxStandard(jsonTask.id, jsonTask.fixed);
        let newTask = initTask(jsonTask, toolbox);

        taskView.append(newTask);

        toolbox = new TaskToolBoxStandard(id);
        newTask = new Task(id);
        viewList.put(newTask);

        toolbox.uploadToolboxBtn.on("click", function () {
            initUploadFileForm(id);
        });
        toolbox.toTrashBoxToolboxBtn.click(function () {
            taskInBasketUpdate(id, true)
        })
        toolbox.downloadToolboxBtn.on("click", function () {
            downloadFile(id);
        });
        toolbox.updateToolboxBtn.on("click", function () {
            initUpdateTaskForm(id);
        });
        toolbox.fixedToolboxToggleSwitch().addEventListener("click", function () {
            newTask.toggleFixed();
            updateTask(id)
        });
    });
}

function getDaysLeft(deadLine) {
    deadLine.setHours(0, 0, 0, 0);
    let today = new Date();
    today.setHours(0, 0, 0, 0);

    var daysLeft = deadLine.getTime() - today;
    daysLeft = Math.floor(daysLeft / (1000 * 3600 * 24));

    return daysLeft;
}