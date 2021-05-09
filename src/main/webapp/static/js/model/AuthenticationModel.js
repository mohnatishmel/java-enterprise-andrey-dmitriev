function authenticate(token, url, successFunk, errorFunk) {
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });

    let jsonToken = JSON.stringify(token);

    console.log(jsonToken)

    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        data: jsonToken,
        contentType: 'application/json',

        success: function (data) {
            successFunk(data)
            console.log(data)
        },
        error: function (data) {
            errorFunk(data)
            console.log(data)
        }
    });
}



function logout(action) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/logout", false );
    xmlHttp.send( null );
    action();

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