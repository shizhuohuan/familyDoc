$(document).ready(function () {
    $("#detailDialog").dialog({
        autoOpen: false,
        width:500,
        height:400
    });
    $("#commit").on("click", function () {
        $.ajax({
            url: "/familyDoc/record/updateRecord",
            data: $("#myform").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.type == 'success') {
                    swal({
                        title: "操作成功",
                        text: "",
                        type: "success",
                        confirmButtonColor: "#02dd03",
                        confirmButtonText: "OK"
                    }, function () {
                        window.location.href = "http://localhost:8080/familyDoc/record/toMyRecord";
                    });
                }
            }
        })
    });

    $("#back").on("click", function () {
        window.location.href = "http://localhost:8080/familyDoc/record/toMyRecord";
    });

    $("#detail").on("click", function () {
        $( "#detailDialog" ).dialog( "open" );
    });
});