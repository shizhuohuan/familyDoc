$(document).ready(function(){


});
function getHistory() {
    $.ajax({
        url:"/familyDoc/chat/getUnconfirmMsg",
        data:{"sendId":recId,"recId":sendId},
        dataType:"json",
        success:function(data) {
            if(data.type == 'success'){
                $.each(data.unconfirmMsg,function (index,val) {
                    setMessageInnerHTML(recName + "    " + val.createDate);
                    setMessageInnerHTML(val.content);
                })
            }else {
                swal(data.message, "", data.type);
            }
        }
    })
}