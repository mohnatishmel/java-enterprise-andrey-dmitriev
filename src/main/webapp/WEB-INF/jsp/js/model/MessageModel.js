
function convertUnlockRequestMessageToJson(message) {
    let jsonMessage = JSON.stringify({message});
    return jsonMessage;
}

function MessageList() {
    this.list = [];
    this.put = function (message) {
        this.list.push(message)
    }
    this.getMessage = function (id) {
        let message;
        let referenceId = id;
        let targetId;
        this.list.forEach(function (t, index) {
            targetId = t.id;
            if (referenceId == targetId) {
                message =  t;
            }
        })
        return message;
    }
}

function Message(jsonUnlockMessage) {

    this.id = jsonUnlockMessage.id;

    this.userId = jsonUnlockMessage.userId;

    this.userName = jsonUnlockMessage.userName;

    this.messageBody = jsonUnlockMessage.messageBody;

    this.hide = function () {
        let $t = $("#headingMessage" + this.id);
        fadeOut($t);
    }
}

function MessageToolBox(id) {
    this.resolveRequestToggleSwitch = $("#resolveRequestToggleSwitch" + id);
    this.deleteMessageBtn = $("#deleteMessageBtn" + id);
}