$(document).ready(function () {
    $("#docSearchDialog").dialog({
        autoOpen: false,
        width:600,
        height:500
    });
    $("#docSearch").on("click",function () {
        var keyword = $("#docKeyword").val();
        var url = "/familyDoc/search/searchDoc";
        if(keyword == ""){
            url = "/familyDoc/search/searchAllDoc";
        }
        $("#docContent").html("搜索中...");
        $.ajax({
            url: url,
            data: {"keyword": keyword},
            dataType: "json",
            success: function (data) {
                if(data.type == 'success'){
                    var html = "<div class=\"title\">\n" +
                        "                <h3>医生信息</h3>\n" +
                        "            </div>\n" ;
                    if(data.doc == null || data.doc == ""){
                        html+="                <img src=\"../static/images/noData.jpg\">\n" ;
                    }else {
                        $.each(data.doc,function (index,val) {
                            html +=
                                "                <article class=\"excerpt excerpt-1\" style=\"\">\n" +
                                "                    <div style=\"margin-left: -130px \">\n" +
                                "                        <h2><a href=\"javascript:docDetail('"+ val.userId +"')\">"+val.relName+"</a>\n" +
                                "                        </h2>\n" +
                                "                        <p class=\"meta\">\n" +
                                "                            <time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i>\n" ;
                            if(val.sex == "1"){
                                html += "男";
                            }else {
                                html +="女";
                            }
                            html +=
                                "                                </time>\n" +
                                "                            <span class=\"comment\" title=\"年龄\" target=\"_blank\">\n" +
                                "                                <i class=\"glyphicon glyphicon-comment\"></i>"+val.age+"岁\n" +
                                "                            </span>\n" +
                                "                        </p>\n" +
                                "                        <p class=\"meta\">\n" +
                                "                            <time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i>\n" +
                                "                                "+val.hospital+"</time>\n" +
                                "                            <span class=\"comment\" title=\"职称\" target=\"_blank\">\n" +
                                "                                <i class=\"glyphicon glyphicon-comment\"></i>"+val.level+"\n" +
                                "                            </span>\n" +
                                "                        </p>\n" +
                                "                        <p class=\"note\">"+val.address+"</p>\n" +
                                "                        <hr>\n" +
                                "                        <p class=\"note\">"+val.produce+"</p>\n" +
                                "                    </div>\n" +
                                "                </article>\n";
                        })
                    }
                    $("#docContent").html(html);
                    $("#hitCount").text(data.doc.length);
                }else {
                    swal(data.message, "", data.type);
                    $("#docContent").html("发生错误");
                }
            }
        })
    });
});

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
                $( "#docSearchDialog" ).dialog( "open" );
            }else {
                swal(data.message, "", data.type);
            }
        }
    });
}