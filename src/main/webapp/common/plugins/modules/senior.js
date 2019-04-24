/** layuiAdmin.std-v1.0.0 LPPL License By http://www.layui.com/admin/ */
;layui.define(function (e) {
    layui.use(["admin", "carousel"],
        function () {
            var e = layui.$,
                a = (layui.admin, layui.carousel),
                t = layui.element,
                i = layui.device();
            e(".layadmin-carousel").each(function () {
                var t = e(this);
                a.render({
                    elem: this,
                    width: "100%",
                    arrow: "none",
                    interval: t.data("interval"),
                    autoplay: t.data("autoplay") === !0,
                    trigger: i.ios || i.android ? "click" : "hover",
                    anim: t.data("anim")
                })
            }),
                t.render("progress")
        }
    ),
        //end
        layui.use(["carousel", "echarts"],
            function () {
                var e = layui.$,
                    a = (layui.carousel, layui.echarts);

                var r = [],
                    o = [
                        {
                            tooltip: {trigger: "axis"},
                            legend: {data: ["邮件营销", "联盟广告", "视频广告", "直接访问", "搜索引擎"]},
                            calculable: !0,
                            xAxis: [{
                                type: "category",
                                boundaryGap: !1,
                                data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
                            }],
                            yAxis: [{type: "value"}],
                            series: [
                                {name: "邮件营销", type: "line", stack: "总量", data: [120, 132, 101, 134, 90, 230, 210]},
                                {name: "联盟广告", type: "line", stack: "总量", data: [220, 182, 191, 234, 290, 330, 310]},
                                {name: "视频广告", type: "line", stack: "总量", data: [150, 232, 201, 154, 190, 330, 410]},
                                {name: "直接访问", type: "line", stack: "总量", data: [320, 332, 301, 334, 390, 330, 320]},
                                {name: "搜索引擎", type: "line", stack: "总量", data: [820, 932, 901, 934, 1290, 1330, 1320]}
                            ]
                        }
                    ],
                    m = e("#LAY-index-heapline").children("div"),
                    s = function (e) {
                        r[e] = a.init(m[e], layui.echartsTheme),
                            r[e].setOption(o[e]),
                            window.onresize = r[e].resize
                    };
                if (m[0]) {//---------3
                    s(0);
                }//end of --------3
            }
        ), e("senior", {})
});