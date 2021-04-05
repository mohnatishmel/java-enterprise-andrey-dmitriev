
function showMessageList(command) {

    messageView.clear();

    viewList = new MessageList();

    $.when(getJson(command)).then(function (jsonMessageList) {
        mapMessageFromJson(jsonMessageList);
    })
}

function mapMessageFromJson(jsonMessageList) {
    $.each(jsonMessageList, function (index, jsonMessage) {

        let id = jsonMessage.id

        let toolbox = initMessageToolBox(jsonMessage.id);
        let newMessage = initMessage(jsonMessage, toolbox);

        userView.append(newMessage);

        toolbox = new MessageToolBox(id);
        newMessage = new Message(jsonMessage);
        viewList.put(newMessage);

        toolbox.resolveRequestToggleSwitch.on("click", function () {
            resolveUnlockUserRequest(id);
        });

        toolbox.deleteMessageBtn.click(function () {
            deleteMessage(id);
        });
    });
}

function resolveUnlockUserRequest(id) {
    let message = viewList.getMessage(id);
    message.hide();
    message = convertUnlockRequestMessageToJson(message);
    sendJson(message, "ResolveUserUnlockRequest", function (data) {
        console.log(data.message);
    })
}

function deleteMessage(id) {
    let message = viewList.getMessage(id);
    message.hide();
    message = convertUnlockRequestMessageToJson(message);
    sendJson(message, "DeleteUserUnlockRequest", function (data) {
        console.log(data.message);
    })
}

