function getJson(command) {
    return $.getJSON("/?command=" + command);
}

function getJsonPage(command, page, size) {
    return getJson(command + "&page=" + page + "&size=" +size);
}

function sendJson(data, command, success) {

    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    $.ajax({
        url: "/?command=" + command,
        type: 'POST',
        dataType: 'json',
        data: data,
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            success(data);
        },
        error: function (data, status, er) {
            alert("error: " + data + " status: " + status + " er:" + er);
        }
    });
}