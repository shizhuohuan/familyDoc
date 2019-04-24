$(document).ready(function () {
    //初始化表格对象
    var myRecordTable = $('#recordTable').dataTable({
        //数据传入
        "autoWidth": false,
        "ajax": {
            "url": "/familyDoc/record/myRecord",
            "dataSrc": ""
        },
        "deferRender": true,
        "lengthMenu": [5, 10, 20, 30],
        //允许重建
        "destroy": true,
        "columns": [
            {
                "data": "talk",
                "title": "主诉",
                "width": "45%",
                "render": function (data, type, row) {
                    if (data.length > 40) {
                        data = data.sub(0, 40) + "...";
                    }
                    return data;
                }
            },
            {
                "data": "deptId",
                "title": "科室",
                "width": "20%"
            },
            {
                "data": "createTime",
                "title": "创建时间",
                "width": "20%"
            },
            {
                "data": "docId",
                "title": "医生",
                "width": "10%"
            },
            {
                "data": "userId",
                "title": "患者",
                "width": "10%"
            },
            {
                "title": "操作",
                "width": "5%",
                "render": function (data, type, row) {
                    var html = "<button class=\"btn btn-primary\" type='button' onclick=\"checkRecord('" + row.id + "')\">查看</button>";
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
        "order": [2, 'desc']
    });

    checkRecord = function (id) {
        window.location.href = "http://localhost:8080/familyDoc/record/recordDetail?cid=" + id;
        // $.ajax({
        //     url: "/familyDoc/record/recordDetail",
        //     data: {"cid": id},
        //     dataType: "json",
        //     success: function (data) {
        //         if (data.type == 'success') {
        //
        //         } else {
        //             swal("操作失败", data.message, data.type);
        //         }
        //     }
        // })
    }
});