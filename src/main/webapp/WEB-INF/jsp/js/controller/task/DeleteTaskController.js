
function deleteTask(id) {
    let task = viewList.getTask(id);
    task.hide();

    let jsonTask = getJsonTask(task);
    console.log(jsonTask);

    sendTask(jsonTask, "DeleteTask", function success(jsonTaskList) {
        fillViewList();
    });
}