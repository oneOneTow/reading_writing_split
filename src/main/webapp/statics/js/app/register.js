function login(RegModel){
    var data = JSON.stringify(RegModel);
    console.log(data);
    $.ajax({
        url: api.register,
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
function redirectToLogin(){
    window.location.href = "/login.jsp";
}