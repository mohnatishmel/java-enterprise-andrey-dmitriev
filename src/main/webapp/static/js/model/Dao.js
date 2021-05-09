function getJson(url) {
    return $.getJSON(url);
}

function getJsonPage(url, page, size) {
    return getJson(url + "?pageNumber=" + page + "&size=" +size);
}

function sendJson(data, url, method, success) {

    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    $.ajax({
        url: url,
        type: method,
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