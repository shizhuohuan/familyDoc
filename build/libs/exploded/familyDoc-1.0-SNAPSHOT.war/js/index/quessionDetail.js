$(document).ready(function () {
    $('.commentAll').on('click', '.plBtn', function () {
        var qid = $(this).data("value");
        //获取输入内容
        var oSize = $(this).parent().find(".comment-input").val();
        var oHtml = "";
        $.ajax({
            url: "/familyDoc/quession/answerQs",
            data: {"qid": qid, "content": oSize},
            dataType: "json",
            success: function (data) {
                if (data.type = 'success') {
                    //动态创建评论模块
                    oHtml = " <div class=\"comment-show-con-list pull-left clearfix\" id='"+ data.answerId +"'>\n" +
                        "                    <div class=\"pl-text clearfix\">\n" +
                        "                        <a href=\"javascript:docDetail('"+data.userId+"')\" class=\"comment-size-name\"> "+ data.docName + ": </a>\n" +
                        "                        <br>\n" +
                        "                        <span class=\"my-pl-con\">" + oSize + " </span>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"date-dz\">\n" +
                        "                        <span class=\"date-dz-left pull-left comment-time\">"+ data.createTime +"</span>\n" +
                        "                        <div class=\"date-dz-right pull-right comment-pl-block\">\n" +
                        "                           <a href=\"javascript:deleteAnswer('"+ data.answerId +"');\" class=\"date-dz-pl pl-hf hf-con-block pull-left\">删除</a>\n" +
                        "                                <span class=\"pull-left date-dz-line\">|</span>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"hf-list-con\"></div>\n" +
                        "                </div>";
                    $('.comment-show').find(".comment-show-con").prepend(oHtml);
                    $(".comment-input").val("");
                    $("#noAnswer").hide();
                }else {
                    swal("操作失败", data.message, data.type);
                }
            }
        });
    });

    $("#docDialog").dialog({
        autoOpen: false,
        width:600,
        height:500
    });

    $("#chatDoc").on("click",function () {
        var id = $("#chatDoc").data("docId");
        window.open("http://localhost:8080/familyDoc/chat/toCharRoom?recId=" + id);
    });
});
function keyUP(t) {
    var len = $(t).val().length;
    if (len > 139) {
        $(t).val($(t).val().substring(0, 140));
    }
}


function deleteAnswer(id){
    var qid = $("#currenQsId").val();
    $.ajax({
        url: "/familyDoc/quession/deleteAnswer",
        data: {"aid": id,"qid":qid},
        dataType: "json",
        success: function (data) {
            if(data.type == 'success'){
                $("#" + id).hide();
            }else {
                swal("操作失败", data.message, data.type);
            }
        }
    })
}

function docDetail(userId) {
    $.ajax({
        url:"/familyDoc/department/docShow",
        data:{"userId":userId},
        dataType:"json",
        success:function(data) {
            if (data.type == 'success') {
                var docData = data.docOfDpt;
                $("#docH3").text(docData.relName);
                $("#docAge").text(docData.age);
                if(docData.sex == "1"){
                    $("#docSex").text('男');
                }else {
                    $("#docSex").text('女');
                }
                $("#docDpt").text(docData.dptId);
                $("#docHospital").text(docData.hospital);
                $("#docProduce").text(docData.produce);
                $("#docAddress").text(docData.address);
                if(data.own == 'true'){
                    $("#chatDoc").hide();
                }else {
                    $("#chatDoc").show();
                }
                $( "#docDialog" ).dialog( "open" );
            }else {
                swal(data.message, "", data.type);
            }
        }
    });

}

function contactDoc(id) {
    window.open("http://localhost:8080/familyDoc/chat/toCharRoom?recId=" + id);
}
