$(function () {
    var _timeline_date_ = $("<li><div class='tldate'>Apr 2017<div><li>");
    $(".timeline").append(_timeline_date_);
    var size = 5;
    var current = 1;
    var stopFlag = true;
    var loadData = function () {
        console.log(2);
        $.ajax({
            url : "/v1/test/testGet",
            type : "GET",
            data:{
                "size":size,
                "current":current
            },
            dataType : "json",
            success : function(data) {
                if (data.returnCode != 0) {
                    errorHandle(data);
                } else {
                    tmpList = data.list;
                    console.log(tmpList);
                    if (tmpList.length == 0) {
                        console.log(6);
                        stopFlag = false;
                        alert("已经到底了");
                    } else {
                        $.each(tmpList, function (idx, tl) {
                            if (idx % 2 === 1) {
                                var _timeline_invert_ = $("<li></li>");
                            } else {
                                _timeline_invert_ = $("<li></li>").addClass("timeline-inverted");
                            }

                            $(".timeline").append(_timeline_invert_);

                            var _timeline_icon_ = $("<div></div>").addClass("timeline-icon timeline-icon-hide-border");
                            var _timeline_fa_ = $("<i style='color:#c23b22'></i>").addClass("fa fa-github fa-lg");
                            _timeline_icon_.append(_timeline_fa_);
                            _timeline_invert_.append(_timeline_icon_);
                            /**
                             * 设置显示内容
                             */

                            var _timeline_panel_ = $("<div></div>").addClass("timeline-panel");
                            var _timeline_head_ = $("<div></div>").addClass("tl-heading");
                            var _timeline_body_ = $("<div></div>").addClass("tl-body");

                            var _timeline_body_p1_ = $("<p></p>").text(tl["feature"]);
                            _timeline_body_.append(_timeline_body_p1_);
                            var _timeline_body_p2_ = $("<p></p>");
                            _timeline_body_p2_.append($("<img/>").attr("src", tl["images"]));
                            _timeline_body_.append(_timeline_body_p1_);
                            _timeline_body_.append(_timeline_body_p2_);

                            var _timeline_head_h4_ = $("<h4></h4>").text(tl["diagDoc"]);
                            var _timeline_head_p_ = $("<p></p>");
                            var _timeline_head_p_small_ = $("<small class='text-muted'></small>");
                            var _timeline_head_p_small_i_ = $("<i class='glyphicon glyphicon-time'></i>");
                            _timeline_head_p_small_.append(_timeline_head_p_small_i_);
                            _timeline_head_p_small_.append(tl["diagTime"]);

                            _timeline_head_p_.append(_timeline_head_p_small_);

                            _timeline_head_.append(_timeline_head_h4_);
                            _timeline_head_.append(_timeline_head_p_);

                            _timeline_panel_.append(_timeline_head_);
                            _timeline_panel_.append(_timeline_body_);

                            _timeline_invert_.append(_timeline_panel_);
                        });
                    }
                    if ($(window).height() >= document.documentElement.scrollHeight) {
                        //没有出现滚动条,继续加载下一页
                        console.log(5);
                        loadData();
                    }
                }
            }
        });
    };

    var tcScroll = function () {
        console.log(3);
        $(window).on('scroll', function () {
            var scrollTop = $(this).scrollTop();
            var scrollHeight = $(document).height();
            var windowHeight = $(this).height();
            if (scrollTop + windowHeight === scrollHeight) {
                //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
                console.log(4);
                if (stopFlag) {
                    current++;
                    loadData();
                }
            }
        })
    };
    console.log(1);
    loadData();
    tcScroll();
});