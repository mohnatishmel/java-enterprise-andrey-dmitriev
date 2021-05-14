function initUploadFileForm(id) {
    console.log("initUploadFileForm")
    document.getElementById("uploadFileTaskId").value = id;
}

document.getElementById("uploadFileBtn").addEventListener("click", function () {
    uploadFile();
})

function fileUploadSuccess() {
    let id = fileUploadForm.getTaskId();
    $("#downloadToolboxBtn" + id).show("fast");
}