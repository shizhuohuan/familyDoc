$(document).ready(function(){
    $("#mydialog").dialog({
        autoOpen: false,
        width:600,
        height:500
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
                    $("#chatDoc").data("docId",docData.userId);
                }
                $( "#mydialog" ).dialog( "open" );
            }else {
                swal(data.message, "", data.type);
            }
        }
    });

    $("#chatDoc").on("click",function () {
        var id = $("#chatDoc").data("docId");
        window.open("http://localhost:8080/familyDoc/chat/toCharRoom?recId=" + id);
    })
}
