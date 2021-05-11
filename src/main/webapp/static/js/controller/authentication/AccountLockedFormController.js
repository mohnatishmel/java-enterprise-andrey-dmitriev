

accountLockedForm.submitBtn.on("click", function () {
    let message = accountLockedForm.getMessage();
    let jsonMessage = convertUnlockRequestMessageToJson(message);

    sendJson(jsonMessage, "/rest/unlock/requests/messages", "POST", function (data) {
        accountLockedForm.success();
        console.log(data.message);
    } )
})