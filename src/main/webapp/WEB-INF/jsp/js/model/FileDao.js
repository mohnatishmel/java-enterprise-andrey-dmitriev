function downloadFile(id) {
    $.getJSON('/?command=DownloadFile&id=' + id, function (result) {

        let name = result.name;

        let b64Data = result.base64;
        const byteCharacters = atob(b64Data);
        const byteNumbers = new Array(byteCharacters.length);
        for (let i = 0; i < byteCharacters.length; i++) {
            byteNumbers[i] = byteCharacters.charCodeAt(i);
        }
        const byteArray = new Uint8Array(byteNumbers);
        const blob = new Blob([byteArray]);

        let a = document.createElement('a');
        let url = window.URL.createObjectURL(blob);
        a.href = url;
        a.download = name.toString();
        document.body.append(a);
        a.click();
        a.remove();
        window.URL.revokeObjectURL(url);
    });
}

function uploadFile() {
    console.log("uploadFile")
    let formData = new FormData();

    formData.append("file", fileUploadForm.getFile());
    formData.append("name", fileUploadForm.getFileName());
    let id = fileUploadForm.getTaskId();

    $.ajax({
        url : "/?command=UploadFile&id=" + id,
        type : "POST",

        data : formData,
        // enctype : "multipart/form-data",
        processData : false,
        contentType: false,
        // cache : false,
        success: function () {
            alert('The file has been uploaded successfully.');
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
    console.log(document.getElementById("uploadFileInput").files[0].name + "is uploaded")

}