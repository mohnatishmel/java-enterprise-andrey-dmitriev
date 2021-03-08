function authenticate(token, command, successFunk, errorFunk) {
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    var jsonToken = JSON.stringify({token});

    $.ajax({
        url: "/?command="+ command,
        type: 'POST',
        dataType: 'json',
        data: jsonToken,
        contentType: 'application/json',

        success: function (data) {
            successFunk(data)
        },
        error: function (data, status, er) {
            errorFunk(data)
        }
    });
}