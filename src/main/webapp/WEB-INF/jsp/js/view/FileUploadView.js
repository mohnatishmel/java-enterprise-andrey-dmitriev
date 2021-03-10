var fileUploadForm = {}

fileUploadForm.getTaskId = function () {
    return document.getElementById("uploadFileTaskId").value;
}

fileUploadForm.input = document.getElementById("uploadFileInput");

fileUploadForm.getFile = function () {
    return this.input.files[0];
}

fileUploadForm.getFileName = function () {
    return this.input.files[0].name;
}
