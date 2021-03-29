function initUpdateTaskForm(id) {
    let task = viewList.getTask(id);

    console.log(task)

    updateTaskForm.setId(id);
    updateTaskForm.setTaskName(task.getName())
    updateTaskForm.setTaskDescription(task.getDescription())

    let deadlineString = task.getDeadline();
    let date = new Date(deadlineString);
    deadlineString = "" + date.getFullYear();
    deadlineString += "-" + ("0" + (date.getMonth() + 1)).slice(-2);
    deadlineString += "-" + ("0" + date.getDate()).slice(-2);

    updateTaskForm.setTaskDeadline(deadlineString);
}

function initTaskFromUpdateTaskForm() {
    let id = updateTaskForm.getId();
    console.log(id)
    let task = viewList.getTask(id);
    console.log(task)
    task.setName(updateTaskForm.getTaskName())
    task.setDescription(updateTaskForm.getTaskDescription())

    let date = new Date(updateTaskForm.getTaskDeadline());
    task.setDeadline(date.toDateString());
    return id;
    // task.setDeadLine(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()));
}

function updateTask(id) {
    let task = viewList.getTask(id);
    let jsonTask = getJsonTask(task);
    console.log(jsonTask);

    sendTask(jsonTask, "UpdateTask", function success(data) {
        console.log(data.message);
        fillViewList();
    });
}

function updateTaskWithoutFetching(id) {
    let task = viewList.getTask(id);
    let jsonTask = getJsonTask(task);
    console.log(jsonTask);

    sendTask(jsonTask, "UpdateTask", function success(data) {
        console.log(data.message);
    });
}

// function taskInBasketUpdate(id, inBasket) {
//     console.log("taskInBasketUpdate Called")
//     let $li = $("#headingTask" + id);
//     fadeOut($li);
//
//     $.each(viewList, function (index, task) {
//         if (task.id === id) {
//             task.inBasket = inBasket;
//             var date = new Date(task.deadLine);
//             task.deadLine = date.getTime();
//
//             var jsonTask = JSON.stringify({task});
//             console.log(jsonTask);
//
//             sendTask(jsonTask, "UpdateTask", function success (data) {
//                 viewList = data;
//             });
//         }
//     });
// }

$(document).on("click", "#updateTask", function () {
    let id = initTaskFromUpdateTaskForm();
    updateTask(id);
});
