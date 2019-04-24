$(document).ready(function () {
    $.ajax({
        url: "/familyDoc/record/getDpt",
        dataType: "json",
        success: function (data) {
            if(data.type == 'success'){
                var html = "";
                $.each(data.allDpt, function(index,val) {
                       html += "<option value='" + val.id + "'>" + val.dpName + "</option>";
                });
                $("#myform select[name='dpt']").append(html);
            }else {
                swal("操作失败", data.message, data.type);
            }
        }
    });


    $("#myform select[name='dpt']").on("change",function f() {
        var dptId =  $("#myform select[name='dpt']").val();
        if(dptId == '-1'){
            $("#myform select[name='doc']").html("<option value=\"-1\">请选择科室</option>");
            return;
        }
        $.ajax({
            url: "/familyDoc/record/getDptDoc",
            data:{"dptId":dptId},
            dataType: "json",
            success: function (data) {
                if(data.type == 'success'){
                    var html = "<option value=\"-1\">请选中</option>";
                    $.each(data.doc, function(index,val) {
                        html += "<option value='" + val.userId + "'>" + val.relName + "</option>";
                    });
                    $("#myform select[name='doc']").html(html);
                }else {
                    swal("操作失败", data.message, data.type);
                }
            }
        });
    });

    $("#myform").find("#commit").on("click",function f() {
        if($("#myform textarea[name='talk']").val() == ''){
            swal("主诉不能为空", "", "error");
            return;
        }
        if($("#myform textarea[name='currentIll']").val() == ''){
            swal("现病史不能为空", "", "error");
            return;
        }
        if($("#myform select[name='dpt']").val() == '-1'){
            swal("请选择科室", "", "error");
            return;
        }
        if($("#myform select[name='doc']").val() == '-1'){
            swal("请选择医生", "", "error");
            return;
        }
        $.ajax({
            url: "/familyDoc/record/commitRecord",
            data:$("#myform").serialize(),
            dataType: "json",
            success: function (data) {
                if(data.type == 'success'){
                    swal({
                        title: "操作成功",
                        text: "请耐心等待医生回答！",
                        type: "success",
                        confirmButtonColor: "#02dd03",
                        confirmButtonText: "OK"
                    },function () {
                        window.location.href = "http://localhost:8080/familyDoc/record/toMyRecord";
                    });
                }else {
                    swal("操作失败", data.message, data.type);
                }
            }
        });
    })
});