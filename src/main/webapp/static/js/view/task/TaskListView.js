

let taskView = {}
taskView.append = function (task) {
    $("#viewList").append($($.parseHTML(task)).hide().fadeIn(200));
}

taskView.clear = function () {
    $("#viewList").empty()
}

function initTask(jsonTask, toolbox) {
    let taskDeadline = new Date(jsonTask.deadLine);
    let daysLeft = getDaysLeft(taskDeadline);
    let $template = $(".task-template");
    let $newTask = $template.clone();
    let id = jsonTask.id;

    $newTask.attr("id", "task" + id);

    $newTask.find("#collapseControl").attr("data-target", "#collapse" + id);
    $newTask.find("#collapseControl").attr("aria-controls", "collapse" + id);
    $newTask.find("#collapseControl").attr("id", "collapseControl" + id);

    $newTask.find("#inBasket").attr("id", "inBasket" + id).val(jsonTask.inBasket);;
    $newTask.find("#fixed").attr("id", "fixed" + id).val(jsonTask.fixed);

    $newTask.find("#collapse").attr("aria-labelledby", "headingTask" + id);
    $newTask.find("#collapse").attr("id", "collapse" + id);

    $newTask.find("#headingTask").attr("id", "headingTask" + id);
    $newTask.find("#headingTask" + id).prepend(toolbox);

    $newTask.find("#taskName").attr("id", "taskName" + id).text(jsonTask.name);
    $newTask.find("#taskDescription").attr("id", "taskDescription" + id).text(jsonTask.description);
    $newTask.find("#taskDeadline").attr("id", "taskDeadline" + id).text(taskDeadline.toDateString());
    $newTask.find("#daysLeft").attr("id", "daysLeft" + id).text(daysLeft + " days");


    $newTask.removeClass("d-none");
    $newTask.removeClass("task-template");

    if (jsonTask.fixed === true) {
        $newTask.find(".check-icon").removeClass("inactive");
        $newTask.find(".check-icon").addClass("done");
    }

    console.log("task_" + id + " initialized")
    return $newTask.html();
}

    function initTaskToolBoxStandard(id, fixed) {

    let $template = $("#taskToolBoxCommonTemplate");
    let $newToolBox = $template.clone();
    $newToolBox.attr("id", "taskToolBox" + id)

    $newToolBox.find("#toTrashBoxToolboxBtn").attr("id", "toTrashBoxToolboxBtn" + id);
    $newToolBox.find("#updateToolboxBtn").attr("id", "updateToolboxBtn" + id);
    $newToolBox.find("#uploadToolboxBtn").attr("id", "uploadToolboxBtn" + id);
    $newToolBox.find("#downloadToolboxBtn").attr("id", "downloadToolboxBtn" + id);
    $newToolBox.find("#fixedToolboxToggleSwitch").attr("id", "fixedToolboxToggleSwitch" + id);

    console.log("tool box for task_" + id + " initialized")

    $newToolBox.removeClass("d-none");
    return $newToolBox.html();
}

function initTaskToolBoxForTrashBox(id) {

    let $template = $("#taskToolBoxBoxForTrashBox");
    let $newToolBox = $template.clone();
    $newToolBox.attr("id", "taskToolBox" + id)

    $newToolBox.find("#outOfTrashBoxBtn").attr("id", "outOfTrashBoxBtn" + id);
    $newToolBox.find("#deleteTaskBtn").attr("id", "deleteTaskBtn" + id);

    console.log("tool box for task_" + id + " initialized");

    $newToolBox.removeClass("d-none");

    let result = $newToolBox.html();
    return result;
}
