$(document).ready(function () {
    $("#quessionSearch").on("click",function () {
        var keyword = $("#quessionKeyword").val();
        if(keyword == ""){
            window.location.href = "http://localhost:8080/familyDoc/quession/allQuession";
            return;
        }
        $("#quessionContent").html("搜索中...");
        $.ajax({
            url: "/familyDoc/search/searchQuession",
            data: {"keyword": keyword},
            dataType: "json",
            success: function (data) {
                if(data.type == 'success'){
                    var html = "<div class=\"title\">\n" +
                        "                <h3>网友提问</h3>\n" +
                        "            </div>\n";
                    if(data.quession == null || data.quession == ""){
                        html+= "                <img src=\"../static/images/noData.jpg\">\n" ;
                    }else {
                        $.each(data.quession,function (index,val) {
                            html +=
                                "                <article class=\"excerpt excerpt-1\" style=\"\">\n" +
                                "                    <div style=\"margin-left: -130px \">\n" +
                                "                        <h2><a href=\"/familyDoc/quession/quessionDetail?qid="+ val.id +"\" >"+val.title+"</a>\n" +
                                "                        </h2>\n" +
                                "                        <p class=\"meta\">\n" +
                                "                            <time class=\"time\"><i class=\"glyphicon glyphicon-time\"></i> \n" +
                                "                                    "+val.createTime+"</time>\n" +
                                "                            <span class=\"comment\" title=\"评论\" target=\"_blank\">\n" +
                                "                                <i class=\"glyphicon glyphicon-comment\"></i> 评论"+val.answer.length+"\n" +
                                "                            </span>\n" +
                                "                        </p>\n" +
                                "                        <p class=\"note\">"+val.content+"</p>\n" +
                                "                    </div>\n" +
                                "                </article>\n";
                        })
                    }
                    $("#quessionContent").html(html);
                    $("#quessionCount").text(data.quession.length);
                }else {
                    swal(data.message, "", data.type);
                    $("#quessionContent").html("发生错误");
                }
            }
        })
    })
});