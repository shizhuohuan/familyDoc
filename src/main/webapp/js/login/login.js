$(document).ready(function(){
    $("#usernamesignup").on("blur",function () {
        var username = $("#usernamesignup").val();
       $.ajax({
           url:"/familyDoc/home/validateUserName",
           data:{"username":username},
           dataType:"json",
           success:function(data){
                if(data.error){
                    alert(data.error);
                    $("#usernamesignup").val("");
                }
           }
       })
    });
    $("#passwordsignup_confirm").on("blur",function () {
        var pwd = $("#passwordsignup").val();
        var pwd_confirm = $("#passwordsignup_confirm").val();
        if(pwd!=pwd_confirm){
            $("#confirmPwdMsg").show();
        }else {
            $("#confirmPwdMsg").hide();
        }
    });

    $("#registBtn").on("click",function () {
        $.ajax({
            url:"/familyDoc/home/register",
            data:$('#registForm').serialize(),
            dataType:"json",
            success:function(data){
                alert(data.msg);
                if(data.type == "success"){
                    window.location.href="http://localhost:8080/familyDoc/";
                }
            }
        })
    });
});