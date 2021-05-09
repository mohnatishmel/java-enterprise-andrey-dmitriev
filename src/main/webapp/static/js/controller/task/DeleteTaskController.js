
function deleteTaskById(id) {
    let task = viewList.getTask(id);
    task.hide();

    let jsonTask = getJsonTask(task);
    console.log(jsonTask);

    deleteTask(jsonTask);
}

function deleteAllTasksFromViewList() {

    viewList.forEach(function (task) {

    task.disappear();

    let jsonTask = getJsonTask(task);
    console.log(jsonTask);

    deleteTask(jsonTask);

    })
}

function deleteTask(jsonTask) {
    sendJson(jsonTask, "tasks","DELETE" , function success(data) {
        console.log(data.message)
    });
}