function initCreateTaskForm() {

    let date = new Date();

    switch (currentView) {
        case "TOMORROW":
            date.setDate(date.getDate() + 1)
            date.setHours(0, 0, 0, 0);
            break;
        case "SOMEDAY":
            date.setDate(date.getDate() + 2)
            date.setHours(0, 0, 0, 0);
            break;
        default:
            date.setHours(0, 0, 0, 0);;
    }

    let deadlineString;
    deadlineString = "" + date.getFullYear();
    deadlineString += "-" + ("0" + (date.getMonth() + 1)).slice(-2);
    deadlineString += "-" + ("0" + date.getDate()).slice(-2);

    createTaskForm.setTaskDeadLine(deadlineString);
}

function createTask() {
    var task = {};
    task.id = 0;
    task.name = createTaskForm.getTaskName();
    task.description =  createTaskForm.getTaskDescription();

    let date = new Date(createTaskForm.getTaskDeadLine());
    task.deadLine = Date.UTC(date.getFullYear(), date.getMonth(), date.getDate());

    task.fixed = false;
    task.inBasket = false;

    var jsonTask = JSON.stringify({task});
    console.log(jsonTask);

    sendTask(jsonTask, "CreateTask",function (data) {
        fillViewList();
    });
}

$(document).on("click", "#createTaskBtn", function () {
    createTask();
    createTaskForm.toggle();
});