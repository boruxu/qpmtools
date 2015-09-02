/**
 * Created by x on 2015/7/7.
 * 主要是界面隐藏、显示和拖放一个div row的通用的js操作，和业务没有关系
 */

// glyphicon glyphicon-chevron-up 与 glyphicon glyphicon-chevron-down 互相变化以及其父容器内容的展现与隐藏

chevronUp();
chevronDown();
aUpDown();

function chevronUp(){
    $("body").delegate(".glyphicon-chevron-up", "click", function () {
        slideUp($(this));
    });
}

function chevronDown(){
    $("body").delegate(".glyphicon-chevron-down","click",function(){
        slideDown($(this));
    });
}


function slideUp(e)
{
    var thisNode = e;
    thisNode.parent().parent().parent().parent().children().eq(1).slideUp("normal", function () {
        thisNode.removeClass("glyphicon-chevron-up");
        thisNode.addClass("glyphicon-chevron-down");
    });
}
function slideDown(e)
{
    var thisNode=e;
    thisNode.parent().parent().parent().parent().children().eq(1).slideDown("normal",function()
    {
        thisNode.removeClass("glyphicon-chevron-down");
        thisNode.addClass("glyphicon-chevron-up");
    });
}


function aUpDown(){

    $("body").delegate("a", "click", function () {

        var thisNode=$(this).children(".glyphicon-chevron-up").eq(0);
        slideUp(thisNode);
        thisNode=$(this).children(".glyphicon-chevron-down").eq(0);
        slideDown(thisNode);


    });
}



$(function(){

    $("body").delegate(".dropdown-toggle", "click", function () {
        console.log("nav dropdown-toggle click event!");
        //对应的子菜单展开和合并
        $(this).parent().children().eq(2).slideToggle(500);
    });
    //解决页面直接跳转为某一侧边栏的选项，而侧边栏没展开的问题

    var findAngularJSUIActive=setInterval(function()
    {
        var $active=$("li.nav-level4.active");
        if($active.length!=0)
        {
            console.log("find active!");

            $active.parents(".nav-level2").children().eq(0).trigger("click");
            setTimeout(function(){
                $active.parents(".nav-level3").children().eq(0).trigger("click");
            },600);
            clearInterval(findAngularJSUIActive);
        }

    },100);

});


