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

function nav_dropdown_toggle(){
    $("body").delegate(".dropdown-toggle-x", "click", function () {
        var ul=$(this).parent().children().eq(2);
        if(ul.hasClass("nav-hide"))
        {
            ul.slideDown("normal", function () {
                ul.remove("nav-hide");
                ul.addClass("nav-show");
            });

        }
        else if(ul.hasClass("nav-show"))
        {
            ul.slideUp("normal", function () {
                ul.remove("nav-show");
                ul.addClass("nav-hide");
            });

        }
    });
}

nav_dropdown_toggle();
