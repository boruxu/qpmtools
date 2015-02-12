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
                        <li><a class="ajax-link" href="spc"><i class="glyphicon glyphicon-list-alt"></i><span>统计过程控制</span></a></li>
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
            <a href="spc">统计过程控制</a>
        </li>
    </ul>
</div>
<div class="row">
     <div class="box col-md-12">
        <div class="box-inner row" style="margin-right:0px; margin-left:0px; ">
            <div class="box-header well col-lg-12 col-md-12" >
                <h2><i class="glyphicon glyphicon-info-sign"></i>统计过程控制输入</h2>           
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
                        <a  class="btn btn-primary btn-lg"  onClick="submit()"><i
                                class="glyphicon glyphicon-globe glyphicon-white"></i>提交计算</a>
                        </p>
                       
               </div>
               
   
                          
           </div>
        </div>
    </div>
     
</div>
<div class="row">
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well">
                <h2><i class="glyphicon glyphicon-info-sign"></i>控制异常点</h2>

                <div class="box-icon">
                    <a href="#" class="btn btn-setting-alpha btn-round btn-default"><i
                            class="glyphicon glyphicon-cog"></i></a>
                    <a href="#"  class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content row" >
                <div class="col-lg-12 col-md-12 ">
                    <p>
                       <h4 style="color:#000">无异常<h2>
                    </p>
           
                </div>            
           </div>
        </div>
    </div>
</div>
<div class="row">  
    <div class="box col-md-12">
        <div class="box-inner">
            <div class="box-header well" id="spcplot" >
                <h2><i class="glyphicon glyphicon-info-sign"></i>控制图</h2>           
                <div class="box-icon">
                    <a href="#" class="btn btn-setting btn-round btn-default"><i
                            class="glyphicon glyphicon-cog"></i></a>
                    <a href="#" class="btn btn-minimize btn-round btn-default"><i
                            class="glyphicon glyphicon-chevron-up"></i></a>
                    <a href="#" class="btn btn-close btn-round btn-default"><i
                            class="glyphicon glyphicon-remove"></i></a>
                </div>
            </div>
            <div class="box-content row" >
            
                 <div class="col-lg-12 col-md-12 "  style=" text-align:left">样本均值</div>

                 <div class="col-lg-12 col-md-12 " id="spcSvg" >
                      
                      <svg id="svg_1">
                      </svg>
                </div>
                <div class="col-lg-12 col-md-12 "  style=" text-align:center">样本</div>
                <div class="col-lg-12 col-md-12 "  style=" text-align:left">样本均值</div>
                <div class="col-lg-12 col-md-12 "  >
                      
                      <svg id="svg_2">
                      </svg>
                </div>
                <div class="col-lg-12 col-md-12 "  style=" text-align:center">样本</div>  
     
                <div class="col-lg-12 col-md-12 ">
                        
                        <p class="center-block download-buttons" id="plot-button" >
                        <a href="/" class="btn btn-default btn-lg"><i
                                class="glyphicon glyphicon-download-alt"></i>下载报告</a>
                        </p>
                       
               </div>
   
                          
           </div>
        </div>
    </div>
   

</div>
    <!--设置控制准则框-->
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


<script type="text/javascript" src="js/spcD3.js"></script>
<style>
.dot {
  fill: #fff;
  stroke: steelblue;
}
text.dot {
  fill: #fff;
  stroke: black;
  opacity:0;
}
g.dot:hover text.dot{
  fill: #fff;
  stroke: black;
  opacity:1;
}
path.line {
fill: none;
stroke: steelblue;
stroke-width: 3;
} 
path.line_2 {
fill: none;
stroke: darkcyan;
stroke-width: 3;
}
line._0 {
fill: none;
stroke: orangered ;
stroke-width: 3;
}
line._1 {
fill: none;
stroke: yellowgreen;
stroke-width: 3;
}
line._2 {
fill: none;
stroke: orangered ;
stroke-width: 3;
}
</style>
<script type="text/javascript">

 var svgX="#svg_1";
 var svgR="#svg_2";
 var spcOutData={x:[10.948, 10.95, 10.94, 10.938, 10.974, 10.944, 10.936, 10.944, 10.95, 10.934, 10.954, 10.95, 10.96, 10.938, 10.968, 10.94, 10.964, 10.966, 10.96, 10.94, 10.954, 10.954, 10.946, 10.962, 10.942],
 time:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25],
 r:[0.08, 0.07, 0.06, 0.03, 0.1, 0.06, 0.08, 0.03, 0.04, 0.05, 0.04, 0.06, 0.04, 0.05, 0.11, 0.02, 0.04, 0.08, 0.07, 0.06, 0.03, 0.08, 0.03, 0.1, 0.04],
 xUCL:10.9837,
 xCL:10.9502,
 xLCL:10.9168,
 rUCL:0.1226,
 rCL:0.058,
 rLCL:0.0};
 
 var spcOutDataXS={x:[80.04, 52.82, 35.61, 32.72],time:[1, 2, 3, 4],s:[43.6, 33.26, 16.89, 21.1],xUCL:72.94,xCL:50.30,xLCL:27.65,sUCL:45.15,sCL:28.72,sLCL:12.29};
 
 var spcOutDataXMR={x:[50.5, 43.5, 45.5, 39.8, 42.9, 44.3, 44.9, 42.9, 39.8, 39.3, 48.8, 51.0, 44.3, 43.0, 51.3, 46.3, 45.2, 48.1, 45.7, 44.1, 40.6, 45.7, 51.9, 47.3, 46.4, 44.4, 49.0, 47.9, 45.5, 44.8, 46.0, 41.1, 44.1, 41.8, 47.9, 44.9, 43.4, 49.0, 45.5, 47.4, 50.0, 49.0, 42.6, 41.7, 38.5, 44.5, 46.5, 41.7, 42.6, 41.7, 43.8, 41.8, 45.5, 44.5, 38.6, 43.2, 43.8, 44.8, 43.5, 40.9, 50.0, 43.4, 48.3, 46.4, 43.4, 52.3, 45.2, 42.2, 44.8, 42.2, 50.0, 46.2, 47.4, 42.2, 47.0, 47.3, 49.7, 48.0, 42.0, 41.0],time:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80],mr:[7.0, 2.0, 5.7, 3.1, 1.4, 0.6, 2.0, 3.1, 0.5, 9.5, 2.2, 6.7, 1.3, 8.3, 5.0, 1.1, 2.9, 2.4, 1.6, 3.5, 5.1, 6.2, 4.6, 0.9, 2.0, 4.6, 1.1, 2.4, 0.7, 1.2, 4.9, 3.0, 2.3, 6.1, 3.0, 1.5, 5.6, 3.5, 1.9, 2.6, 1.0, 6.4, 0.9, 3.2, 6.0, 2.0, 4.8, 0.9, 0.9, 2.1, 2.0, 3.7, 1.0, 5.9, 4.6, 0.6, 1.0, 1.3, 2.6, 9.1, 6.6, 4.9, 1.9, 3.0, 8.9, 7.1, 3.0, 2.6, 2.6, 7.8, 3.8, 1.2, 5.2, 4.8, 0.3, 2.4, 1.7, 6.0, 1.0],xUCL:54.0704,xCL:45.05,xLCL:36.0296,mrUCL:11.0789,mrCL:3.3911,mrLCL:0.0}
 var width=$("#spcplot").width();
 //spcD3.size(width,400,50).XR(spcOutData,svgX,svgR);
  //spcD3.size(width,400,50).XS(spcOutDataXS,svgX,svgR);
 spcD3.size(width,400,50).XMR(spcOutDataXMR,svgX,svgR);
 function empty(){
	 spcD3.empty(svgX,svgR);
	 }
 var data=
 {name:"test2",input:{x:[[10.95, 10.90, 10.95, 10.96, 10.98],
                                           [10.91, 10.97, 10.95, 10.98, 10.94],
                                           [10.97, 10.91, 10.94, 10.95, 10.93],
                                           [10.92, 10.94, 10.95, 10.95, 10.93],
                                           [11.02, 10.96, 10.92, 10.98, 10.99],
                                           [10.92, 10.94, 10.93, 10.98, 10.95],
                                           [10.98, 10.91, 10.96, 10.90, 10.93],
                                           [10.96, 10.93, 10.94, 10.93, 10.96],
[10.94, 10.93, 10.97, 10.96, 10.95],
[10.91, 10.95, 10.93, 10.96, 10.92],
[10.94, 10.94, 10.98, 10.94, 10.97],
[10.97, 10.95, 10.93, 10.92, 10.98],
[10.99, 10.95, 10.95, 10.95, 10.96],
[10.93, 10.97, 10.94, 10.92, 10.93],
[11.02, 10.98, 10.97, 10.96, 10.91],
[10.95, 10.95, 10.93, 10.94, 10.93],
[10.96, 10.95, 10.97, 10.99, 10.95],
[10.97, 10.97, 10.93, 10.95, 11.01],
[11.00, 10.93, 10.95, 10.96, 10.96],
[10.95, 10.92, 10.92, 10.98, 10.93],
[10.95, 10.94, 10.95, 10.96, 10.97],
[10.92, 10.97, 11.00, 10.94, 10.94],
[10.95, 10.94, 10.93, 10.96, 10.95],
[11.00, 10.99, 10.90, 10.94, 10.98],
[10.94, 10.92, 10.96, 10.93, 10.96]],
time:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25]}};
 var datd2={name:Test2,type:spcXRspcXR,stauts:computeFinished,input:{x:[[10.95, 10.90, 10.95, 10.96, 10.98],[10.91, 10.97, 10.95, 10.98, 10.94],[10.97, 10.91, 10.94, 10.95, 10.93],[10.92, 10.94, 10.95, 10.95, 10.93],[11.02, 10.96, 10.92, 10.98, 10.99],[10.92, 10.94, 10.93, 10.98, 10.95],[10.98, 10.91, 10.96, 10.90, 10.93],[10.96, 10.93, 10.94, 10.93, 10.96],[10.94, 10.93, 10.97, 10.96, 10.95],[10.91, 10.95, 10.93, 10.96, 10.92],[10.94, 10.94, 10.98, 10.94, 10.97],[10.97, 10.95, 10.93, 10.92, 10.98],[10.99, 10.95, 10.95, 10.95, 10.96],[10.93, 10.97, 10.94, 10.92, 10.93],[11.02, 10.98, 10.97, 10.96, 10.91],[10.95, 10.95, 10.93, 10.94, 10.93],[10.96, 10.95, 10.97, 10.99, 10.95],[10.97, 10.97, 10.93, 10.95, 11.01],[11.00, 10.93, 10.95, 10.96, 10.96],[10.95, 10.92, 10.92, 10.98, 10.93],[10.95, 10.94, 10.95, 10.96, 10.97],[10.92, 10.97, 11.00, 10.94, 10.94],[10.95, 10.94, 10.93, 10.96, 10.95],[11.00, 10.99, 10.90, 10.94, 10.98],[10.94, 10.92, 10.96, 10.93, 10.96]],time:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25],},output:{x:[10.9480, 10.9500, 10.9400, 10.9380, 10.9740, 10.9440, 10.9360, 10.9440, 10.9500, 10.9340, 10.9540, 10.9500, 10.9600, 10.9380, 10.9680, 10.9400, 10.9640, 10.9660, 10.9600, 10.9400, 10.9540, 10.9540, 10.9460, 10.9620, 10.9420],time:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25],r:[0.0800, 0.0700, 0.0600, 0.0300, 0.1000, 0.0600, 0.0800, 0.0300, 0.0400, 0.0500, 0.0400, 0.0600, 0.0400, 0.0500, 0.1100, 0.0200, 0.0400, 0.0800, 0.0700, 0.0600, 0.0300, 0.0800, 0.0300, 0.1000, 0.0400],xUCL:10.9837,xCL:10.9502,xLCL:10.9168,rUCL:0.1226,rCL:0.0580,rLCL:0.0000}}


 function submit(){
	
	

 $.ajax({
		contentType: 'application/json', 
        type: "GET",
        url: "/qpmtools/spc/xsplot/byName/中文",
       // data:JSON.stringify(data),
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

