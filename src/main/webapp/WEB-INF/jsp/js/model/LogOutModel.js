

function logout(success, error) {
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    $.ajax({
        url: "/?command=Logout",
        type: 'POST',
        dataType: 'json',
        contentType: 'application/json',

        success: function (data) {
            success(data)
        },
        error: function (data, status, er) {
            error(data)
        }
    });
}