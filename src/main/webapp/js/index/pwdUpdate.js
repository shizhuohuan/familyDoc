$(document).ready(function(){
    $("#myform").find("#commit").on("click",function f() {
        var pwd = $("#myform input[name='pwd']").val();
        var confirm = $("#myform input[name='confirm_pwd']").val();
        if(pwd == null || pwd == '' || confirm == null || confirm == ''){
            swal({
                title: "内容为空",
                text: "密码不能为空",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "OK"
            });
        } else if(pwd != confirm){
            swal({
                title: "内容不一致",
                text: "两次密码输入不一致",
                type: "warning",
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "OK"
            });
        }else {
            $.ajax({
                url:"/familyDoc/home/updatePwd",
                data:$("#myform").serialize(),
                dataType:"json",
                success:function(data){
                    if(data) {
                        swal(data.message,"", data.type);
                    }
                }
            });
        }
    });
});