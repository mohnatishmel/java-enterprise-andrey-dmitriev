let accountLockedForm = {}

accountLockedForm.messageBody = function () {
    return $("#messageToAdministrator").val();
}
accountLockedForm.submitBtn = $("#accountLockedFormSubmitBtn");

accountLockedForm.hide = function () {
    $("#accountLockedForm").modal("hide");
}

accountLockedForm.show = function () {
    this.hideSuccessMessage();
    this.showForm();
    $("#accountLockedForm").modal("show");
}

accountLockedForm.getMessage = function () {
    let message = {}
    message.id = 0;
    message.userId = principal.id;
    message.userName = principal.name;
    message.messageBody = this.messageBody().toString();
    return message;
}

accountLockedForm.showSuccessMessage = function () {
    $("#messageSendSuccess").show();
}

accountLockedForm.hideSuccessMessage = function () {
    $("#messageSendSuccess").hide();
}

accountLockedForm.hideForm = function () {
    $("#accountLockedForm").find("form").hide();
}

accountLockedForm.showForm = function () {
    $("#accountLockedForm").find("form").show();
}

accountLockedForm.success = function () {
    $("#accountLockedForm").find(".modal-content").fadeOut("fast", function () {
        accountLockedForm.hideForm();
        accountLockedForm.showSuccessMessage();
        $("#accountLockedForm").find(".modal-content").fadeIn("fast");
    })
}

