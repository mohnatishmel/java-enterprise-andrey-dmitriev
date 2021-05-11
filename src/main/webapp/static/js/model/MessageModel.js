
function convertUnlockRequestMessageToJson(message) {
    return JSON.stringify(message);

}

function MessageList() {
    this.list = [];
    this.put = function (message) {
        this.list.push(message)
    }
    this.getMessage = function (id) {
        let message = null;
        let referenceId = id;
        let targetId;
        this.list.forEach(function (t) {
            targetId = t.id;
            if (referenceId === targetId) {
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