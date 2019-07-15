$(function(){
    window.onclick = function(e){
        var a = ["富强", "民主", "文明", "和谐", "自由", "平等", "公正", "法治", "爱国", "敬业", "诚信", "友善"];
        var a_idx = Math.floor(Math.random()* a.length);
        var $i = $("<span/>").text(a[a_idx]);
        var x = e.pageX, y = e.pageY;
        $i.css({
            "z-index": 100000000,
            "top": y - 20,
            "left": x,
            "position": "absolute",
            "font-weight": "bold",
            "font-size": "20px",
            "color": randomColor()
        });
        $("body").append($i);
        $i.animate({"top": y - 180, "opacity": 0}, 1500, function () {
            $i.remove();
        });
    };
    function randomColor(){
        return "rgb("+(~~(Math.random()*255))+","+(~~(Math.random()*255))+","+(~~(Math.random()*255))+")";
    }
});

