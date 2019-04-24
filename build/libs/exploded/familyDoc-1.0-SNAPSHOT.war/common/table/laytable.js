/*
    创建表格
    如果页面需要创建多个表格，则使用该语句：var Table_Layui1=Table_Layui,然后使用对象Table_Layui1去操作
*/
var Table_Layui = {
    //Table的模块ID--建议使用Div
    TablePanel: "",
    //是否需要序号
    CountNumberBool: true,
    //表格的列标题参数：
    //{ txtName: "列标题的名称", 
    //ValueCode: "列值的取值编码", 
    //width: 列的长度, 
    //Style: "自定义列的样式"
    //ValueDeal: 自定义的处理值得方法 }   
    Column: [
        {
            txtName: "", isShow: 1, ValueCode: "", width: 0, Style: "", ValueDeal: function (value, data) {
                return value;
            }
        }
    ],
    //编辑方法
    Edit: function () {
    },
    //删除方法
    Delete: function () {
    },
    data: [],
    //当前页
    PageIndex: 1,
    //每页展示的数据量
    PageSize: 10,
    //总数据量，假如该值为0，则表示不需要创建页码
    SumDateCounte: 0,
    //根据页码查询指定数据的方法
    SelectDataByPageIndex: function () {
    },
    //获取指定行数据
    GetRowData: function (index) {
        return this.data[index];
    },
    Utils: [],
    //页码
    Page_Layui: Page_Layui,
    //创建Layui的表格框架
    CreateTableFrame: function () {
        var width = 0;
        var theadHtml = "";
        //序号字段
        if (this.CountNumberBool) {
            theadHtml = "<th>序号</th>";
        }
        //列标题
        var Column = this.Column;
        for (var i = 0; i < Column.length; i++) {
            if (Column[i].isShow != 0)
                theadHtml += "<th style='" + (Column[i].Style == undefined ? "" : Column[i].Style) + "'>" + Column[i].txtName + "</th>";
        }
        theadHtml += "<th>操作</th>";


        //数据
        var tbody = "";
        if (this.data != undefined && this.data != null) {
            if (this.data.length > 0) {
                for (var i = 0; i < this.data.length; i++) {
                    var num = (this.PageIndex - 1) * this.PageSize + i + 1;
                    tbody += "<tr><td>" + num + "</td>";
                    //序号            
                    for (var j = 0; j < Column.length; j++) {
                        if (Column[j].isShow != 0) {
                            //单元格的样式
                            tbody += "<td style=\'" + (Column[j].Style == undefined ? "" : Column[j].Style) + "\'>";
                            var value = "";
                            //单元格的数据
                            if (Column[j].ValueDeal != undefined) {
                                value = Column[j].ValueDeal(this.data[i][Column[j].ValueCode], this.data);
                            }
                            else {
                                value = this.data[i][Column[j].ValueCode];
                            }
                            tbody += (value == null ? "" : value) + "</td>";
                        }
                    }

                    //控制按钮
                    tbody += "<td><div class='layui-btn-group'>";

                    //查看按钮功能
                    if (this.See != "") {
                        tbody += "<a href='javascript:void(0);' class='layui-btn layui-btn-mini layui-btn-normal' title='查看' onclick='See(" + i + ")' > 查看</a>";
                    }
                    //编辑按钮功能
                    if (this.Edit != "") {
                        tbody += "<a href='javascript:void(0);' class='layui-btn layui-btn-mini layui-btn-warm' title='编辑' onclick='Edit(" + i + ")' >编辑</a>";
                    }
                    //删除按钮功能
                    if (this.Delete != "") {
                        tbody += "<a href='javascript:void(0);' class='layui-btn layui-btn-mini layui-btn-danger' title='删除' onclick='Delete(" + i + ")' >删除</a>";
                    }

                    for (var k = 0; k < this.Utils.length; k++) {
                        tbody += "<a href='javascript:void(0);' class='layui-btn layui-btn-mini " + this.Utils[k].style + "' title='" + this.Utils[k].Name + "' onclick='" + this.Utils[k].MethodName + "(" + i + ")' >" + this.Utils[k].Cron + "</a>";
                    }
                    tbody += "</div></td>";
                    tbody += "</tr>";
                }
            }
        }
        //初始化页码对象
        this.Page_Layui = Page_Layui;
        //初始化分页容器的ID
        if (this.Page_Layui.PagePlaneID == "") {
            this.Page_Layui.PagePlaneID = new Date().getTime();
        }

        //需要创建页码
        if (this.SumDateCounte > 0) {
            width = width < 530 ? 530 : width;//控制容器的最小长度需要适配页码
        }


        var html = "<div class='layui-field-box'>"
            + " <table class='site-table table-hover'>"
            + "<thead><tr style='font-size:14px;'>"
            + theadHtml
            + "</tr></thead>"
            + tbody
            + " </table>"
            + " <div id=\"" + this.Page_Layui.PagePlaneID + "\"></div>"
            + "</div>";
        $("#" + this.TablePanel).html(html);
        //创建页码
        if (this.SumDateCounte > 0) {
            this.Page_Layui.PageIndex = this.PageIndex;
            this.Page_Layui.PageSize = this.PageSize;
            this.Page_Layui.SumDateCounte = this.SumDateCounte;
            this.Page_Layui.SelectDataByPageIndex = this.SelectDataByPageIndex;
            this.Page_Layui.CreatePage();//创建页码
        }
    }
};


var Util = {
    Name: "",
    MethodName: "",
    Cron: "",
    style: ""
};

function CreateUtil(name, mname, cron, style) {
    var util = {};
    util.Name = name;
    util.Cron = cron;
    util.MethodName = mname;
    util.style = style;
    return util;
}


function SeeIt(Column, data, id, i, add) {
    var form = "";
    if (add != undefined)
        form += add;
    form += "<form class='layui-form'>"
    for (var j = 0; j < Column.length; j++) {
        var value = "";
        if (Column[j].ValueDeal != undefined) {
            value = Column[j].ValueDeal(data[Column[j].ValueCode], this.data);
        }
        else {
            value = data[Column[j].ValueCode];
        }
        if (value == null)
            value = "";
        if (isNaN(value) && value.indexOf("<img") >= 0)
            ;//form+="<div class='layui-input-block'>"+value+"</div></div>";
        else {
            form += "<div class='layui-form-item' pane>";
            form += "<label class='layui-form-label'>" + Column[j].txtName + "</label>"
            form += "<div class='layui-input-block'><input type='text' class='layui-input' value='" + value + "' disabled></div></div>";
        }
    }
    form += "</form>";
    $('#' + id).html(form);
    ShowIt(id);
}


function ShowIt(id) {
    layer.open({
        maxWidth: 600,
        type: 1,
        content: $('#' + id), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        cancel: function (index, layero) {
            layer.close(index)

            return false;
        }
    });
}

/*
    创建页码
    如果页面需要创建多个页码，则使用该语句：var Page_Layui1=Page_Layui,然后使用对象Page_Layui1去操作
*/
var Page_Layui = {
    //存放页码的容器
    PagePlaneID: "",
    //当前页
    PageIndex: 1,
    //每页展示的数据量
    PageSize: 10,
    //数据的总数量
    SumDateCounte: 0,
    //根据页码查询数据的方法
    SelectDataByPageIndex: function (PageIndex) {
    },
    //刷新当前页数据
    Refresh: function () {
        this.SelectDataByPageIndex(this.PageIndex);
    },
    CreatePage: function () {
        $("#" + this.PagePlaneID).css("width", "100%");
        $("#" + this.PagePlaneID).css("text-align", "left");
        var Page_Layui_PageSelect = this.SelectDataByPageIndex;
        var Page_Layui_PageID = this.PagePlaneID;
        var Page_Layui_PageIndex = this.PageIndex;
        var SumPage = Math.ceil(this.SumDateCounte / this.PageSize);
        layui.use(['laypage', 'layer'], function () {
            var laypage = layui.laypage
                , layer = layui.layer;
            laypage({
                cont: Page_Layui_PageID
                , curr: Page_Layui_PageIndex
                , pages: SumPage //总页数
                , groups: 5 //连续显示分页数
                , jump: function (obj, first) {
                    //得到了当前页，用于向服务端请求对应数据
                    if (!first) {
                        //$("#" + thisPageIndexLabelID).val(obj.curr);
                        Page_Layui_PageSelect(obj.curr);
                    }
                }
            });
        });
    }


};
