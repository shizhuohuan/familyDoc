$(document).ready(function () {
    //初始化表格对象
    var messageTable = $('#messageTable').dataTable({
        //数据传入
        "autoWidth": false,
        "ajax": {
            "url": "/familyDoc/message/myMessage",
            "dataSrc": ""
        },
        "deferRender": true,
        "lengthMenu": [5, 10, 20, 30],
        //允许重建
        "destroy": true,
        "columns": [
            {
                "data": "content",
                "title": "内容",
                "render": function (data, type, row) {
                    if (data.length > 40) {
                        data = data.sub(0, 40) + "...";
                    }
                    return data;
                }
            },
            {
                "data": "sendId",
                "title": "发送人",
                "width": "10%"
            },
            {
                "data": "type",
                "title": "消息类型",
                "width": "10%",
                "render": function (data, type, row) {
                    if (data == null || data == '') {
                        return "---";
                    }
                    if(data == '0'){
                        return "聊天";
                    }
                    if(data == '1'){
                        return "问题";
                    }
                    if(data == '2'){
                        return "病历";
                    }
                    return data;
                }
            },
            {
                "data": "createDate",
                "title": "创建时间",
                "width": "20%"
            },

            {
                "title": "操作",
                "width": "5%",
                "render": function (data, type, row) {
                    var html = "<button class=\"btn btn-primary\" type='button' onclick=\"checkMessage('" + row.messageId + "','"+ row.type +"')\">查看</button>";
                    return html;
                }
            }
        ],
        "oLanguage": {
            "sLengthMenu": "每页显示 _MENU_ 条",
            "sZeroRecords": "没有找到符合条件的数据",
            "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
            "sInfoEmpty": "没有记录",
            "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
            "sSearch": "搜索",
            "sProcessing": "数据加载中...",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",
                "sLast": "尾页"
            }
        },
        "order": [3, 'desc']
    });
    checkMessage = function (id,type) {
        if(type == null || type == ''){
            swal("类型为空", "", "error");
            return;
        }
        $.ajax({
            url: "/familyDoc/message/confirmMsg",
            data: {"mid": id},
            dataType: "json",
            success: function (data) {
                if(data.type == 'success'){
                    $('#messageTable').dataTable()._fnAjaxUpdate();
                    if(type == '0'){
                        id = id.slice(0,id.indexOf("&"));
                        window.location.href = "http://localhost:8080/familyDoc/chat/toCharRoom?recId=" + id;
                    }else if(type == '1'){
                        window.open("http://localhost:8080/familyDoc/quession/quessionDetail?qid=" + id);
                    }else if(type == '2'){
                        window.open("http://localhost:8080/familyDoc/record/recordDetail?cid=" + id);
                        //window.location.href = "http://localhost:8080/familyDoc/record/recordDetail?cid=" + id;
                    }
                }else {
                    swal("操作失败", data.message, data.type);
                }
            }
        });
    }

});