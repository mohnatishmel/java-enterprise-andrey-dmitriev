let createTaskForm = {}

createTaskForm.getTaskName = function () {
    return document.getElementById("createTaskName").value;
}
createTaskForm.getTaskDescription = function () {
    return document.getElementById("createTaskDescription").value;
}
createTaskForm.getTaskDeadLine = function () {
    return document.getElementById("createTaskDedLine").value;
}

createTaskForm.setTaskName = function (val) {
    document.getElementById("createTaskName").value = val;
}
createTaskForm.setTaskDescription = function (val) {
    document.getElementById("createTaskDescription").value = val
}
createTaskForm.setTaskDeadLine = function (val) {
    document.getElementById("createTaskDedLine").value = val
}

createTaskForm.toggle = function () {
    document.getElementById('openCreateTaskFormBtn').click();
}