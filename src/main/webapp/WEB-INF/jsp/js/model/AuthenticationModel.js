function authenticate(token, command, successFunk, errorFunk) {
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    let jsonToken = JSON.stringify({token});

    $.ajax({
        url: "/?command="+ command,
        type: 'POST',
        dataType: 'json',
        data: jsonToken,
        contentType: 'application/json',

        success: function (data) {
            successFunk(data)
        },
        error: function (data) {
            errorFunk(data)
        }
    });
}



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
        error: function (data) {
            error(data)
        }
    });
}

function getCurrentUser() {
    return $.getJSON("/?command=GetPrincipal");
}

function Principal(jsonPrinciple) {
    this.id = jsonPrinciple.id;
    this.login = jsonPrinciple.credential.login;
    this.name = this.login;
    this.roles = jsonPrinciple.roles;
}