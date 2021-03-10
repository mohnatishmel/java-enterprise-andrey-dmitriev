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

function fixedUpdate(id, fixed) {
    console.log("taskInBasketUpdate Called on task id:" +id )
    var $li = $("#headingTask" + id);
    if (fixed === true) {
        $li.find("#fixedIcon").removeClass("inactive")
        fadeOut($li)
    } else {
        $li.find("#fixedIcon").addClass("inactive")
        fadeOut($li)
    }

    $.each(viewList, function (index, task) {
        if (task.id === id) {
            task.fixed = fixed;
            var date = new Date(task.deadLine);
            task.deadLine = date.getTime();

            var jsonTask = JSON.stringify({task});
            console.log(jsonTask);

            sendTask(jsonTask, "UpdateTask", function success (data) {
                viewList = data;
            });
        }
    });
}

function updateTask(id) {
    let jsonTask = getJsonTask(id);
    console.log(jsonTask);

    sendTask(jsonTask, "UpdateTask", function success(jsonTaskList) {
        fillTaskTable();
    });
}

function getJsonTask(id) {
    let updateData = viewList.getTask(id);
    let task = {}
    task.id = updateData.getId();
    task.name = updateData.getName();
    task.description = updateData.getDescription();

    let date = new Date(updateData.getDeadline());
    task.deadLine = Date.UTC(date.getFullYear(), date.getMonth(), date.getDate());

    task.inBasket = updateData.isInBasket();
    task.fixed = updateData.isFixed();

    let jsonTask = JSON.stringify({task});
    return jsonTask;
}


function sendTask(data, command, success) {
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    $.ajax({
        url: "/?command=" + command,
        type: 'POST',
        dataType: 'json',
        data: data,
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            success(data);
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}

$(document).on("click", "#updateTask", function () {
    let id = initTaskFromUpdateTaskForm();
    updateTask(id);
});
