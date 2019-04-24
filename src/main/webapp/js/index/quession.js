$(document).ready(function () {
    $("#commit").on("click", function () {
        var title = $("#myform input[name='title']").val();
        var content = $("#content").val();
        var dpt = $("#dptId").val();
        if (title == null || title == '') {
            swal("咨询标题不能为空", "", "error");
            return;
        }
        if (content == null || content == '') {
            swal("疾病介绍不能为空", "", "error");
            return;
        }
        $.ajax({
            url: "/familyDoc/quession/addQuession",
            data: {"title": title, "content": content, "dpt": dpt},
            dataType: "json",
            success: function (data) {
                if (data.type == 'success') {
                    swal({
                        title: "操作成功",
                        text: "请耐心等待医生回答！",
                        type: "success",
                        confirmButtonColor: "#02dd03",
                        confirmButtonText: "OK"
                    }, function () {
                        window.location.href = "http://localhost:8080/familyDoc/quession/toMyQuession";
                    });
                }
            }
        })
    });

    //初始化表格对象
    var myQsTable = $('#datatable').dataTable({
        //数据传入
        "autoWidth": false,
        "ajax": {
            "url": "/familyDoc/quession/myQuession",
            "dataSrc": ""
        },
        "deferRender": true,
        "lengthMenu": [5, 10, 20, 30],
        //允许重建
        "destroy": true,
        "columns": [
            {
                "data": "title",
                "title": "标题",
                "width": "30%",
                "render": function (data, type, row) {
                    var html = "";
                    if(row.userId == "iamanswer"){
                        html += "<span style='color: red'>(答)</span>";
                    }
                    html += "<a href=\"/familyDoc/quession/quessionDetail?qid=" + row.id + "\">" + data + "</a>";
                    return html;
                }
            },
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
                "data": "createTime",
                "title": "创建时间",
                "width": "20%"
            },
            {
                "title": "操作",
                "width": "5%",
                "render": function (data, type, row) {
                    var html = "<button class=\"btn btn-primary\" type='button' onclick=\"deleteQuession('" + row.id + "')\">删除</button>";
                    if(row.userId == "iamanswer"){
                        html = "<span style='margin-left: 30%'>---</span>";
                    }
                    return html;
                }
            },
            {
                "data": "answer",
                "title": "回答",
                "width": "5%",
                "render": function (data, type, row) {
                    if (data != undefined && data != '' && data != []) {
                        return "<span style='margin-left: 30%'>" + data.length + "</span>";
                    }
                    return "<span style='margin-left: 30%'>0</span>";
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
        "order": [2, 'desc']
    });

    deleteQuession = function (id) {
        $.ajax({
            url: "/familyDoc/quession/dQuession",
            data: {"qid": id},
            dataType: "json",
            success: function (data) {
                if (data.type == 'success') {
                    $('#datatable').dataTable()._fnAjaxUpdate();
                } else {
                    swal("操作失败", data.message, data.type);
                }
            }
        })
    };

});


// columnDefs: [{
//     targets: 3,// 操作例的位置，从0开始数为第几例，
//     data: "title",
//     width: 140,
//     "render": function (data, type, full) {
//         return "1";
//     }
// }],