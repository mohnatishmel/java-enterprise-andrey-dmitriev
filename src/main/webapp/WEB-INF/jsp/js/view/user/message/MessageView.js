
let messageView = {}
messageView.append = function (user) {
    $("#viewList").append($($.parseHTML(user)).hide().fadeIn(200));
}

messageView.clear = function () {
    $("#viewList").empty()
}

function initMessage(jsonMessage, toolbox) {

    let $template = $(".message-template");
    let $newMessage = $template.clone();
    let id = jsonMessage.id;

    $newMessage.find("#headingMessage").attr("id", "headingMessage" + id);
    $newMessage.find("#headingMessage" + id).prepend(toolbox);

    $newMessage.find("#collapseControl").attr("data-target", "#collapse" + id);
    $newMessage.find("#collapseControl").attr("aria-controls", "collapse" + id);
    $newMessage.find("#collapseControl").attr("id", "collapseControl" + id);

    $newMessage.find("#collapse").attr("aria-labelledby", "headingMessage" + id);
    $newMessage.find("#collapse").attr("id", "collapse" + id);

    $newMessage.find("#senderName").attr("id", "senderName" + id).text(jsonMessage.userName);
    $newMessage.find("#messageBody").attr("id", "messageBody" + id).text(jsonMessage.messageBody);

    $newMessage.removeClass("d-none");
    $newMessage.removeClass("message-template");

    console.log("message_" + id + " initialized")
    return $newMessage.html();
}

function initMessageToolBox(id) {

    let $template = $("#messageToolBox");
    let $newToolBox = $template.clone();
    $newToolBox.attr("id", "messageToolBoxBox" + id)

    $newToolBox.find("#resolveRequestToggleSwitch").attr("id", "resolveRequestToggleSwitch" + id);
    $newToolBox.find("#deleteMessageBtn").attr("id", "deleteMessageBtn" + id);

    console.log("tool box for message_" + id + " initialized");

    $newToolBox.removeClass("d-none");

    return $newToolBox.html();
}
