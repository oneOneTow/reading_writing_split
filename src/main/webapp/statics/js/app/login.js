
function redirectToReg(){
    window.location.href = "/index.jsp";
}

function login(LogModel){
    var data = JSON.stringify(LogModel);
    console.log(data);
    $.ajax({
        url: api.login,
        type: "post",
        async: true,
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        data: data,
        success: function (data) {
            console.log(data)
        }
    })
}