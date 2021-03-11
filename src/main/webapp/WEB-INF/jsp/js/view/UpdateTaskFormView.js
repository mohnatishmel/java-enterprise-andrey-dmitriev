var updateTaskForm = {}

updateTaskForm.setId = function (val) {
    document.getElementById('updateTaskId').value = val;
}

updateTaskForm.setTaskName = function (val) {
    document.getElementById('updateTaskName').value = val;
}

updateTaskForm.setTaskDescription = function (val) {
    document.getElementById('updateTaskDescription').value = val;
}

updateTaskForm.setTaskDeadline = function (val) {
    document.getElementById('updateTaskDeadline').value = val;
}

updateTaskForm.getId = function () {
   return document.getElementById('updateTaskId').value ;
}

updateTaskForm.getTaskName = function () {
   return document.getElementById('updateTaskName').value;
}

updateTaskForm.getTaskDescription = function () {
   return document.getElementById('updateTaskDescription').value;
}

updateTaskForm.getTaskDeadline = function () {
   return document.getElementById('updateTaskDeadline').value;
}

