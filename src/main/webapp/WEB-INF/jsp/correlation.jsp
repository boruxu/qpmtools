<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%  String path = request.getContextPath();  
    String basePath = request.getScheme()+"://"+request.getServerName()
    		+":"+request.getServerPort()+path+"/";%>      
     



<!DOCTYPE html>
<html >
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>量化项目管理工具</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="QPM,QPMTools,量化项目管理工具,量化项目管理">
    <meta name="author" content="boruxu">

    <!-- The styles -->
    <link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="css/charisma-app.css" rel="stylesheet">
    <link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='css/jquery.noty.css' rel='stylesheet'>
    <link href='css/noty_theme_default.css' rel='stylesheet'>
    <link href='css/elfinder.min.css' rel='stylesheet'>
    <link href='css/elfinder.theme.css' rel='stylesheet'>
    <link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='css/uploadify.css' rel='stylesheet'>
    <link href='css/animate.min.css' rel='stylesheet'>
    <link href='css/scatterplot_styles.css' rel='stylesheet'>
    
    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

    <!-- The fav icon 未解决之后修改-->
    <link rel="shortcut icon" href="img/favicon.ico">

</head>

<body>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
        
            <a class="navbar-brand" href="index.html"> <img alt="QPMTools Logo" src="img/logo20.png" class="hidden-xs"/>
                <span>量化项目管理工具</span></a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs">用户名字长用户名字长</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">个人设置</a></li> 
                    <li class="divider"></li>
                    <li><a href="#">个人仓库</a></li> 
                    <li class="divider"></li>
                    <li><a href="login.html">登出</a></li>
                </ul>
            </div>

            <ul class="collapse navbar-collapse nav navbar-nav top-menu pull-right">
            <!--
                <li><a href="#"><i class="glyphicon glyphicon-globe"></i> Visit Site</a></li>
                <li class="dropdown">
                    <a href="#" data-toggle="dropdown"><i class="glyphicon glyphicon-star"></i> Dropdown <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>-->
                <li >
                    <form class="navbar-search pull-left">
                        <input placeholder="搜索" class="search-query form-control col-md-10" name="query"
                               type="text">
                    </form>
                </li>
            </ul>

        </div>
    </div>
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">主菜单</li>
                        <li><a class="ajax-link" href="home"><i class="glyphicon glyphicon-home"></i><span>主页</span></a>
                        </li>
                        <li><a class="ajax-link" href="correlation"><i class="glyphicon glyphicon-eye-open"></i><span>相关性分析</span></a>
                        </li>
                        <li><a class="ajax-link" href="form.html"><i  class="glyphicon glyphicon-edit"></i><span>回归分析</span></a></li>
                        <li><a class="ajax-link" href="chart.html"><i class="glyphicon glyphicon-list-alt"></i><span>蒙特卡洛模拟</span></a>
                        </li>
                        <li><a class="ajax-link" href="typography.html"><i class="glyphicon glyphicon-font"></i><span>层次分析法</span></a>
                        </li>
      
                    <label id="for-is-ajax" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">警告!</h4>

                <p>使用本网站的一些功能，你必须解除对 <a href="http://baike.baidu.com/view/16168.htm" target="_blank">JavaScript</a>
                    的限制</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
            <div>
            <!--显示访问路径-->
    <ul class="breadcrumb">
        <li>
            <a href="correlation">相关性分析</a>
        </li>
    </ul>
</div>
<div class="row">
     <div class="box col-md-12">
        <div class="box-inner row" style="margin-right:0px; margin-left:0px; ">
            <div class="box-header well col-lg-12 col-md-12" >
                <h2><i class="glyphicon glyphicon-info-sign"></i>相关性分析输入</h2>           
                <div class="box-icon">
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content col-lg-12 col-md-12 " >
                      <ul class="nav nav-tabs " id="myTab">
                          <li class="active"><a href="#inputFromDB">从资产库获取输入数据</a></li>
                          <li class=""><a href="#inputByUser">自定义输入</a></li>
                      </ul>

                      <div id="myTabContent" class="tab-content  col-lg-12">
                           <div class="tab-pane active" id="inputFromDB">
                                <div class="control-group col-lg-10 col-md-10">
                                      <!--<label class="control-label  " for="selectTegs" >从资产库选择输入</label>-->

                                      <div class="controls" style=" margin-top:10px">
                                           <select id="selectTags" multiple class="form-control" >
                                               <optgroup label="组织名称">
                                                  <option>北航软件所过程改进小组</option>
                                                  <option>XXX公司1</option>
                                               </optgroup>
                                               <optgroup label="项目名称">
                                                  <option>量化项目管理工具</option>
                                                  <option>测试项目1</option>
                                               </optgroup>
                                               <optgroup label="相关性分析输入产品名">
                                                   <option>QPM测试输入</option>
                                               </optgroup>
                                           </select>
                                      </div>
                                  </div>
                           </div>
                           <div class="tab-pane " id="inputByUser">
                                <h3>设置输入
                                    <small>可以从资产库导入数据进行修改</small>
                                </h3>
                           </div>
                     </div>
                <div class="col-lg-12 col-md-12 ">
                        
                        <p class="center-block download-buttons" id="plot-button" >
                        <a  class="btn btn-primary btn-lg"  onClick="compute()"><i
                                class="glyphicon glyphicon-globe glyphicon-white"></i>提交计算</a>
                        </p>
                       
               </div>
               
   
                          
           </div>
        </div>
    </div>
     
</div>
<div class="row">  
    <div class="box col-md-9">
        <div class="box-inner">
            <div class="box-header well" id="scatterplot">
                <h2><i class="glyphicon glyphicon-info-sign"></i>散点图</h2>           
                <div class="box-icon">
                    <a href="#" class="btn btn-setting btn-round btn-default"><i
                            class="glyphicon glyphicon-cog"></i></a>
                    <a href="#" id="outputResult_1" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content row" >
            
                 <div class="col-lg-12 col-md-12 " id="scatterplotYname" style=" text-align:left">Y轴</div>

                 <div class="col-lg-12 col-md-12 " id="scatterplotSvg" style=" height:500px">
                      
                      <svg>
                      </svg>
                </div>
                <div class="col-lg-12 col-md-12 " id="scatterplotXname" style=" text-align:center">x轴</div>  
     
                <div class="col-lg-12 col-md-12 ">
                        
                        <p class="center-block download-buttons" id="plot-button" >
                        <a  class="btn btn-primary btn-lg"  onClick="rescale()">下一组<i
                                class="glyphicon glyphicon-chevron-right glyphicon-white"></i></a>
                        <a href="/" class="btn btn-default btn-lg"><i
                                class="glyphicon glyphicon-download-alt"></i>下载报告</a>
                        <!--<h5 id="scatterplotXname" style=" margin: 0 auto; text-align:right;">X轴：</h5>-->
                        </p>
                       
               </div>
   
                          
           </div>
        </div>
    </div>
     <div class="box col-md-3">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon glyphicon-info-sign"></i>相关</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-setting-alpha btn-round btn-default"><i
                            class="glyphicon glyphicon-cog"></i></a>
                    <a href="#" id="outputResult_2" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content row" >
                <div class="col-lg-12 col-md-12 ">
                    <p>
                       <h4 id="correlationName" style="color:#000">相关：<h2>
                       <h4 id="correlationR" style="color:#000">Pearson相关系数 r=<h4>
                       <h4 id="correlationP" style="color:#000">P值=<h4>
                       <h4 id="correlationAlpha" style="color:#000">α值=<h4>
                       <h4 id="correlationResult" style="color:#000">相关？<h4>
                    </p>
           
                </div>            
           </div>
        </div>
    </div>
    <div class="box col-md-3">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon glyphicon-info-sign"></i>术语解释</h2>

                <div class="box-icon">                  
                    <a href="#" id="outputResult_3" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content row" >
                <div class="col-lg-12 col-md-12 ">
                    <p >
                       <h4 style=" color:#000;">相关系数r表示两个变量间线性相关关系的密切程度<h4>
                          <ul style=" color:#000">
                             <li>r为正数：正相关</li>
                             <li>r为负数：负相关</li>
                             <li>r接近于0，两组值非线性相关</li>
                          </ul>
                       <h4 style=" color:#000;">由于工程实践中r有误差，需通过假设检验的P值来判断其相关性<h4>
                          <ul style=" color:#000">
                             <li>P值与设定的α值进行比较，一般α=0.05</li>
                             <li>P值&lt;α：表明数据相关</li>
                             <li>P值&ge;α：表明数据不相关</li>
                             <li>P值越接近0，表明对数据相关的判定越强</li>
                          </ul>
                    </p>
                       
           
                </div>            
           </div>
        </div>
    </div>
</div>
    <!--设置阿尔法值的框-->
    <div class="modal fade" id="alpha" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <!--<button type="button" class="close" data-dismiss="modal">×</button>-->
                    <h3>设置显著水平α值</h3>
                </div>
                <div class="modal-body">
                    <input id="alphaV" autofocus value="0.05" style=" text-align:center;"></input>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">取消</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">设置</a>
                </div>
            </div>
        </div>
    </div>

   
</div><!--/.fluid-container-->
<hr class="col-md-12 col-lg-12">
    
<footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; <a href="" target="_blank">北航计算机学院软件所课题改进小组</a> 2014-? </p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">作者：<a
                href="">xuboru</a></p>
</footer>


<!-- external javascript -->

<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="js/charisma.js"></script>
<script type="text/javascript" src="js/d3.js"></script>
<script type="text/javascript">
<!--以下为生成散点图的代码-->
//初始化数据
var dataXY={
	x:[45,59,33,81,77,26,19,55,50,34,99,61,38,72,59,25],
	y:[25,36,19,45,42,23,16,38,32,22,48,42,29,36,29,17],
	xName:"测试1X轴（单位）",
	yName:"测试1y轴（单位）",
	p:0,
	r:0,
	alpha:0.05};
var dataXY1={
	x:[10,20,30,40,50,60,70,80],
	y:[10,20,30,40,50,60,70,80],
	xName:"测试2X轴（单位）",
	yName:"测试2y轴（单位）",
	p:0,
	r:0,
	alpha:0.05};
var dataXY2={
	x:[10,20,30],
	y:[30,20,10],
	xName:"测试3X轴（单位）",
	yName:"测试3y轴（单位）" ,
	p:0,
	r:0,
	alpha:0.05};
		
var dataXYArray=[dataXY,dataXY1,dataXY2];

initialGlobelValues();

function initialGlobelValues()
{
	//全局变量
	arrayNumber=0;
    xmin=0;
    xmax=0;
    ymin=0;
    ymax=0;
    xName="x";
    yName="y";
    xyP="未计算";
    xyR="未计算";
    xyAlpha="未计算";
	outputMin("#outputResult_1");
	outputMin("#outputResult_2");
	outputMin("#outputResult_3");
	
}


var width =$("#scatterplot").width()*8/9, 
    height =$("#scatterplotSvg").height(), 
    margin = 25,
    xAxis, yAxis, xAxisLength, yAxisLength;
    


var svg = d3.select("svg")     
        .attr("class", "axis")  
        .attr("width", width)
        .attr("height", height)
		.attr("style","margin:0px 0px 0px 50px");


function renderXAxis(){
    xAxisLength = width - 2 * margin;

    var scale = d3.scale.linear()
                    .domain([xmin, xmax])
                    .range([0, xAxisLength]);
    
    xAxis = d3.svg.axis()
            .scale(scale)
            .tickSubdivide(1)
            .orient("bottom");
            
    svg.append("g")       
        .attr("class", "x-axis")
        .attr("transform", function(){ 
            return "translate(" + margin + "," + (height - margin) + ")";
        })
        .call(xAxis);

}

function renderYAxis(){        
    yAxisLength = height - 2 * margin;

    var scale = d3.scale.linear()
                    .domain([ymax, ymin])
                    .range([0, yAxisLength]);

    yAxis = d3.svg.axis()
            .scale(scale)
            .tickSubdivide(1)
            .orient("left");
        
    svg.append("g")       
        .attr("class", "y-axis")
        .attr("transform", function(){
            return "translate(" + margin + "," + margin + ")";
        })
        .call(yAxis);

}   
<!--数据轮换显示按钮变化-->        
function rescale(){
	if((++arrayNumber)<dataXYArray.length)
	{
		getXYrange(dataXYArray[arrayNumber]);
        xAxis.scale().domain([xmin,xmax]); 
        svg.select("g.x-axis")
        .transition()
        .call(xAxis); 
        
        yAxis.scale().domain([ymax, ymin]);
        svg.select("g.y-axis")
           .transition()
           .call(yAxis);
    
        renderXGridlines();
        renderYGridlines();
		
		setOther();

	    renderXYPoint(dataXYArray[arrayNumber]);

	}
	else
	{
		arrayNumber=-1;
		rescale();
	}
           
}
function setOther(){
			
	//设置散点图X轴名字
	$("#scatterplotXname").text(xName);
		//设置散点图y轴名字
	$("#scatterplotYname").text(yName);
	$("#correlationName").text("相关:"+xName+","+yName);
	$("#correlationR").text("R="+xyR);
	$("#correlationP").text("P="+xyP);
	$("#correlationAlpha").text(xyAlpha);
	}        
<!--建立X坐标轴-->
function renderXGridlines(){
    var lines = d3.selectAll("g.x-axis g.tick")
            .select("line.grid-line")
            .remove(); 
            
    lines = d3.selectAll("g.x-axis g.tick")
            .append("line") 
            .classed("grid-line", true)

    lines.attr("x1", 0) 
            .attr("y1", 0)
            .attr("x2", 0)
            .attr("y2", - yAxisLength); 
}
<!--建立Y坐标轴-->
function renderYGridlines(){
    var lines = d3.selectAll("g.y-axis g.tick")
            .select("line.grid-line").remove(); 
    
    lines = d3.selectAll("g.y-axis g.tick")
            .append("line") 
            .classed("grid-line", true)
            
    lines.attr("x1", 0)
        .attr("y1", 0)
        .attr("x2", xAxisLength)
        .attr("y2", 0);
}
//获取XY中的最大最小值，通过一定调整设置XY轴的范围，得到XY名字
function getXYrange(data){
	xmin=d3.min(data.x)-5;
	xmax=d3.max(data.x)+5;
	ymin=d3.min(data.y)-5;
	ymax=d3.max(data.y)+5;
	xName=data["xName"];
	yName=data["yName"];
    xyP=data["p"];
    xyR=data["r"];
    xyAlpha=data["alpha"];
	
}
<!--数据转换函数-->	
function dataToPointArray(data){
//输入数据为dataXY格式
    var dataset=new Array();
	var datasetX=new Array();
	data.x.forEach(function(d)
	{
		datasetX.push(d);
		});
    data.y.forEach(function(d,i)
	{
		dataset.push([datasetX[i],d])
		});
	return dataset;
}
<!--画出XY的点函数-->
function renderXYPoint(dataXYi){

var scalex= d3.scale.linear()
                    .domain([xmin, xmax])
                    .range([0, xAxisLength]);
var scaley= d3.scale.linear()
                    .domain([ymax, ymin])
                    .range([0, yAxisLength]);
			
var dataset=dataToPointArray(dataXYi);

svg.selectAll("circle")
	   .data(dataset)
	   .enter()
	   .append("circle");
	   
svg.selectAll("circle")
	   .data(dataset)
       .transition()
	   .attr("cx", function(d) {
		   return scalex(d[0]);})
	   .attr("cy", function(d) {
			   return scaley(d[1]);
			   })
	   .attr("r", 4)
	   .attr("transform", function(){
            return "translate(" + margin + "," + margin + ")";
        });	
		
svg.selectAll("circle")
	   .data(dataset)
	   .exit()
	   .remove(); 
		
}
<!--画出散点图-->
function scatterplot(){
  getXYrange(dataXYArray[0]);
  renderYAxis();
  renderXAxis();
  renderXGridlines();
  renderYGridlines();
  renderXYPoint(dataXYArray[0]);
  setOther();
};
//窗口改变事件，SVG散点图也要改变大小已适应不同窗口的大小
$(window).resize(function() {

   //清除原有的SVG图像
   $("svg").empty();
   $("plot-button").empty();
   width=$("#scatterplot").width()*8/9;
   height =$("#scatterplotSvg").height();
   scatterplot();
});

//scatterplot();
<!--以下为按钮弹出框等CSS设置-->
 $('.btn-setting-alpha').click(function (e) {
    e.preventDefault();
    $('#alpha').modal('show');
});

//输入js操作
$('.btn-minimize1').click(function (e) {
    e.preventDefault();
    var $target = $(this).parent().parent().nextall('.box-content');
    if ($target.is(':visible')) 
	     $('i', $(this)).removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
    else                       
	     $('i', $(this)).removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
    $target.slideToggle();
});
//绑定chosen插件
$("#selectTags").chosen();
$("#selectTags").chosen().change(
  function(){
	  var array=new Array();
	   $("ul.chosen-choices li.search-choice span").each(function() {
               array.push($(this).text()); 
        });
		//ajax查询数据
	  }
);
//展示块的最化
function outputMin(dom)
{
	var $target = $(dom).parent().parent().next('.box-content');
    if ($target.is(':visible')) 
	{
		$('i', $(dom)).removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
	    $target.slideUp(0);
	}  
}
function outputMax(dom)
{
	var $target = $(dom).parent().parent().next('.box-content');
    if ($target.is(':visible')){} 
	else
	{
		 $('i', $(dom)).removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
         $target.slideDown();
		}
}

//清空数据，收起输出结果栏
function clearData(){
	//清除散点图
	$("svg").empty();
	//初始化其他数据
	initialGlobelValues();
	 outputMin("#outputResult_1");
	 outputMin("#outputResult_2");
	 outputMin("#outputResult_3");
	
}


//提交计算结果
function compute()
{
	var dataArraysUpload={correlationIn:dataXYArray};
	clearData();
	setTimeout(function(){correlationAjax(dataArraysUpload);},1000);

	};
	
//ajax
function correlationAjax(data)
{ 
	 $.ajax({
			contentType: 'application/json', 
            type: "POST",
            url: "correlation/outputProduct",
            data:JSON.stringify(data),
			dataType: "json",
            error: function(e){
            	 alert(e.status);
				   alert("数据连接错误！");					 
				},
            success: function(json){
				
				json.forEach(function(d,i)
				{
					dataXYArray[i].p=d.p;
					dataXYArray[i].r=d.r;
					});
				 
			    scatterplot();
				outputMax("#outputResult_1");
	            outputMax("#outputResult_2");
	            outputMax("#outputResult_3");
		
					     	
            },
         
		 });
}



</script>
   

</body>
</html>

