

accountLockedForm.submitBtn.on("click", function () {
    let message = accountLockedForm.getMessage();
    let jsonMessage = convertUnlockRequestMessageToJson(message);

    sendJson(jsonMessage, "CreateUserUnlockRequest", function (data) {
        accountLockedForm.success();
        console.log(data.message);
    } )
})