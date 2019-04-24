$(document).ready(function(){
    $("#myform").find("#commit").on("click",function f() {
        $.ajax({
            url:"/familyDoc/home/updateData",
            data:$("#myform").serialize(),
            dataType:"json",
            success:function(data){
                if(data.type == 'success'){
                    if(data.identity == "doc"){
                        swal({
                            title: "恭喜",
                            text: "你已成为本站医生",
                            type: "success",
                            confirmButtonColor: "#02dd03",
                            confirmButtonText: "OK"
                        },function () {
                            top.location.reload() ;
                        });
                        return;
                    }else if(data.identity == "patient"){
                        swal({
                            title: "操作成功",
                            text: "你可以行使本站患者所享有的权利啦！",
                            type: "success",
                            confirmButtonColor: "#02dd03",
                            confirmButtonText: "OK"
                        },function () {
                            top.location.reload() ;
                        });
                        return;
                    }
                    swal("操作成功", data.message, "success");
                }else {
                    swal("操作失败",data.message , "error");
                }
            }
        });
    });
    $("input").on("blur",function () {
        if($(this).val() == null || $(this).val()==''){
            swal({
                    title: "内容为空",
                    text: $(this).data("helper") + "不能为空",
                    type: "warning",
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "OK"
                });
        }
    });
});

$("#acceptDoc").on("click",function () {
    window.location.href="http://localhost:8080/familyDoc/home/myData?identity=1";
});
